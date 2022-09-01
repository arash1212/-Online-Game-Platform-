package com.salehi.messaging.model.provider.service;

import com.salehi.datasource.relational.entity.messaging.MessagingProviderEntity;
import com.salehi.datasource.relational.entity.user.UsersEntity;
import com.salehi.messaging.model.provider.dto.MessagingProviderInput;
import com.salehi.messaging.model.provider.dto.MessagingProviderOutput;
import com.salehi.messaging.model.provider.mapper.MessagingProviderMapper;
import com.salehi.messaging.model.provider.repository.MessagingProviderRepository;
import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class MessagingProviderService {

    private final MessagingProviderRepository providerRepository;
    private final MessagingProviderMapper providerMapper;

    @Autowired
    public MessagingProviderService(MessagingProviderRepository providerRepository, MessagingProviderMapper providerMapper) {
        this.providerRepository = providerRepository;
        this.providerMapper = providerMapper;
    }

    public MessagingProviderOutput findById(Long id) {
        MessagingProviderEntity entity = this.providerRepository.getById(id);
        if (entity == null)
            throw new OpenApiResourceNotFoundException("Provider ID : " + id);

        return this.providerMapper.mapEntityToOutput(entity);
    }

    public List<MessagingProviderOutput> findAll() {
        List<MessagingProviderEntity> entities = this.providerRepository.getAll();
        return entities.stream().filter(Objects::nonNull).map(providerMapper::mapEntityToOutput).collect(Collectors.toList());
    }

    public Long save(MessagingProviderInput input) {
        if (this.providerRepository.existByFieldName("title", input.getTitle()))
            throw new EntityExistsException("title : " + input.getTitle());
        if (this.providerRepository.existByFieldName("provider", input.getProvider()))
            throw new EntityExistsException("provider : " + input.getProvider());

        input.validate();
        MessagingProviderEntity entity = this.providerMapper.mapInputToEntity(input);
        return this.providerRepository.save(entity);
    }

    public void update(Long id, MessagingProviderInput input) {
        MessagingProviderEntity entity = this.providerMapper.mapInputToEntity(input);
        if (entity == null)
            throw new OpenApiResourceNotFoundException("Provider ID : " + id);

        entity.setId(id);
        this.providerRepository.update(entity);
    }

    public void delete(Long id) {
        MessagingProviderEntity entity = this.providerRepository.getById(id);
        if (entity == null)
            throw new OpenApiResourceNotFoundException("Provider ID : " + id);

        this.providerRepository.delete(id);
    }

}
