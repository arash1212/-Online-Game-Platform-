package com.salehi.datasource.redis.hash;

import com.salehi.datasource.redis.interfaces.IRedisHash;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@Setter
@RedisHash(value = "TestRedis")
public class TestRedisHash implements IRedisHash {
    @Id
    private String id;
    @Indexed
    private String name;
    @Indexed
    private int score;
}
