package com.salehi.datasource.relational.repository;

import com.salehi.datasource.relational.interfaces.IEntity;

import javax.el.MethodNotFoundException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

public class GenericRepositoryImpl<T extends IEntity> implements GenericRepository<T> {

    @PersistenceContext(type = PersistenceContextType.TRANSACTION)
    private EntityManager entityManager;

    public Class<T> getEntityClass() {
        throw new MethodNotFoundException();
    }

    @Override
    public T getById(Long id) {
        return entityManager.find(this.getEntityClass(), id);
    }

    @Override
    public List<T> getAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(this.getEntityClass());
        criteriaQuery.from(this.getEntityClass());

        TypedQuery<T> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList().size() > 0 ? query.getResultList() : new ArrayList<>();
    }

    @Transactional
    @Override
    public Long save(T t) {
        entityManager.persist(t);
        entityManager.flush();
        return t.getId();
    }

    @Transactional
    @Override
    public T update(T t) {
        entityManager.merge(t);
        return null;
    }

    @Transactional
    @Override
    public void delete(Long id) {
        entityManager.remove(id);
    }

    @Override
    public List<Integer> save(List<T> ts) {
        throw new MethodNotFoundException();
    }

    @Override
    public T getByFieldName(String fieldName, String value) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(this.getEntityClass());
        Root<T> root = criteriaQuery.from(this.getEntityClass());
        criteriaQuery.select(root).where(criteriaBuilder.like(root.get(fieldName), value));

        TypedQuery<T> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList().stream().findFirst().orElse(null);
    }

    @Override
    public List<T> getAllByFieldName(String fieldName, String value) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(this.getEntityClass());
        Root<T> root = criteriaQuery.from(this.getEntityClass());
        criteriaQuery.select(root).where(criteriaBuilder.like(root.get(fieldName), value));

        TypedQuery<T> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public List<T> getAllByFieldName(String fieldName, Object value) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(this.getEntityClass());
        Root<T> root = criteriaQuery.from(this.getEntityClass());
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get(fieldName), value));

        TypedQuery<T> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public boolean existByFieldName(String fieldName, String value) {
        return this.getByFieldName(fieldName, value) != null;
    }

}
