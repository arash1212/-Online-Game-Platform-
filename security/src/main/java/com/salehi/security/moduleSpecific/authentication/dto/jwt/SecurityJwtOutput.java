package com.salehi.security.moduleSpecific.authentication.dto.jwt;

import lombok.Getter;

/**
 * @author Arash Salehi
 * @author arashsalehi849@yahoo.com
 * @since 0.0.1
 */
@Getter
public class SecurityJwtOutput {
    String token;

    public SecurityJwtOutput(String token) {
        this.token = token;
    }
}
