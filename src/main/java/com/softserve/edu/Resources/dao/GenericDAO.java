/**
 * Suggest to employ this implementation as template for all DAOs
 */
package com.softserve.edu.Resources.dao;

import javax.persistence.LockModeType;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * An interface shared by all business data access objects.
 * <p>
 * All CRUD (create, read, update, delete) basic data access operations are
 * isolated in this interface and shared across all DAO implementations.
 * The current design is for a state-management oriented persistence layer
 * (for example, there is no UPDATE statement function) which provides
 * automatic transactional dirty checking of business objects in persistent
 * state.
 */
public interface GenericDAO<T, ID extends Serializable> {

    Optional<T> findById(ID id);

    Optional<T> findById(ID id, LockModeType lockModeType);

    Optional<T> findReferenceById(ID id);

    List<T> findAll();

    Long getCount();

    T makePersistent(T entity);

    void makeTransient(T entity);

    void checkVersion(T entity, boolean forceUpdate);

    Optional<T> querySingleResult(String queryWithNamedParams, Map<String, Object> params);

    List<T> queryResultList(String queryWithNamedParams, Map<String, Object> params);

    void flush();
}
