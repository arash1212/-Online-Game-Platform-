package com.salehi.security.moduleSpecific.authentication.controller;

import com.salehi.security.moduleSpecific.authentication.dto.jwt.JwtInput;
import com.salehi.security.moduleSpecific.authentication.dto.jwt.JwtOutput;
import com.salehi.security.moduleSpecific.authentication.service.AuthService;
import com.salehi.utility.constant.RestControllerConstant;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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


@Tag(name = "Pub-Auth", description = "Authenticating in different methods")
@Validated
@RestController
@RequestMapping(path = RestControllerConstant.PUB + "/auth")
public class PubAuthController {

    @Autowired
    private AuthService authService;

    @Operation(summary = "Jwt Authentication")
    @PostMapping(path = "/jwt", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JwtOutput> jwt(@Valid
                                         @io.swagger.v3.oas.annotations.parameters.RequestBody(
                                                 content = @Content(
                                                         schema = @Schema(example =
                                                                 "{\n" +
                                                                         "  \"email\": \"arashsalehi867@yahoo.com\",\n" +
                                                                         "  \"password\": \"123456\"\n" +
                                                                         "}"))
                                         ) @RequestBody JwtInput input) {
        return ResponseEntity.ok(this.authService.authJwt(input));
    }
}
