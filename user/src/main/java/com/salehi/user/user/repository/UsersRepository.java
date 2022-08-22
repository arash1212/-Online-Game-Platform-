package com.salehi.user.user.repository;

import com.salehi.datasource.relational.entity.user.UsersEntity;
import com.salehi.utility.repository.GenericRepositoryImpl;
import org.springframework.stereotype.Repository;

@Repository
public class UsersRepository extends GenericRepositoryImpl<UsersEntity> {
    @Override
    public Class<UsersEntity> getEntityClass() {
        return UsersEntity.class;
    }
}
