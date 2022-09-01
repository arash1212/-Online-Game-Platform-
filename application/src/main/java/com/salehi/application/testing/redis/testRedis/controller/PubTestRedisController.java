package com.salehi.application.testing.redis.testRedis.controller;

import com.salehi.application.testing.redis.testRedis.dto.TestRedisInput;
import com.salehi.application.testing.redis.testRedis.service.TestRedisService;
import com.salehi.utility.constant.RestControllerConstant;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Tag(name = "Pub-Redis-Test")
@Validated
@RestController
@RequestMapping(path = RestControllerConstant.PUB + "/messaging")
public class PubTestRedisController {

    @Autowired
    private TestRedisService testRedisService;

    @Operation(summary = "Create new RedisTestHash")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Resource Created Successfully"),
    })
    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(
            @Valid @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(schema = @Schema(
                            example ="{\n" +
                                    "  \"id\": \"1\",\n" +
                                    "  \"name\": \"arash\",\n" +
                                    "  \"score\": 15\n" +
                                    "}"
                    ))
            )
            @RequestBody TestRedisInput input, BindingResult result) {
        this.testRedisService.create(input);
    }
}
