package com.salehi.user.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsersInput {
    private String email;
    private String password;
    private String accountName;
}
