package com.salehi.security.config.provider;

import com.salehi.datasource.relational.entity.user.UsersEntity;
import com.salehi.security.config.token.JwtAuthenticationToken;
import com.salehi.security.moduleSpecific.authentication.service.SecurityUsersDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * @author Arash Salehi
 * @author arashsalehi849@yahoo.com
 * @since 0.0.1
 */
@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private SecurityUsersDetailService securityUsersDetailService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String requestToken = authentication.getCredentials().toString();
        UsersEntity entity = this.securityUsersDetailService.extractJwtUser(authentication.getCredentials().toString());
        if (entity == null)
            throw new AccessDeniedException("User Not Found");

        return new JwtAuthenticationToken(entity.getEmail(), requestToken, entity.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(JwtAuthenticationToken.class);
    }
}
