package com.salehi.security.config.filter;

import com.salehi.security.config.token.JwtAuthenticationToken;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {

    public JwtAuthenticationProcessingFilter(RequestMatcher requestMatcher) {
        super(new AntPathRequestMatcher("/api/adm/**"));
    }

    @Override
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String token = request.getHeader("token");
        if (token == null || StringUtils.isBlank(token))
            throw new AccessDeniedException("Token header not found");


        JwtAuthenticationToken jwtAuthenticationToken = new JwtAuthenticationToken(token);
        return this.getAuthenticationManager().authenticate(jwtAuthenticationToken);
    }

}
