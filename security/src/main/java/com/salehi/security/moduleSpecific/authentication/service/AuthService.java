package com.salehi.security.moduleSpecific.authentication.service;

import com.salehi.datasource.relational.entity.user.UsersEntity;
import com.salehi.security.moduleSpecific.authentication.dto.jwt.JwtInput;
import com.salehi.security.moduleSpecific.authentication.dto.jwt.JwtOutput;
import com.salehi.user.model.user.repository.UsersRepository;
import com.salehi.utility.utils.interfaces.IJwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private IJwtUtils jwtUtils;
    @Autowired
    private UsersRepository usersRepository;

    public JwtOutput authJwt(JwtInput input) {
        UsersEntity entity = this.usersRepository.getByEmail(input.getEmail());
        if (entity == null)
            throw new AuthenticationCredentialsNotFoundException("Credentials not found");
        if (!entity.getPassword().equals(input.getPassword()))
            throw new AuthenticationCredentialsNotFoundException("Credentials not found");

        String token = this.jwtUtils.generateJwt(input.getEmail());
        return new JwtOutput(token);
    }

}
