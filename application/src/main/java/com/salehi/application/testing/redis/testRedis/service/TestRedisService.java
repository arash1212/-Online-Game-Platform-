package com.salehi.application.testing.redis.testRedis.service;

import com.salehi.application.testing.redis.testRedis.dto.TestRedisInput;
import com.salehi.application.testing.redis.testRedis.mapper.TestRedisHashMapper;
import com.salehi.application.testing.redis.testRedis.repository.TestRedisRepository;
import com.salehi.datasource.redis.hash.TestRedisHash;
import org.springframework.stereotype.Service;

@Service
public class TestRedisService {

    private final TestRedisRepository repository;
    private final TestRedisHashMapper mapper;

    public TestRedisService(TestRedisRepository repository, TestRedisHashMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public void create(TestRedisInput input) {
        repository.createWithIndices(this.mapper.mapInputToEntity(input));
    }

    public TestRedisHash findById(String id) {
        return repository.getById(id);
    }
}
