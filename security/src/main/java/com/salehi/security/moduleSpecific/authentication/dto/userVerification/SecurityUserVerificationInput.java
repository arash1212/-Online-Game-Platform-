package com.salehi.security.moduleSpecific.authentication.dto.userVerification;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @author Arash Salehi
 * @author arashsalehi849@yahoo.com
 * @date 2022-09-02 10:52
 * @since 0.0.1
 */
@Getter
@Setter
public class SecurityUserVerificationInput {
    @NotNull
    @Schema(description = "Input Generated Otp Value", example = "1234")
    private Integer otp;
}
