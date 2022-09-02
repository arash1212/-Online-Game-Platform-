package com.salehi.datasource.redis.repository;

import com.salehi.datasource.redis.interfaces.IRedisHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.index.Indexed;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.util.*;
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
    private SetOperations<String, Long> setOperations;
    @Autowired
    private ValueOperations<String, Long> valueOperations;
    @Autowired
    private HashOperations<String, Long, T> hashOperations;

    public String getHashName() {
        throw new UnsupportedOperationException();
    }

    public void create(T t) {
        t.setId(this.generateId());
        String key = this.getHashName() + ":" + t.getId();
        this.hashOperations.put(key, t.getId(), t);
    }

    public void createWithIndex(T t) {
        t.setId(this.generateId());
        String key = this.getHashName() + ":" + t.getId();
        this.hashOperations.put(key, t.getId(), t);
        this.createIndexes(t);
    }

//    public void createWithTtl(T t, Long ttl) {
//        this.hashOperations.put(this.getHashName() + ":" + t.getId(), this.getHashName(), t);
//        this.redisTemplate.expire(t.getId(), ttl, TimeUnit.SECONDS);
//    }

    public void delete(T t) {
        String key = this.getHashName() + ":" + t.getId();
        this.removeIndexes(t);
        this.redisTemplate.delete(key);
    }

    public T getById(Long id) {
        String key = this.getHashName() + ":" + id;
        return hashOperations.entries(key).values().stream().findFirst().orElse(null);
    }

    //TODO bayad unique bashe meghdar (?)
    public T getByField(String fieldName, Object value) {
        String key = this.getHashName() + ":" + fieldName + ":" + value;
        Set<T> intersects = this.redisTemplate.opsForSet().intersect(Collections.singletonList(key));
        List<T> result = new ArrayList<>();
        if (intersects != null && intersects.size() > 0) {
            for (Object o : intersects) {
                Map<Long, T> hash = this.hashOperations.entries(this.getHashName() + ":" + o);
                result.add(hash.values().stream().findFirst().orElse(null));
            }
        }
        return result.get(0) != null ? result.get(0) : null;
    }

    public List<T> getAllByField(String fieldName, Object value) {
        String key = this.getHashName() + ":" + fieldName + ":" + value;
        Set<T> intersects = this.redisTemplate.opsForSet().intersect(Collections.singletonList(key));
        List<T> result = new ArrayList<>();
        if (intersects != null && intersects.size() > 0) {
            for (Object o : intersects) {
                Map<Long, T> hash = this.hashOperations.entries(this.getHashName() + ":" + o);
                result.add(hash.values().stream().findFirst().orElse(null));
            }
        }
        return result;
    }

    private void createIndexes(T t) {
        try {
            List<Field> indexedFields = this.getIndexedFields(t);
            for (Field field : indexedFields) {
                field.setAccessible(true);
                String key = this.getHashName() + ":" + field.getName() + ":" + field.get(t);
                this.setOperations.add(key, t.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void removeIndexes(T t) {
        try {
            List<Field> indexedFields = this.getIndexedFields(t);
            for (Field field : indexedFields) {
                field.setAccessible(true);
                String key = this.getHashName() + ":" + field.getName() + ":" + field.get(t);
                this.setOperations.remove(key, t.getId());
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
}