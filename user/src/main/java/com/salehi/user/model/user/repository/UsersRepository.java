package com.salehi.user.model.user.repository;

import com.salehi.datasource.relational.entity.user.UsersEntity;
import com.salehi.utility.repository.GenericRepositoryImpl;
import org.springframework.stereotype.Repository;

@Repository
public class UsersRepository extends GenericRepositoryImpl<UsersEntity> {

    @Override
    public Class<UsersEntity> getEntityClass() {
        return UsersEntity.class;
    }

    public UsersEntity getByEmail(String email) {
        return this.getByFieldName("email", email);
    }
}
