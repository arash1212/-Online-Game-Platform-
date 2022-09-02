package com.salehi.datasource.relational.repository;

import com.salehi.datasource.relational.interfaces.IEntity;
import org.springframework.transaction.annotation.Transactional;

import javax.el.MethodNotFoundException;
import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Arash Salehi
 * @author arashsalehi849@yahoo.com
 * @since 0.0.1
 */
public class GenericRepositoryImpl<T extends IEntity> implements IGenericRepository<T> {

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

    @Transactional
    public void updateField(String fieldName, Object value, Map<String, Object> conditions, Class<T> tClass) {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaUpdate<T> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(tClass);
        Root<T> root = criteriaUpdate.from(tClass);

        criteriaUpdate.set(fieldName, value);
        for (String condition : conditions.keySet())
            criteriaUpdate.where(criteriaBuilder.equal(root.get(condition), conditions.get(condition)));

        entityManager.createQuery(criteriaUpdate).executeUpdate();
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
    public T getByFieldName(String fieldName, Object value) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(this.getEntityClass());
        Root<T> root = criteriaQuery.from(this.getEntityClass());
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get(fieldName), value));

        TypedQuery<T> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList().stream().findFirst().orElse(null);
    }

    @Override
    public T getByFieldName(String fieldName, String value, String[] joins) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(this.getEntityClass());
        Root<T> root = criteriaQuery.from(this.getEntityClass());
        criteriaQuery.select(root).where(criteriaBuilder.like(root.get(fieldName), value));

        for (String join : joins) {
            root.fetch(join, JoinType.LEFT);
        }
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
    public List<T> getAllByQuery(String queryString, Map<String, Object> params) {
        Query query = entityManager.createQuery(queryString);
        for (String key : params.keySet()) {
            query.setParameter(key, params.get(key));
        }
        return query.getResultList();
    }

    @Override
    public T getByQuery(String queryString, Map<String, Object> params) {
        Query query = entityManager.createQuery(queryString);
        for (String key : params.keySet()) {
            query.setParameter(key, params.get(key));
        }
        return (T) query.getResultList().stream().findFirst().orElse(null);
    }

    @Override
    public boolean existByFieldName(String fieldName, String value) {
        return this.getByFieldName(fieldName, value) != null;
    }

    @Override
    public boolean existByFieldName(String fieldName, Object value) {
        return this.getByFieldName(fieldName, value) != null;
    }


////    //TODO update beshe
//    public void updateFields(Map<String, Object> updateFields, Map<String, Object> conditions, Class<T> tClass) {
//        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
//        CriteriaUpdate<T> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(tClass);
//        Root<T> root = criteriaUpdate.from(tClass);
//
//        for (String value : updateFields.keySet())
//            criteriaUpdate.set(value, updateFields.get(value));
//        for (String condition : conditions.keySet())
//            criteriaUpdate.where(criteriaBuilder.greaterThanOrEqualTo(root.get(condition), (String) conditions.get(condition)));
//
//        this.entityManager.createQuery(criteriaUpdate).executeUpdate();
//    }

}
