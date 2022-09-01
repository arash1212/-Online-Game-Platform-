package com.salehi.user.model.user.service;

import com.salehi.datasource.relational.entity.user.UsersEntity;
import com.salehi.user.model.user.dto.UsersInput;
import com.salehi.user.model.user.dto.UsersOutput;
import com.salehi.user.model.user.mapper.UsersMapper;
import com.salehi.user.model.user.repository.UsersRepository;
import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UsersService {

    private final UsersRepository usersRepository;
    private final UsersMapper usersMapper;

    @Autowired
    public UsersService(UsersRepository usersRepository, UsersMapper usersMapper) {
        this.usersRepository = usersRepository;
        this.usersMapper = usersMapper;
    }

    public UsersOutput findById(Long id) {
        UsersEntity entity = this.usersRepository.getById(id);
        if (entity == null)
            throw new OpenApiResourceNotFoundException("User ID :" + id);

        return this.usersMapper.mapEntityToOutput(entity);
    }

    public List<UsersOutput> findAll() {
        List<UsersEntity> entities = this.usersRepository.getAll();
        return entities.stream().filter(Objects::nonNull).map(usersMapper::mapEntityToOutput).collect(Collectors.toList());
    }

    public Long save(UsersInput input) {
        if (this.usersRepository.existByFieldName("email", input.getEmail()))
            throw new EntityExistsException("email : " + input.getEmail());
        if (this.usersRepository.existByFieldName("accountName", input.getAccountName()))
            throw new EntityExistsException("accountName : " + input.getAccountName());

        UsersEntity entity = usersMapper.mapInputToEntity(input);
        return this.usersRepository.save(entity);
    }

    public void update(Long id, UsersInput input) {
        UsersEntity entity = this.usersMapper.mapInputToEntity(input);
        if (entity == null)
            throw new OpenApiResourceNotFoundException("User ID : " + id);

        entity.setId(id);
        this.usersRepository.update(entity);
    }

    public void delete(Long id) {
        UsersEntity entity = usersRepository.getById(id);
        if (entity == null)
            throw new OpenApiResourceNotFoundException("User ID : " + id);

        this.usersRepository.delete(id);
    }

}
