package com.salehi.security.model.authority.mapper;

import com.salehi.datasource.relational.entity.security.SecurityAuthorityEntity;
import com.salehi.security.model.authority.dto.SecurityAuthorityInput;
import com.salehi.security.model.authority.dto.SecurityAuthorityOutput;
import org.mapstruct.Mapper;

/**
 * @author Arash Salehi
 * @author arashsalehi849@yahoo.com
 * @since 0.0.1
 */
@Mapper(componentModel = "spring")
public interface SecurityAuthorityMapper {
    SecurityAuthorityEntity mapInputToEntity(SecurityAuthorityInput input);

    SecurityAuthorityEntity mapOutputToEntity(SecurityAuthorityOutput output);

    SecurityAuthorityOutput mapEntityToOutput(SecurityAuthorityEntity entity);
}
