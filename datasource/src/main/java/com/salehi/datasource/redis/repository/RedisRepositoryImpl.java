package com.salehi.datasource.redis.repository;

import com.salehi.datasource.redis.interfaces.IRedisHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.core.index.Indexed;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author Arash Salehi
 * @author arashsalehi849@yahoo.com
 * @since 0.0.1
 */
@Repository
public class RedisRepositoryImpl<T extends IRedisHash> {

    @Autowired
    private RedisTemplate<String, T> redisTemplate;
    @Autowired
    private SetOperations<String, String> setOperations;
    @Autowired
    private ValueOperations<String, String> valueOperations;
    @Autowired
    private HashOperations<String, String, T> hashOperations;

    public String getHashName() {
        throw new UnsupportedOperationException();
    }

    public void create(T t) {
        String id = String.valueOf(this.generateId());
        t.setId(id);
        String key = this.getHashName() + ":" + t.getId();
        this.addKeyToHashSet(id);
        this.setExpireTime(t, key);
        this.hashOperations.put(key, t.getId(), t);
    }

    public void createOrReplace(T t, Object uniqueField) {
        String id = String.valueOf(uniqueField);
        t.setId(id);
        String key = this.getHashName() + ":" + uniqueField;
        this.addKeyToHashSet(id);
        this.setExpireTime(t, key);
        this.hashOperations.put(key, id, t);
    }

    public void createWithIndex(T t) {
        String id = String.valueOf(this.generateId());
        t.setId(id);
        String key = this.getHashName() + ":" + t.getId();
        this.hashOperations.put(key, t.getId(), t);
        this.addKeyToHashSet(id);
        this.setExpireTime(t, key);
        this.createIndexes(t, t.getId());
    }

    public void createOrReplaceWitIndex(T t, Object uniqueField) {
        String id = String.valueOf(uniqueField);
        t.setId(id);
        String key = this.getHashName() + ":" + uniqueField;
        this.hashOperations.put(key, id, t);
        this.addKeyToHashSet(id);
        this.setExpireTime(t, key);
        this.createIndexes(t, uniqueField);
    }

    public void delete(T t) {
        String key = this.getHashName() + ":" + t.getId();
        this.removeIndexes(t, t.getId());
        this.redisTemplate.delete(key);
        this.removeKeyFromHashSet(t.getId());
    }

    public T getById(Long id) {
        String key = this.getHashName() + ":" + id;
        return hashOperations.entries(key).values().stream().findFirst().orElse(null);
    }

    public T getByField(String fieldName, Object value) {
        String key = this.getHashName() + ":" + fieldName + ":" + value;
        Set<T> intersects = this.redisTemplate.opsForSet().intersect(Collections.singletonList(key));
        List<T> result = new ArrayList<>();
        if (intersects != null && intersects.size() > 0) {
            for (Object o : intersects) {
                Map<String, T> hash = this.hashOperations.entries(this.getHashName() + ":" + o);
                result.add(hash.values().stream().findFirst().orElse(null));
            }
        }
        return result.size() > 0 ? result.get(0) : null;
    }

    public List<T> getAll() {
        String key = this.getHashName();
        Set<T> intersects = this.redisTemplate.opsForSet().intersect(Collections.singletonList(key));
        List<T> result = new ArrayList<>();
        if (intersects != null && intersects.size() > 0) {
            for (Object o : intersects) {
                Map<String, T> hash = this.hashOperations.entries(this.getHashName() + ":" + o);
                result.add(hash.values().stream().findFirst().orElse(null));
            }
        }
        return result.stream().filter(Objects::nonNull).collect(Collectors.toList());
    }

    public List<T> getAllByField(String fieldName, Object value) {
        String key = this.getHashName() + ":" + fieldName + ":" + value;
        Set<T> intersects = this.redisTemplate.opsForSet().intersect(Collections.singletonList(key));
        List<T> result = new ArrayList<>();
        if (intersects != null && intersects.size() > 0) {
            for (Object o : intersects) {
                Map<String, T> hash = this.hashOperations.entries(this.getHashName() + ":" + o);
                result.add(hash.values().stream().findFirst().orElse(null));
            }
        }
        return result.stream().filter(Objects::nonNull).collect(Collectors.toList());
    }

    private void createIndexes(T t, Object uniqueField) {
        try {
            List<Field> indexedFields = this.getIndexedFields(t);
            for (Field field : indexedFields) {
                field.setAccessible(true);
                String key = this.getHashName() + ":" + field.getName() + ":" + field.get(t);
                this.setOperations.add(key, String.valueOf(uniqueField));
//                this.setExpireTime(t, key);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void removeIndexes(T t, Object uniqueField) {
        try {
            List<Field> indexedFields = this.getIndexedFields(t);
            for (Field field : indexedFields) {
                field.setAccessible(true);
                String key = this.getHashName() + ":" + field.getName() + ":" + field.get(t);
                this.setOperations.remove(key, uniqueField);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<Field> getIndexedFields(T t) {
        Field[] fields = t.getClass().getDeclaredFields();
        return Arrays.stream(fields)
                .filter(x -> x.isAnnotationPresent(Indexed.class) || x.isAnnotationPresent(Id.class)).collect(Collectors.toList());
    }

    private Long generateId() {
        String sequence = this.getHashName() + "_SEQUENCE";
        return this.valueOperations.increment(sequence);
    }

    private void setExpireTime(T t, String key) {
        long ttl = t.getClass().getAnnotation(RedisHash.class).timeToLive();
        if (ttl != -1)
            this.redisTemplate.expire(key, ttl, TimeUnit.SECONDS);
    }

    private void addKeyToHashSet(String key) {
        this.setOperations.add(this.getHashName(), key);
    }

    private void removeKeyFromHashSet(String key) {
        this.setOperations.remove(this.getHashName(), key);
    }
}
