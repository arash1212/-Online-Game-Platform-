package com.salehi.user.model.user.mapper;

import com.salehi.datasource.relational.entity.user.UsersEntity;
import com.salehi.user.model.user.dto.UsersInput;
import com.salehi.user.model.user.dto.UsersOutput;
import org.mapstruct.Mapper;

/**
 * @author Arash Salehi
 * @author arashsalehi849@yahoo.com
 * @since 0.0.1
 */
@Mapper(componentModel = "spring")
public interface UsersMapper {

    UsersEntity mapInputToEntity(UsersInput input);

    UsersEntity mapOutputToEntity(UsersOutput output);

    UsersOutput mapEntityToOutput(UsersEntity entity);

}
