package com.salehi.security.model.authority.mapper;

import com.salehi.datasource.relational.entity.security.AuthorityEntity;
import com.salehi.security.model.authority.dto.AuthorityInput;
import com.salehi.security.model.authority.dto.AuthorityOutput;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorityMapper {
    AuthorityEntity mapInputToEntity(AuthorityInput input);

    AuthorityEntity mapOutputToEntity(AuthorityOutput output);

    AuthorityOutput mapEntityToOutput(AuthorityEntity entity);
}
