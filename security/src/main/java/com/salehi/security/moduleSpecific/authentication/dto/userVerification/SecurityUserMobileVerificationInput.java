package com.salehi.security.moduleSpecific.authentication.dto.userVerification;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Arash Salehi
 * @author arashsalehi849@yahoo.com
 * @date 2022-09-02 10:52
 * @since 0.0.1
 */
@Getter
@Setter
public class SecurityUserMobileVerificationInput {
    @NotNull
    private Integer otp;
}
