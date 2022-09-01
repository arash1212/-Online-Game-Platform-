package com.salehi.application.testing.redis.testRedis.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class TestRedisInput {
    @NotNull
    private String id;
    @NotBlank
    private String name;
    @NotNull
    private Integer score;
}
