package com.salehi.security.model.authority.service;

import com.salehi.datasource.relational.entity.security.AuthorityEntity;
import com.salehi.security.model.authority.dto.AuthorityInput;
import com.salehi.security.model.authority.dto.AuthorityOutput;
import com.salehi.security.model.authority.mapper.AuthorityMapper;
import com.salehi.security.model.authority.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AuthorityService {

    private final AuthorityRepository authorityRepository;
    private final AuthorityMapper authorityMapper;

    @Autowired
    public AuthorityService(AuthorityRepository authorityRepository, AuthorityMapper authorityMapper) {
        this.authorityRepository = authorityRepository;
        this.authorityMapper = authorityMapper;
    }

    public AuthorityOutput findById(Long id) {
        AuthorityEntity entity = authorityRepository.getById(id);
        if (entity == null) {
            throw new EntityNotFoundException("User :" + id);
        }

        return authorityMapper.mapEntityToOutput(entity);
    }

    public List<AuthorityOutput> findAll() {
        List<AuthorityEntity> entities = authorityRepository.getAll();
        return entities.stream().filter(Objects::nonNull).map(authorityMapper::mapEntityToOutput).collect(Collectors.toList());
    }

    public Long save(AuthorityInput input) {
        if (this.authorityRepository.existByFieldName("authority", input.getAuthority()))
            throw new EntityExistsException("authority : " + input.getAuthority());

        AuthorityEntity entity = authorityMapper.mapInputToEntity(input);
        return this.authorityRepository.save(entity);
    }

    public void update(Long id, AuthorityInput input) {
        AuthorityEntity entity = authorityMapper.mapInputToEntity(input);
        entity.setId(id);
        this.authorityRepository.update(entity);
    }

    public void delete(Long id) {
        this.authorityRepository.delete(id);
    }

}
