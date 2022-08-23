package com.salehi.security.model.authority.controller;

import com.salehi.security.model.authority.dto.AuthorityInput;
import com.salehi.security.model.authority.dto.AuthorityOutput;
import com.salehi.security.model.authority.service.AuthorityService;
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

@Tag(name = "Adm-Authority")
@Validated
@RestController
@RequestMapping(path = RestControllerConstant.ADM + "/authorities")
public class AdmAuthorityController {

    @Autowired
    private AuthorityService authorityService;

    @Operation(summary = "Get authority by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Resource Found"),
            @ApiResponse(responseCode = "403", description = "Access Denied", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Resource not found", content = @Content(schema = @Schema()))})
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthorityOutput> user(@PathVariable(name = PathVariableConstant.ID) Long id) {
        return ResponseEntity.ok(this.authorityService.findById(id));
    }

    @Operation(summary = "Get authorities list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully Loaded Resources"),
            @ApiResponse(responseCode = "403", description = "Access Denied", content = @Content(schema = @Schema()))
    })
    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AuthorityOutput>> users() {
        return ResponseEntity.ok(this.authorityService.findAll());
    }

    @Operation(summary = "Create new authority")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Resource Created Successfully"),
            @ApiResponse(responseCode = "403", description = "Access Denied", content = @Content(schema = @Schema())),
    })
    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> create(
            @Valid @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(schema = @Schema(example =
                            "{" +
                                    "  \"authority\": \"user\"\n" +
                                    "}"))
            )
            @RequestBody AuthorityInput input, BindingResult result) {
        return ResponseEntity.ok(this.authorityService.save(input));
    }

    @Operation(summary = "Update authority by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Resource updated successfully"),
            @ApiResponse(responseCode = "403", description = "Access Denied", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Resource not found", content = @Content(schema = @Schema()))})
    @PutMapping(path = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@PathVariable(name = PathVariableConstant.ID) Long id,
                       @Valid @io.swagger.v3.oas.annotations.parameters.RequestBody(
                               content = @Content(schema = @Schema(example =
                                       "{" +
                                               "  \"authority\": \"admin\"\n" +
                                               "}"))
                       )
                       @RequestBody AuthorityInput input, BindingResult result) {
        this.authorityService.update(id, input);
    }

    @Operation(summary = "Delete Authority by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Resource deleted successfully"),
            @ApiResponse(responseCode = "403", description = "Access Denied", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Resource not found", content = @Content(schema = @Schema()))})
    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable(name = PathVariableConstant.ID) Long id) {
        this.authorityService.delete(id);
    }

}
