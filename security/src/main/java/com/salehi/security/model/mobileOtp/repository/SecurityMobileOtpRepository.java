package com.salehi.security.model.mobileOtp.repository;

import com.salehi.datasource.redis.hash.security.SecurityMobileOtpHash;
import com.salehi.datasource.redis.repository.RedisRepositoryImpl;
import org.springframework.stereotype.Repository;

/**
 * @author Arash Salehi
 * @author arashsalehi849@yahoo.com
 * @date 2022-09-02 09:13
 * @since 0.0.1
 */
@Repository
public class SecurityMobileOtpRepository extends RedisRepositoryImpl<SecurityMobileOtpHash> {
    @Override
    public String getHashName() {
        return "SECURITY_MOBILE_OTP";
    }
}
