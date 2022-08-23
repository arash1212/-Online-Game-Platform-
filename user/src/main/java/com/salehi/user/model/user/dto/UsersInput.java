package com.salehi.user.model.user.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UsersInput {
    @Email
    @NotBlank
    @Size(max = 150)
    private String email;
    @NotBlank
    @Size(min = 11, max = 30)
    private String mobile;
    @NotBlank
    @Size(min = 6, max = 100)
    private String password;
    @NotBlank
    @Size(min = 4, max = 150)
    private String accountName;
}
