package com.salehi.security.moduleSpecific.authentication.dto.jwt;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author Arash Salehi
 * @author arashsalehi849@yahoo.com
 * @since 0.0.1
 */
@Getter
@Setter
public class SecurityJwtInput {
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
