package com.salehi.user.model.user.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UsersInput {
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String mobile;
    @NotBlank
    private String password;
    @NotBlank
    private String accountName;
}
