package com.salehi.security.moduleSpecific.authentication.controller;

import com.salehi.security.moduleSpecific.authentication.dto.jwt.SecurityJwtInput;
import com.salehi.security.moduleSpecific.authentication.dto.jwt.SecurityJwtOutput;
import com.salehi.security.moduleSpecific.authentication.service.SecurityAuthenticationService;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Arash Salehi
 * @author arashsalehi849@yahoo.com
 * @since 0.0.1
 */
@Tag(name = "Pub-Security-Authentication")
@Validated
@RestController
@RequestMapping(path = RestControllerConstant.PUB + "/auth")
public class PubSecurityAuthenticationController {

    @Autowired
    private SecurityAuthenticationService securityAuthenticationService;

    @Operation(summary = "Jwt Authentication")
    @PostMapping(path = "/jwt", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SecurityJwtOutput> jwt(@Valid @RequestBody SecurityJwtInput input) {
        return ResponseEntity.ok(this.securityAuthenticationService.authJwt(input));
    }

    @Operation(summary = "Request Otp")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Resource Created Successfully"),
            @ApiResponse(responseCode = "403", description = "Access Denied", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Resource not found", content = @Content(schema = @Schema()))
    })
    @PostMapping(path = "/request-otp")
    public ResponseEntity<String> requestOtp() {
        this.securityAuthenticationService.authOtp();
        return ResponseEntity.ok("test");
    }
}
