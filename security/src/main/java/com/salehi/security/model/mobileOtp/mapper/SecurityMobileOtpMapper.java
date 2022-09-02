package com.salehi.security.model.mobileOtp.mapper;

import com.salehi.datasource.redis.hash.security.SecurityMobileOtpHash;
import com.salehi.security.model.mobileOtp.dto.SecurityMobileOtpInput;
import com.salehi.security.model.mobileOtp.dto.SecurityMobileOtpOut;
import org.mapstruct.Mapper;

/**
 * @author Arash Salehi
 * @author arashsalehi849@yahoo.com
 * @date 2022-09-02 09:10
 * @since 0.0.1
 */
@Mapper(componentModel = "spring")
public interface SecurityMobileOtpMapper {

    SecurityMobileOtpHash mapInputToEntity(SecurityMobileOtpInput input);

    SecurityMobileOtpHash mapOutputToEntity(SecurityMobileOtpOut output);

    SecurityMobileOtpOut mapEntityToOutput(SecurityMobileOtpHash entity);

}
