package com.salehi.security.config.provider;

import com.salehi.datasource.relational.entity.user.UsersEntity;
import com.salehi.security.authentication.service.AuthService;
import com.salehi.security.config.token.JwtAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private AuthService authService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsersEntity entity = this.authService.extractUser(authentication.getPrincipal().toString());
        return new JwtAuthenticationToken(authentication.getPrincipal().toString());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(JwtAuthenticationToken.class);
    }
}
