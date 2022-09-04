package com.salehi.application.testing.redis.testRedis.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Arash Salehi
 * @author arashsalehi849@yahoo.com
 * @since 0.0.1
 */
@Getter
@Setter
public class TestRedisInput {
    @NotBlank
    @Schema(example = "arash")
    private String name;
    @NotNull
    @Schema(example = "10")
    private Integer score;
}
