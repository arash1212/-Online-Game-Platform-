package com.salehi.utility.utils.interfaces;

public interface IJwtUtils {
    String generateJwt(String email);

    String getEmail(String token);

    void validate(String token);
}
