package com.salehi.security.authentication.controller;

import com.salehi.security.authentication.dto.jwt.JwtInput;
import com.salehi.security.authentication.dto.jwt.JwtOutput;
import com.salehi.security.authentication.service.AuthService;
import com.salehi.utility.constant.RestControllerConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping(path = RestControllerConstant.PUB + "/auth")
public class PubAuthController {

    @Autowired
    private AuthService authService;

    @PostMapping(path = "/jwt")
    public ResponseEntity<JwtOutput> jwt(@Valid @RequestBody JwtInput input) {
        return ResponseEntity.ok(this.authService.authJwt(input));
    }
}
