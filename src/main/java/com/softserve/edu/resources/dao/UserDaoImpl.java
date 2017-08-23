package com.softserve.edu.resources.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.softserve.edu.resources.entity.User;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(User entity) {
        entityManager.persist(entity);
    }

}
