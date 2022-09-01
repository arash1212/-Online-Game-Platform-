package com.salehi.security.model.authority.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

/**
 * @author Arash Salehi
 * @author arashsalehi849@yahoo.com
 * @since 0.0.1
 */
@Getter
@Setter
public class SecurityAuthorityOutput {
    private ZonedDateTime creationTime;
    private String authority;
}
