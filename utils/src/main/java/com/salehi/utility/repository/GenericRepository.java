package com.salehi.utility.repository;

import com.salehi.utility.interfaces.IEntity;

import java.util.List;

public interface GenericRepository<T extends IEntity> {

    Class<T> getEntityClass();

    T getById(Long id);

    List<T> getAll();

    Long save(T t);

    T update(T t);

    void delete(Long id);

    List<Integer> save(List<T> ts);

    T getByFieldName(String fieldName, String value);

    boolean existByFieldName(String fieldName, String value);
}
