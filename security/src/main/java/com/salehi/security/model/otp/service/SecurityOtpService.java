package com.salehi.security.model.otp.service;

import com.salehi.datasource.redis.hash.security.SecurityOtpRedisHash;
import com.salehi.datasource.relational.enums.messaging.MessageTypeEnum;
import com.salehi.security.model.otp.dto.SecurityOtpInput;
import com.salehi.security.model.otp.dto.SecurityOtpOutput;
import com.salehi.security.model.otp.mapper.SecurityOtpMapper;
import com.salehi.security.model.otp.repository.SecurityOtpRepository;
import com.salehi.user.model.user.dto.UsersOutput;
import com.salehi.user.model.user.service.UsersService;
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
public class SecurityOtpService {

    private final SecurityOtpRepository otpRepository;
    private final SecurityOtpMapper otpMapper;
    private final ISecurityUtils securityUtils;
    private final UsersService usersService;

    @Autowired
    public SecurityOtpService(SecurityOtpRepository otpRepository, SecurityOtpMapper otpMapper, ISecurityUtils securityUtils, UsersService usersService) {
        this.otpRepository = otpRepository;
        this.otpMapper = otpMapper;
        this.securityUtils = securityUtils;
        this.usersService = usersService;
    }

    public void create(SecurityOtpInput input) {
        UsersOutput usersOutput = this.usersService.findByUsername(input.getUsername());
        if (usersOutput == null)
            throw new OpenApiResourceNotFoundException("user : " + input.getUsername());

        this.otpRepository.createOrReplaceWitIndex(this.otpMapper.mapInputToEntity(input), input.getUsername());
    }

    public SecurityOtpRedisHash findById(Long id) {
        SecurityOtpRedisHash hash = this.otpRepository.getById(id);
        if (hash == null)
            throw new OpenApiResourceNotFoundException("RedisTest ID : " + id);

        return this.otpRepository.getById(id);
    }

    public List<SecurityOtpOutput> findByUsername(String username) {
        List<SecurityOtpRedisHash> hashes = this.otpRepository.getAllByField("name", username);
        return hashes.stream().map(this.otpMapper::mapEntityToOutput).collect(Collectors.toList());
    }

    public SecurityOtpOutput findByOtp(int otp) {
        SecurityOtpRedisHash hash = this.otpRepository.getByField("otp", otp);
        return this.otpMapper.mapEntityToOutput(hash);
    }

    public void delete(Long id) {
        SecurityOtpRedisHash hash = this.otpRepository.getById(id);
        if (hash == null)
            throw new OpenApiResourceNotFoundException("RedisTest ID : " + id);

        this.otpRepository.delete(hash);
    }

    public SecurityOtpOutput generateOtp(String username, MessageTypeEnum type) {
        SecurityOtpRedisHash otpHash = this.getOtpHash(username, type);
        this.otpRepository.createOrReplaceWitIndex(otpHash, username);
        return this.otpMapper.mapEntityToOutput(otpHash);
    }

    //TODO
    private SecurityOtpRedisHash getOtpHash(String username, MessageTypeEnum type) {
        String generatedOtp = this.securityUtils.generateOTP();
        SecurityOtpRedisHash otpHash = new SecurityOtpRedisHash();
        otpHash.setUsername(username);
        otpHash.setOtp(generatedOtp);
        otpHash.setType(type);
        return otpHash;
    }
}
