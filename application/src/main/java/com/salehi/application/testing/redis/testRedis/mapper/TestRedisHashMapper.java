package com.salehi.application.testing.redis.testRedis.mapper;


import com.salehi.application.testing.redis.testRedis.dto.TestRedisInput;
import com.salehi.application.testing.redis.testRedis.dto.TestRedisOutPut;
import com.salehi.datasource.redis.hash.TestRedisHash;
import org.mapstruct.Mapper;

/**
 * @author Arash Salehi
 * @author arashsalehi849@yahoo.com
 * @since 0.0.1
 */
@Mapper(componentModel = "spring")
public interface TestRedisHashMapper {

    TestRedisHash mapInputToEntity(TestRedisInput input);

    TestRedisHash mapOutputToEntity(TestRedisOutPut output);

    TestRedisOutPut mapEntityToOutput(TestRedisHash entity);

}
