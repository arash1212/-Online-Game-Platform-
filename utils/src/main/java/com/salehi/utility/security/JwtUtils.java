package com.salehi.utility.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtils {

    @Value("${JwtSecret}")
    private String JWT_SECRET;
    private final int TOKEN_EXPIRATION = 60 * 60 * 60;

    public String generateJwt(String email) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", email);
        return Jwts.builder().signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION * 1000))
                .setClaims(claims)
                .compact();
    }

    public String getEmail(String token) {
        Claims claims = Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody();
        return claims.get("email").toString();
    }

    //TODO jwt validation
}
