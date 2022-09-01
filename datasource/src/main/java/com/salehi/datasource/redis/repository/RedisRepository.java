package com.salehi.datasource.redis.repository;

import com.salehi.datasource.redis.interfaces.IRedisHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.index.Indexed;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Repository
public class RedisRepository<T extends IRedisHash> {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public String getHashName() {
        throw new UnsupportedOperationException();
    }

    public void create(T t) {
        this.redisTemplate.opsForHash().put(t.getId(), this.getHashName(), t);
    }

    public void createWithIndices(T t) {
        this.redisTemplate.opsForHash().put(t.getId(), this.getHashName(), t);
        this.createIndices(t);
    }

    public void createWithTtl(T t, Long ttl) {
        this.redisTemplate.opsForHash().put(t.getId(), this.getHashName(), t);
        this.redisTemplate.expire(t.getId(), ttl, TimeUnit.SECONDS);
    }

    public void delete(T t) {
        this.redisTemplate.opsForHash().delete(t.getId(), this.getHashName());
    }

    public T getById(String id) {
        return (T) this.redisTemplate.opsForHash().get(id, this.getHashName());
    }


    private void createIndices(T t) {
        try {
            List<Field> indexedFields = this.getIndexedFields(t);
            for (Field field : indexedFields) {
                field.setAccessible(true);
                String key = this.getHashName() + ":" + field.getName() + ":" + field.get(t);
                this.redisTemplate.opsForSet().add(key, t.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<Field> getIndexedFields(T t) {
        Field[] fields = t.getClass().getDeclaredFields();
        return Arrays.stream(fields).filter(x -> x.getAnnotation(Indexed.class) != null).collect(Collectors.toList());
    }
}
