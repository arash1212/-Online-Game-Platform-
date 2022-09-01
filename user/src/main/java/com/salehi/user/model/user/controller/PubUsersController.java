package com.salehi.user.model.user.controller;

import com.salehi.user.model.user.dto.UsersInput;
import com.salehi.user.model.user.dto.UsersOutput;
import com.salehi.user.model.user.service.UsersService;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Arash Salehi
 * @author arashsalehi849@yahoo.com
 * @since 0.0.1
 */
@Tag(name = "Pub-User")
@Validated
@RestController
@RequestMapping(path = RestControllerConstant.PUB + "/users")
public class PubUsersController {

    @Autowired
    private UsersService usersService;

    @Operation(summary = "Get user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Resource Found"),
            @ApiResponse(responseCode = "403", description = "Access Denied", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Resource not found", content = @Content(schema = @Schema()))})
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsersOutput> user(@PathVariable(name = PathVariableConstant.ID) Long id) {
        return ResponseEntity.ok(this.usersService.findById(id));
    }

    @Operation(summary = "Get users list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully Loaded Resources"),
            @ApiResponse(responseCode = "403", description = "Access Denied", content = @Content(schema = @Schema())),
    })
    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UsersOutput>> users() {
        return ResponseEntity.ok(this.usersService.findAll());
    }

    @Operation(summary = "Create new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Resource Created Successfully"),
            @ApiResponse(responseCode = "403", description = "Access Denied", content = @Content(schema = @Schema())),
    })
    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> create(
            @Valid @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(schema = @Schema(example = "{\n" +
                            "  \"email\": \"arashsalehi867@yahoo.com\",\n" +
                            "  \"mobile\": \"09001112222\",\n" +
                            "  \"password\": \"123456\",\n" +
                            "  \"accountName\": \"arash111\"\n" +
                            "}"))
            )
            @RequestBody UsersInput input) {
        return ResponseEntity.ok(this.usersService.save(input));
    }

    @Operation(summary = "Update user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Resource updated successfully"),
            @ApiResponse(responseCode = "403", description = "Access Denied", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Resource not found", content = @Content(schema = @Schema()))})
    @PutMapping(path = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@PathVariable(name = PathVariableConstant.ID) Long id,
                       @Valid @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(
                               schema = @Schema(example = "{\n" +
                                       "  \"email\": \"arashsalehi867@yahoo.com\",\n" +
                                       "  \"mobile\": \"09001234321\",\n" +
                                       "  \"password\": \"654321\",\n" +
                                       "  \"accountName\": \"arash321\"\n" +
                                       "}")
                       ))
                       @RequestBody UsersInput input) {
        this.usersService.update(id, input);
    }

    @Operation(summary = "Delete user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Resource deleted successfully"),
            @ApiResponse(responseCode = "403", description = "Access Denied", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Resource not found", content = @Content(schema = @Schema()))})
    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable(name = PathVariableConstant.ID) Long id) {
        this.usersService.delete(id);
    }

}
