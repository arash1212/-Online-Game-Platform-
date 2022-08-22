package com.salehi.user.user.service;

import com.salehi.datasource.relational.entity.user.UsersEntity;
import com.salehi.user.user.dto.UsersInput;
import com.salehi.user.user.dto.UsersOutput;
import com.salehi.user.user.mapper.UsersMapper;
import com.salehi.user.user.repository.UsersRepository;
import com.salehi.utility.repository.GenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UsersService {

    private final UsersRepository usersGenericRepository;
    private final UsersMapper usersMapper;

    @Autowired
    public UsersService(UsersRepository usersRepository, UsersMapper usersMapper) {
        this.usersGenericRepository = usersRepository;
        this.usersMapper = usersMapper;
    }

    public UsersOutput findById(Long id) {
        UsersEntity entity = usersGenericRepository.getById(id);
        if (entity == null) {
            throw new EntityNotFoundException("User :" + id);
        }
        return usersMapper.mapEntityToOutput(entity);
    }

    public List<UsersOutput> findAll() {
        List<UsersEntity> entities = usersGenericRepository.getAll();
        return entities.stream().filter(Objects::nonNull).map(usersMapper::mapEntityToOutput).collect(Collectors.toList());
    }

    public Long save(UsersInput input) {
        if (this.usersGenericRepository.existByFieldName("email", input.getEmail()))
            throw new EntityExistsException("email : " + input.getEmail());
        if (this.usersGenericRepository.existByFieldName("accountName", input.getAccountName()))
            throw new EntityExistsException("accountName : " + input.getAccountName());

        UsersEntity entity = usersMapper.mapInputToEntity(input);
        return this.usersGenericRepository.save(entity);
    }

    public void update(Long id, UsersInput input) {
        UsersEntity entity = usersMapper.mapInputToEntity(input);
        entity.setId(id);
        this.usersGenericRepository.update(entity);
    }

    public void delete(Long id) {
        this.usersGenericRepository.delete(id);
    }

}
