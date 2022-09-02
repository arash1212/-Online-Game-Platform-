package com.salehi.security.model.mobileOtp.dto;

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
    private String username;
    @NotBlank
    private Integer otp;
}
