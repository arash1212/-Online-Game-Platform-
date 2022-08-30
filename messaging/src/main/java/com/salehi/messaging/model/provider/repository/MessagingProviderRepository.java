package com.salehi.messaging.model.provider.repository;

import com.salehi.datasource.relational.entity.messaging.MessagingProviderEntity;
import com.salehi.datasource.relational.enums.messaging.MessageTypeEnum;
import com.salehi.datasource.relational.repository.GenericRepositoryImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MessagingProviderRepository extends GenericRepositoryImpl<MessagingProviderEntity> {
    @Override
    public Class<MessagingProviderEntity> getEntityClass() {
        return MessagingProviderEntity.class;
    }

    //TODO active
    public List<MessagingProviderEntity> getActiveSmsProviders() {
        return super.getAllByFieldName("supportedType", MessageTypeEnum.SMS);
    }
}
