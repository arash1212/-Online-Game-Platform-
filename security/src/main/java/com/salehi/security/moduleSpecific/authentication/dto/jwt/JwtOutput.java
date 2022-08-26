package com.salehi.security.moduleSpecific.authentication.dto.jwt;

import lombok.Getter;

@Getter
public class JwtOutput {
    String token;

    public JwtOutput(String token) {
        this.token = token;
    }
}
