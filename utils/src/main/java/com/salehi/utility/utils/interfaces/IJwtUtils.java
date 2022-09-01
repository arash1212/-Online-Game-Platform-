package com.salehi.utility.utils.interfaces;

/**
 * @author Arash Salehi
 * @author arashsalehi849@yahoo.com
 * @since 0.0.1
 */
public interface IJwtUtils {
    String generateJwt(String email);

    String getEmail(String token);

    void validate(String token);
}
