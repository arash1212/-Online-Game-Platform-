package com.salehi.security.moduleSpecific.authentication.service;

import com.salehi.datasource.relational.entity.user.UsersEntity;
import com.salehi.user.model.user.repository.UsersRepository;
import com.salehi.utility.utils.interfaces.IJwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

/**
 * @author Arash Salehi
 * @author arashsalehi849@yahoo.com
 * @since 0.0.1
 */
@Service
public class SecurityUsersDetailService {
    @Autowired
    private IJwtUtils jwtUtils;
    @Autowired
    private UsersRepository usersRepository;

    public UsersEntity extractJwtUser(String token) {
        String email = this.jwtUtils.getEmail(token);
        UsersEntity entity = this.usersRepository.getByFieldName("email", email, new String[]{"authorities"});
        if (entity == null)
            throw new AccessDeniedException("Wrong Credentials");

        return entity;
    }
}
