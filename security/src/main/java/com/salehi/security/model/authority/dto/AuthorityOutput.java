package com.salehi.security.model.authority.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
public class AuthorityOutput {
    private ZonedDateTime creationTime;
    private String authority;
}
