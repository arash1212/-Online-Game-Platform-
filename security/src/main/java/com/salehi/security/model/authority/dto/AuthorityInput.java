package com.salehi.security.model.authority.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class AuthorityInput {
    @NotBlank
    private String authority;
}
