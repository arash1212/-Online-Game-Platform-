package com.salehi.security.config.token;

import lombok.Getter;
import org.springframework.security.authentication.AbstractAuthenticationToken;

@Getter
public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private final String principal;

    public JwtAuthenticationToken(String token) {
        super(null);
        this.principal = token;
    }

    @Override
    public Object getCredentials() {
        return null;
    }
}
