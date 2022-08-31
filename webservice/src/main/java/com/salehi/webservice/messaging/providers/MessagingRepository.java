package com.salehi.webservice.messaging.providers;

import com.salehi.datasource.relational.entity.messaging.MessagingProviderEntity;
import com.salehi.datasource.relational.enums.messaging.MessageProviderEnum;
import com.salehi.datasource.relational.repository.GenericRepositoryImpl;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class MessagingRepository extends GenericRepositoryImpl<MessagingProviderEntity> {

    @Override
    public Class<MessagingProviderEntity> getEntityClass() {
        return MessagingProviderEntity.class;
    }

    public MessagingProviderEntity getCredentialsByProvider(MessageProviderEnum provider) {
        String query = "SELECT entity FROM MessagingProviderEntity AS entity WHERE entity.provider=:provider AND entity.active=true";
        Map<String, Object> params = new HashMap<>();
        params.put("provider", provider);
        return super.getByQuery(query, params);
    }
}
