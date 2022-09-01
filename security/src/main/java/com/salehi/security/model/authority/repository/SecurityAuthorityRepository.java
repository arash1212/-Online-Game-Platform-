package com.salehi.security.model.authority.repository;

import com.salehi.datasource.relational.entity.security.SecurityAuthorityEntity;
import com.salehi.datasource.relational.repository.GenericRepositoryImpl;
import org.springframework.stereotype.Repository;

@Repository
public class SecurityAuthorityRepository extends GenericRepositoryImpl<SecurityAuthorityEntity> {

    @Override
    public Class<SecurityAuthorityEntity> getEntityClass() {
        return SecurityAuthorityEntity.class;
    }

}
