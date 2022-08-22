package com.salehi.user.user.mapper;

import com.salehi.datasource.relational.entity.user.UsersEntity;
import com.salehi.user.user.dto.UsersInput;
import com.salehi.user.user.dto.UsersOutput;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsersMapper {

    UsersEntity mapInputToEntity(UsersInput input);

    UsersEntity mapOutputToEntity(UsersOutput output);

    UsersOutput mapEntityToOutput(UsersEntity entity);
}
