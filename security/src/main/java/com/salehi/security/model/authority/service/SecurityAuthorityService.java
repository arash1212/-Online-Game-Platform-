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

    private final SecurityAuthorityRepository authorityRepository;
    private final SecurityAuthorityMapper authorityMapper;

    @Autowired
    public SecurityAuthorityService(SecurityAuthorityRepository authorityRepository, SecurityAuthorityMapper authorityMapper) {
        this.authorityRepository = authorityRepository;
        this.authorityMapper = authorityMapper;
    }

    public SecurityAuthorityOutput findById(Long id) {
        SecurityAuthorityEntity entity = authorityRepository.getById(id);
        if (entity == null)
            throw new OpenApiResourceNotFoundException("Authority ID : " + id);

        return authorityMapper.mapEntityToOutput(entity);
    }

    public List<SecurityAuthorityOutput> findAll() {
        List<SecurityAuthorityEntity> entities = authorityRepository.getAll();
        return entities.stream().filter(Objects::nonNull).map(authorityMapper::mapEntityToOutput).collect(Collectors.toList());
    }

    public Long save(SecurityAuthorityInput input) {
        if (this.authorityRepository.existByFieldName("authority", input.getAuthority()))
            throw new EntityExistsException("authority : " + input.getAuthority());

        SecurityAuthorityEntity entity = authorityMapper.mapInputToEntity(input);
        return this.authorityRepository.save(entity);
    }

    public void update(Long id, SecurityAuthorityInput input) {
        SecurityAuthorityEntity entity = authorityMapper.mapInputToEntity(input);
        if (entity == null)
            throw new OpenApiResourceNotFoundException("Authority ID : " + id);

        entity.setId(id);
        this.authorityRepository.update(entity);
    }

    public void delete(Long id) {
        this.authorityRepository.delete(id);
    }

}
