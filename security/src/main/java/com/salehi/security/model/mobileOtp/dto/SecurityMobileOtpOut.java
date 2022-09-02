package com.salehi.security.model.mobileOtp.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Arash Salehi
 * @author arashsalehi849@yahoo.com
 * @date 2022-09-02 09:05
 * @since 0.0.1
 */
@Getter
@Setter
public class SecurityMobileOtpOut {
    private String id;
    private String username;
    private Integer otp;
}
