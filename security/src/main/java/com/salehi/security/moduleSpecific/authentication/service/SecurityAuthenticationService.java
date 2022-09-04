package com.salehi.security.moduleSpecific.authentication.service;

import com.salehi.datasource.relational.entity.user.UsersEntity;
import com.salehi.security.model.otp.dto.SecurityOtpInput;
import com.salehi.security.moduleSpecific.authentication.dto.jwt.SecurityJwtInput;
import com.salehi.security.moduleSpecific.authentication.dto.jwt.SecurityJwtOutput;
import com.salehi.user.model.user.repository.UsersRepository;
import com.salehi.utility.utils.interfaces.IJwtUtils;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Arash Salehi
 * @author arashsalehi849@yahoo.com
 * @since 0.0.1
 */
@Service
public class SecurityAuthenticationService {
    private final IJwtUtils jwtUtils;
    private final UsersRepository usersRepository;
    private final SecurityUserVerificationService userVerificationService;

    public SecurityAuthenticationService(IJwtUtils jwtUtils, UsersRepository usersRepository, SecurityUserVerificationService userVerificationService) {
        this.jwtUtils = jwtUtils;
        this.usersRepository = usersRepository;
        this.userVerificationService = userVerificationService;
    }

    public SecurityJwtOutput authJwt(SecurityJwtInput input) {
        UsersEntity entity = this.usersRepository.getByEmail(input.getEmail());
        if (entity == null)
            throw new AuthenticationCredentialsNotFoundException("Credentials not found");
        if (!entity.getPassword().equals(input.getPassword()))
            throw new AuthenticationCredentialsNotFoundException("Credentials not found");

        String token = this.jwtUtils.generateJwt(input.getEmail());
        return new SecurityJwtOutput(token);
    }

    //TODO
    public void authOtp(SecurityOtpInput input) {
        this.userVerificationService.sendOtp(input);
    }

}
