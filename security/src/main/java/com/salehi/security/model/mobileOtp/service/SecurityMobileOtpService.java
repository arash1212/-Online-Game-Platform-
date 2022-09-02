package com.salehi.security.model.mobileOtp.service;

import com.salehi.datasource.redis.hash.security.SecurityMobileOtpHash;
import com.salehi.security.model.mobileOtp.dto.SecurityMobileOtpInput;
import com.salehi.security.model.mobileOtp.dto.SecurityMobileOtpOut;
import com.salehi.security.model.mobileOtp.mapper.SecurityMobileOtpMapper;
import com.salehi.security.model.mobileOtp.repository.SecurityMobileOtpRepository;
import com.salehi.utility.utils.interfaces.ISecurityUtils;
import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Arash Salehi
 * @author arashsalehi849@yahoo.com
 * @date 2022-09-02 09:20
 * @since 0.0.1
 */
@Service
public class SecurityMobileOtpService {

    private final SecurityMobileOtpRepository otpRepository;
    private final SecurityMobileOtpMapper otpMapper;
    private final ISecurityUtils securityUtils;

    @Autowired
    public SecurityMobileOtpService(SecurityMobileOtpRepository otpRepository, SecurityMobileOtpMapper otpMapper, ISecurityUtils securityUtils) {
        this.otpRepository = otpRepository;
        this.otpMapper = otpMapper;
        this.securityUtils = securityUtils;
    }

    public void create(SecurityMobileOtpInput input) {
        this.otpRepository.createWithIndex(this.otpMapper.mapInputToEntity(input));
    }

    public SecurityMobileOtpHash findById(Long id) {
        SecurityMobileOtpHash hash = this.otpRepository.getById(id);
        if (hash == null)
            throw new OpenApiResourceNotFoundException("RedisTest ID : " + id);

        return this.otpRepository.getById(id);
    }

    public List<SecurityMobileOtpOut> findByUsername(String username) {
        List<SecurityMobileOtpHash> hashes = this.otpRepository.getAllByField("name", username);
        return hashes.stream().map(this.otpMapper::mapEntityToOutput).collect(Collectors.toList());
    }

    public SecurityMobileOtpOut findByOtp(int otp) {
        SecurityMobileOtpHash hash = this.otpRepository.getByField("otp", otp);
        return this.otpMapper.mapEntityToOutput(hash);
    }

    public void delete(Long id) {
        SecurityMobileOtpHash hash = this.otpRepository.getById(id);
        if (hash == null)
            throw new OpenApiResourceNotFoundException("RedisTest ID : " + id);

        this.otpRepository.delete(hash);
    }

    //TODO delete ghabli agar bud
    public SecurityMobileOtpOut generateOtp(String username) {
        SecurityMobileOtpHash otpHash = this.getOtpHash(username);
        this.otpRepository.createWithIndex(otpHash);
        return this.otpMapper.mapEntityToOutput(otpHash);
    }

    private SecurityMobileOtpHash getOtpHash(String username) {
        String generatedOtp = this.securityUtils.generateOTP();
        SecurityMobileOtpHash otpHash = new SecurityMobileOtpHash();
        otpHash.setUsername(username);
        otpHash.setOtp(generatedOtp);
        return otpHash;
    }
}