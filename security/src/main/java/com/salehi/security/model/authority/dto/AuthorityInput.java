package com.salehi.security.model.authority.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class AuthorityInput {
    @NotBlank
    @Size(max = 100)
    private String authority;
}
