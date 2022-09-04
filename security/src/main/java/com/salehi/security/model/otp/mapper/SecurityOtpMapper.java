package com.salehi.security.model.otp.mapper;

import com.salehi.datasource.redis.hash.security.SecurityOtpRedisHash;
import com.salehi.security.model.otp.dto.SecurityOtpInput;
import com.salehi.security.model.otp.dto.SecurityOtpOutput;
import org.mapstruct.Mapper;

/**
 * @author Arash Salehi
 * @author arashsalehi849@yahoo.com
 * @date 2022-09-02 09:10
 * @since 0.0.1
 */
@Mapper(componentModel = "spring")
public interface SecurityOtpMapper {

    SecurityOtpRedisHash mapInputToEntity(SecurityOtpInput input);

    SecurityOtpRedisHash mapOutputToEntity(SecurityOtpOutput output);

    SecurityOtpOutput mapEntityToOutput(SecurityOtpRedisHash entity);

}
