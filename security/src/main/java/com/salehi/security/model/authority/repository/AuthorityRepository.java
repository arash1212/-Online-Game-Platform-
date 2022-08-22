package com.salehi.security.model.authority.repository;

import com.salehi.datasource.relational.entity.security.AuthorityEntity;
import com.salehi.utility.repository.GenericRepositoryImpl;
import org.springframework.stereotype.Repository;

@Repository
public class AuthorityRepository extends GenericRepositoryImpl<AuthorityEntity> {

    @Override
    public Class<AuthorityEntity> getEntityClass() {
        return AuthorityEntity.class;
    }

}
