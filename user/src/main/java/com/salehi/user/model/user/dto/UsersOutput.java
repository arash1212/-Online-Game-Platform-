package com.salehi.user.model.user.dto;

import com.salehi.datasource.relational.entity.security.SecurityAuthorityEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.Set;

/**
 * @author Arash Salehi
 * @author arashsalehi849@yahoo.com
 * @since 0.0.1
 */
@Getter
@Setter
public class UsersOutput extends UsersInput {
    private Long id;
    private ZonedDateTime creationTime;
    private String email;
    private String password;
    private Boolean deleted;
    private String accountName;
    private Boolean accountNonExpired;
    private Boolean accountNonLocked;
    private Boolean credentialsNonExpired;
    private Boolean enabled;
    private Set<SecurityAuthorityEntity> authorities;
}
