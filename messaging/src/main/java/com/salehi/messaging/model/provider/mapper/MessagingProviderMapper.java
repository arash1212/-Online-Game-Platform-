package com.salehi.messaging.model.provider.mapper;

import com.salehi.datasource.relational.entity.messaging.MessagingProviderEntity;
import com.salehi.datasource.relational.entity.security.AuthorityEntity;
import com.salehi.messaging.model.provider.dto.MessagingProviderInput;
import com.salehi.messaging.model.provider.dto.MessagingProviderOutput;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MessagingProviderMapper {

    MessagingProviderEntity mapInputToEntity(MessagingProviderInput input);

    MessagingProviderEntity mapOutputToEntity(MessagingProviderOutput output);

    MessagingProviderOutput mapEntityToOutput(MessagingProviderEntity entity);

}
