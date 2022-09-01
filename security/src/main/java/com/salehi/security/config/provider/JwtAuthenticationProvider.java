package com.salehi.security.config.provider;

import com.salehi.datasource.relational.entity.user.UsersEntity;
import com.salehi.security.config.token.JwtAuthenticationToken;
import com.salehi.security.moduleSpecific.authentication.service.UsersDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
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
        String requestToken = authentication.getCredentials().toString();
        UsersEntity entity = this.usersDetailService.extractJwtUser(authentication.getCredentials().toString());
        if (entity == null)
            throw new AccessDeniedException("User Not Found");

        return new JwtAuthenticationToken(entity.getEmail(), requestToken, entity.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(JwtAuthenticationToken.class);
    }
}
