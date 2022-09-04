package com.salehi.security.model.mobileOtp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * @author Arash Salehi
 * @author arashsalehi849@yahoo.com
 * @date 2022-09-02 09:05
 * @since 0.0.1
 */
@Getter
@Setter
public class SecurityMobileOtpInput {
    @NotBlank
    @Schema(example = "arashsalehi867@yahoo.com")
    private String username;
    @NotBlank
    @Schema(example = "1234")
    private Integer otp;
}
