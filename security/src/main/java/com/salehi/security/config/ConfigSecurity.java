package com.salehi.security.config;

import com.salehi.security.config.filter.JwtAuthenticationProcessingFilter;
import com.salehi.security.config.handler.JwtAuthenticationFailureHandler;
import com.salehi.security.config.handler.JwtAuthenticationSuccessHandler;
import com.salehi.utility.constant.RestControllerConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * @author Arash Salehi
 * @author arashsalehi849@yahoo.com
 * @since 0.0.1
 */
@Configuration
public class ConfigSecurity {

    @Autowired
    private AuthenticationConfiguration configuration;
    @Autowired
    private JwtAuthenticationSuccessHandler jwtAuthenticationSuccessHandler;
    @Autowired
    private JwtAuthenticationFailureHandler jwtAuthenticationFailureHandler;

    @Bean
    public JwtAuthenticationProcessingFilter jwtFilter() throws Exception {
        JwtAuthenticationProcessingFilter jwtFilter = new JwtAuthenticationProcessingFilter(new AntPathRequestMatcher("/api/adm/***"));
        jwtFilter.setAuthenticationManager(this.authenticationManager());
        jwtFilter.setAuthenticationSuccessHandler(this.jwtAuthenticationSuccessHandler);
        jwtFilter.setAuthenticationFailureHandler(this.jwtAuthenticationFailureHandler);
        return jwtFilter;
    }

    //TODO
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests().antMatchers(RestControllerConstant.PUB + "/**").permitAll().and()
                .authorizeRequests().antMatchers(RestControllerConstant.ADM + "/**").authenticated().and()
                .authorizeRequests().antMatchers(RestControllerConstant.SWAGGER_DOC + "/**").permitAll().and()
                .authorizeRequests().anyRequest().authenticated();

        http.addFilterBefore(this.jwtFilter(), UsernamePasswordAuthenticationFilter.class);
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }

    @Bean
    AuthenticationManager authenticationManager() throws Exception {
        return configuration.getAuthenticationManager();
    }
}
