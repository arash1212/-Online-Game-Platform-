package com.salehi.application.testing.redis.testRedis.controller;

import com.salehi.application.testing.redis.testRedis.dto.TestRedisInput;
import com.salehi.application.testing.redis.testRedis.dto.TestRedisOutPut;
import com.salehi.application.testing.redis.testRedis.service.TestRedisService;
import com.salehi.datasource.redis.hash.TestRedisHash;
import com.salehi.utility.constant.PathVariableConstant;
import com.salehi.utility.constant.RestControllerConstant;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Pub-Redis-Test")
@Validated
@RestController
@RequestMapping(path = RestControllerConstant.PUB + "/redisTest")
public class PubTestRedisController {

    private final TestRedisService testRedisService;

    @Autowired
    public PubTestRedisController(TestRedisService testRedisService) {
        this.testRedisService = testRedisService;
    }

    @Operation(summary = "Create new RedisTest")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Resource Created Successfully"),
    })
    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(
            @Valid @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(schema = @Schema(
                            example = "{\n" +
                                    "  \"id\": \"1\",\n" +
                                    "  \"name\": \"arash\",\n" +
                                    "  \"score\": 15\n" +
                                    "}"
                    ))
            )
            @RequestBody TestRedisInput input, BindingResult result) {
        this.testRedisService.create(input);
    }

    @Operation(summary = "Get RedisTests by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Resource Found"),
            @ApiResponse(responseCode = "403", description = "Access Denied", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Resource not found", content = @Content(schema = @Schema()))})
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TestRedisHash> user(@PathVariable(name = PathVariableConstant.ID) String id) {
        return ResponseEntity.ok(this.testRedisService.findById(id));
    }

    @Operation(summary = "Get All RedisTests by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Resource Found"),
            @ApiResponse(responseCode = "404", description = "Resource not found", content = @Content(schema = @Schema()))})
    @GetMapping(path = "/getByName", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TestRedisOutPut>> restTestByName(@RequestParam(name = "name") String name) {
        return ResponseEntity.ok(this.testRedisService.findByName(name));
    }

    @Operation(summary = "Delete RedisTest by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Resource deleted successfully"),
            @ApiResponse(responseCode = "403", description = "Access Denied", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Resource not found", content = @Content(schema = @Schema()))})
    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable(name = PathVariableConstant.ID) String id) {
        this.testRedisService.delete(id);
    }
}
