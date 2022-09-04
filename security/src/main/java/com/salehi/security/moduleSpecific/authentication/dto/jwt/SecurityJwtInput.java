package com.salehi.security.moduleSpecific.authentication.dto.jwt;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "User Email For Authentication", example = "arashsalehi867@yahoo.com")
    private String email;
    @NotBlank
    @Schema(description = "User Password For Authentication", example = "123456")
    private String password;
}
