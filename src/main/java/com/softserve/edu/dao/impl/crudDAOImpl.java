package com.softserve.edu.dao.impl;

import com.softserve.edu.dao.interfaces.crudDAO;
import org.hibernate.Session;

import java.util.List;

public class crudDAOImpl<E> implements crudDAO<E> {

    private Class<E> entityClass;
    private Session session;

    public crudDAOImpl(Class<E> entityClass, Session session) {
        this.entityClass = entityClass;
        this.session = session;
    }

    public void add(E element) {

    }

    public void update(E element) {

    }

    public void delete(E element) {

    }

    public void deleteById(Long id) {

    }

    public E getById(Long id) {
        return null;
    }

    public List getAll() {
        return null;
    }
}
