package com.salehi.security.moduleSpecific.authentication.dto.jwt;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class JwtInput {
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
