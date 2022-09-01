package com.salehi.datasource.redis.config;

import com.salehi.datasource.redis.interfaces.IRedisHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.RedisSerializer;

@Configuration
public class ConfigRedis {
    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }

    @Bean
    public RedisTemplate redisTemplate() {
        RedisTemplate template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setValueSerializer(RedisSerializer.json());
        template.setHashKeySerializer(RedisSerializer.string());
        template.setHashKeySerializer(RedisSerializer.json());
        template.setKeySerializer(RedisSerializer.string());
        return template;
    }

    @Bean
    public HashOperations hashOperations(){
        return this.redisTemplate().opsForHash();
    }

    @Bean
    public SetOperations setOperations(){
        return this.redisTemplate().opsForSet();
    }
}
