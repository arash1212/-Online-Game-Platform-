package com.salehi.application.testing.redis.testRedis.repository;

import com.salehi.datasource.redis.hash.TestRedisHash;
import com.salehi.datasource.redis.repository.RedisRepositoryImpl;
import org.springframework.stereotype.Repository;

/**
 * @author Arash Salehi
 * @author arashsalehi849@yahoo.com
 * @since 0.0.1
 */
@Repository
public class TestRedisRepository extends RedisRepositoryImpl<TestRedisHash> {
    @Override
    public String getHashName() {
        return "TEST_REDIS_HASH";
    }
}
