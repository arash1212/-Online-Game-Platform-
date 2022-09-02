package com.salehi.security.model.mobileOtp.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Arash Salehi
 * @author arashsalehi849@yahoo.com
 * @date 2022-09-02 09:05
 * @since 0.0.1
 */
@Getter
@Setter
public class SecurityMobileOtpInput {
    @NotNull
    private String id;
    @NotBlank
    private String username;
    @NotBlank
    private Integer otp;
}
