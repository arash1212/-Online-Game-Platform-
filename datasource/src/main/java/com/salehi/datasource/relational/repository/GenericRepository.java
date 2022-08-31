package com.salehi.datasource.relational.repository;

import com.salehi.datasource.relational.interfaces.IEntity;

import java.util.List;
import java.util.Map;

public interface GenericRepository<T extends IEntity> {

    Class<T> getEntityClass();

    T getById(Long id);

    List<T> getAll();

    Long save(T t);

    T update(T t);

    void delete(Long id);

    List<Integer> save(List<T> ts);

    T getByFieldName(String fieldName, String value);

    T getByFieldName(String fieldName, Object value);

    List<T> getAllByFieldName(String fieldName, String value);

    List<T> getAllByFieldName(String fieldName, Object value);

    List<T> getAllByQuery(String queryString, Map<String, Object> params);

    T getByQuery(String queryString, Map<String, Object> params);

    boolean existByFieldName(String fieldName, String value);

    boolean existByFieldName(String fieldName, Object value);
}
