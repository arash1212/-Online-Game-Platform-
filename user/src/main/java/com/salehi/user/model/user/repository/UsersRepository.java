package com.salehi.user.model.user.repository;

import com.salehi.datasource.relational.entity.user.UsersEntity;
import com.salehi.datasource.relational.repository.GenericRepositoryImpl;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @author Arash Salehi
 * @author arashsalehi849@yahoo.com
 * @since 0.0.1
 */
@Repository
public class UsersRepository extends GenericRepositoryImpl<UsersEntity> {

    @Override
    public Class<UsersEntity> getEntityClass() {
        return UsersEntity.class;
    }

    public UsersEntity getByEmail(String email) {
        return super.getByFieldName("email", email);
    }

    public void updateField(String fieldName, Object value, Map<String, Object> condition) {
        super.updateField(fieldName, value, condition, UsersEntity.class);
    }
}
