/**
 * Suggest to employ this implementation as template for all DAOs
 */
package com.softserve.edu.Resources.dao.impl;

import com.softserve.edu.Resources.dao.GenericDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.lang.invoke.MethodHandles;
import java.util.*;
import java.util.stream.Collectors;
public abstract class GenericDAOImpl<T, ID extends Serializable>
    implements GenericDAO<T, ID> {

    static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getName());

    private final Logger logger;

    @PersistenceContext
    protected EntityManager em;

    protected final Class<T> entityClass;

    public GenericDAOImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
        this.logger = LOGGER;
    }

    protected GenericDAOImpl(Class<T> entityClass, Logger logger) {
        this.entityClass = entityClass;
        this.logger = logger;
    }

    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    public Optional<T> findById(ID id) {
        return findById(id, LockModeType.NONE);
    }

    public Optional<T> findById(ID id, LockModeType lockModeType) {
        return Optional.ofNullable(em.find(entityClass, id, lockModeType));
    }

    public Optional<T> findReferenceById(ID id) {
        try {
            return Optional.ofNullable(em.getReference(entityClass, id));
        } catch (EntityNotFoundException e) {
            return Optional.empty();
        }
    }

    public List<T> findAll() {
        CriteriaQuery<T> c =
            em.getCriteriaBuilder().createQuery(entityClass);
        c.select(c.from(entityClass));
        return em.createQuery(c).getResultList();
    }

    public Long getCount() {
        CriteriaQuery<Long> c =
           em.getCriteriaBuilder().createQuery(Long.class);
        c.select(em.getCriteriaBuilder().count(c.from(entityClass)));
        return em.createQuery(c).getSingleResult();
    }

    public T makePersistent(T instance) {
//         update() handles transient AND detached instances
        return em.merge(instance);
    }

/*    public T makePersistent(T instance) {
        T persisted = instance;
        if (em.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(instance) == null) {
            em.persist(instance);
        } else {
            persisted = em.merge(instance);
        }
        return persisted;
    }*/

    public Optional<T> querySingleResult(String queryWithNamedParams, String paramName, Object paramValue) {
        Map<String, Object> params = new HashMap<>();
        params.put(paramName, paramValue);
        return querySingleResult(queryWithNamedParams, params);
    }

    @Override
    public Optional<T> querySingleResult(String queryWithNamedParams, Map<String, Object> params) {
        Optional<T> result = Optional.empty();
        try {
            final TypedQuery<T> query = em.createQuery(queryWithNamedParams, entityClass);

            params.entrySet().forEach(entry -> query.setParameter(entry.getKey(), entry.getValue()));

            result = Optional.ofNullable(query.getSingleResult());
        } catch (NoResultException nre) {
            final String warn = String.format("{} - failed to find %s matching to params [%s]",
                                              entityClass.getSimpleName(),
                                              collectParamsString(params));
            logger.warn(warn, nre);
        } catch (PersistenceException pe) {
            logger.error("{} {}", pe, pe.getMessage());
        }

        return result;
    }

    public List<T> queryResultList(String queryWithNamedParams, String paramName, Object paramValue) {
        Map<String, Object> params = new HashMap<>();
        params.put(paramName, paramValue);
        return queryResultList(queryWithNamedParams, params);
    }

    @Override
    public List<T> queryResultList(String queryWithNamedParams, Map<String, Object> params) {
        try {
            final TypedQuery<T> query = em.createQuery(queryWithNamedParams, entityClass);

            params.entrySet().forEach(entry -> query.setParameter(entry.getKey(), entry.getValue()));

            return query.getResultList();

        } catch (PersistenceException pe) {
            logger.error("{} {}", pe, pe.getMessage());
            return Collections.emptyList();
        }
    }

    private String collectParamsString(Map<String, Object> params) {
        return params.values().stream().map(Object::toString).collect(Collectors.joining("', '", "'", "'"));
    }

    public void makeTransient(T instance) {
        em.remove(instance);
    }

    public void checkVersion(T entity, boolean forceUpdate) {
        em.lock(
            entity,
            forceUpdate
                ? LockModeType.OPTIMISTIC_FORCE_INCREMENT
                : LockModeType.OPTIMISTIC
        );
    }

    public void flush() {
        em.flush();
    }
}
