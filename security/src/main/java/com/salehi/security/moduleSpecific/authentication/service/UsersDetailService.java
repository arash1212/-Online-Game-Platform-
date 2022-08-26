package com.salehi.security.moduleSpecific.authentication.service;

import com.salehi.datasource.relational.entity.user.UsersEntity;
import com.salehi.user.model.user.repository.UsersRepository;
import com.salehi.utility.utils.interfaces.IJwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

@Service
public class UsersDetailService {
    @Autowired
    private IJwtUtils jwtUtils;
    @Autowired
    private UsersRepository usersRepository;

    public UsersEntity extractJwtUser(String token) {
        String email = this.jwtUtils.getEmail(token);
        UsersEntity entity = this.usersRepository.getByFieldName("email", email);
        if (entity == null)
            throw new AccessDeniedException("Wrong Credentials");

        return entity;
    }
}