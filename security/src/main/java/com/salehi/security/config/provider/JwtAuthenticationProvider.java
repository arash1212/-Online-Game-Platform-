package com.salehi.security.config.provider;

import com.salehi.datasource.relational.entity.user.UsersEntity;
import com.salehi.security.authentication.service.UsersDetailService;
import com.salehi.security.config.token.JwtAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UsersDetailService usersDetailService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsersEntity entity = this.usersDetailService.extractJwtUser(authentication.getPrincipal().toString());
        return new JwtAuthenticationToken(authentication.getPrincipal().toString());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(JwtAuthenticationToken.class);
    }
}
