package com.salehi.application.testing.redis.testRedis.service;

import com.salehi.application.testing.redis.testRedis.dto.TestRedisInput;
import com.salehi.application.testing.redis.testRedis.dto.TestRedisOutPut;
import com.salehi.application.testing.redis.testRedis.mapper.TestRedisHashMapper;
import com.salehi.application.testing.redis.testRedis.repository.TestRedisRepository;
import com.salehi.datasource.redis.hash.TestRedisHash;
import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Arash Salehi
 * @author arashsalehi849@yahoo.com
 * @since 0.0.1
 */
@Service
public class TestRedisService {

    private final TestRedisRepository testRepository;
    private final TestRedisHashMapper testMapper;

    public TestRedisService(TestRedisRepository testRepository, TestRedisHashMapper testMapper) {
        this.testRepository = testRepository;
        this.testMapper = testMapper;
    }

    public void create(TestRedisInput input) {
        this.testRepository.createWithIndex(this.testMapper.mapInputToEntity(input));
    }

    public TestRedisHash findById(String id) {
        TestRedisHash hash = this.testRepository.getById(id);
        if (hash == null)
            throw new OpenApiResourceNotFoundException("RedisTest ID : " + id);

        return this.testRepository.getById(id);
    }

    public List<TestRedisOutPut> findByName(String value) {
        List<TestRedisHash> hashes = this.testRepository.getAllByField("name", value);
        return hashes.stream().map(this.testMapper::mapEntityToOutput).collect(Collectors.toList());
    }

    public void delete(String id) {
        TestRedisHash hash = this.testRepository.getById(id);
        if (hash == null)
            throw new OpenApiResourceNotFoundException("RedisTest ID : " + id);

        this.testRepository.delete(hash);
    }
}
