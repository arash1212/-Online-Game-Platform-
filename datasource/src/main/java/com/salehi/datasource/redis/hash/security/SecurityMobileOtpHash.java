package com.salehi.datasource.redis.hash.security;

import com.salehi.datasource.redis.interfaces.IRedisHash;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;

/**
 * @author Arash Salehi
 * @author arashsalehi849@yahoo.com
 * @date 2022-09-01 23:40
 * @since 0.0.1
 */
@Getter
@Setter
@RedisHash(value = "SECURITY_MOBILE_OTP", timeToLive = 60)
public class SecurityMobileOtpHash implements IRedisHash, Serializable {
    @Id
    private String id;
    @Indexed
    private String username;
    @Indexed
    private String otp;
}
