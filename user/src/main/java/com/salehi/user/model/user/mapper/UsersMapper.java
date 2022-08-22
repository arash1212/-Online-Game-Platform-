package com.salehi.user.model.user.mapper;

import com.salehi.datasource.relational.entity.user.UsersEntity;
import com.salehi.user.model.user.dto.UsersOutput;
import com.salehi.user.model.user.dto.UsersInput;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsersMapper {

    UsersEntity mapInputToEntity(UsersInput input);

    UsersEntity mapOutputToEntity(UsersOutput output);

    UsersOutput mapEntityToOutput(UsersEntity entity);
}
