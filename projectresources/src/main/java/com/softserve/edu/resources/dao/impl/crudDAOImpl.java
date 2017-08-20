package com.softserve.edu.resources.dao.impl;

import org.hibernate.Session;

import com.softserve.edu.resources.dao.interfaces.crudDAO;

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
