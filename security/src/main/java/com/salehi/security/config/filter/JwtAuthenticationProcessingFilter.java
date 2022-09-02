package com.salehi.security.config.filter;

import com.salehi.security.config.token.JwtAuthenticationToken;
import com.salehi.utility.constant.RestControllerConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Arash Salehi
 * @author arashsalehi849@yahoo.com
 * @since 0.0.1
 */
public class JwtAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {

    public final static RequestMatcher REQUEST_MATCHER = new OrRequestMatcher(
            new AntPathRequestMatcher(RestControllerConstant.ADM + "/**"),
            new AntPathRequestMatcher(RestControllerConstant.IDT + "/**")
    );

    public JwtAuthenticationProcessingFilter(RequestMatcher requestMatcher) {
        super(REQUEST_MATCHER);
    }

    @Override
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String token = request.getHeader("Bearer");
        if (token == null || StringUtils.isBlank(token))
            throw new AccessDeniedException("Token/Bearer header not found");

        JwtAuthenticationToken jwtAuthenticationToken = new JwtAuthenticationToken(null, token.replaceAll("Bearer", ""), null);
        return this.getAuthenticationManager().authenticate(jwtAuthenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        SecurityContextHolder.getContext().setAuthentication(authResult);
        chain.doFilter(request, response);
    }
}
