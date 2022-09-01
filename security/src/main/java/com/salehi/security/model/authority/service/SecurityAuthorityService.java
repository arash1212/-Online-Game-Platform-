package com.salehi.security.model.authority.service;

import com.salehi.datasource.relational.entity.security.SecurityAuthorityEntity;
import com.salehi.security.model.authority.dto.SecurityAuthorityInput;
import com.salehi.security.model.authority.dto.SecurityAuthorityOutput;
import com.salehi.security.model.authority.mapper.SecurityAuthorityMapper;
import com.salehi.security.model.authority.repository.SecurityAuthorityRepository;
import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SecurityAuthorityService {

    private final SecurityAuthorityRepository securityAuthorityRepository;
    private final SecurityAuthorityMapper sECURITYAuthorityMapper;

    @Autowired
    public SecurityAuthorityService(SecurityAuthorityRepository securityAuthorityRepository, SecurityAuthorityMapper sECURITYAuthorityMapper) {
        this.securityAuthorityRepository = securityAuthorityRepository;
        this.sECURITYAuthorityMapper = sECURITYAuthorityMapper;
    }

    public SecurityAuthorityOutput findById(Long id) {
        SecurityAuthorityEntity entity = securityAuthorityRepository.getById(id);
        if (entity == null)
            throw new OpenApiResourceNotFoundException("Authority ID : " + id);

        return sECURITYAuthorityMapper.mapEntityToOutput(entity);
    }

    public List<SecurityAuthorityOutput> findAll() {
        List<SecurityAuthorityEntity> entities = securityAuthorityRepository.getAll();
        return entities.stream().filter(Objects::nonNull).map(sECURITYAuthorityMapper::mapEntityToOutput).collect(Collectors.toList());
    }

    public Long save(SecurityAuthorityInput input) {
        if (this.securityAuthorityRepository.existByFieldName("authority", input.getAuthority()))
            throw new EntityExistsException("authority : " + input.getAuthority());

        SecurityAuthorityEntity entity = sECURITYAuthorityMapper.mapInputToEntity(input);
        return this.securityAuthorityRepository.save(entity);
    }

    public void update(Long id, SecurityAuthorityInput input) {
        SecurityAuthorityEntity entity = sECURITYAuthorityMapper.mapInputToEntity(input);
        if (entity == null)
            throw new OpenApiResourceNotFoundException("Authority ID : " + id);

        entity.setId(id);
        this.securityAuthorityRepository.update(entity);
    }

    public void delete(Long id) {
        this.securityAuthorityRepository.delete(id);
    }

}
