package com.softserve.edu.resources.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class AbstractDAO<E> {

    @PersistenceContext
    protected EntityManager entityManager;

    public void save(E entity) {
        entityManager.persist(entity);
    }

}
