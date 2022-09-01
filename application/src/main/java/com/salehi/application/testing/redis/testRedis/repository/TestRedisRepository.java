package com.salehi.application.testing.redis.testRedis.repository;

import com.salehi.datasource.redis.hash.TestRedisHash;
import com.salehi.datasource.redis.repository.RedisRepository;
import org.springframework.stereotype.Repository;

@Repository
public class TestRedisRepository extends RedisRepository<TestRedisHash> {
    @Override
    public String getHashName() {
        return "TEST_REDIS_HASH";
    }
}
