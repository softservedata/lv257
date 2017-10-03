package com.softserve.edu.Resources.dao.impl;

import com.softserve.edu.Resources.dao.UserDAO;
import com.softserve.edu.Resources.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.invoke.MethodHandles;
import java.util.List;

@Repository("userDAO")
@Transactional
public class UserDAOImpl extends GenericDAOImpl<User, Long> implements UserDAO
{

    static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getName());

    @PersistenceContext
    private EntityManager entityManager;

    public UserDAOImpl() {
        super(User.class, LOGGER);
    }

    public User findByEmail(String email) {
        Query query = entityManager.createQuery("select i from User i where i.username = :username")
                .setParameter("username", email);

        User user;
        try {
            user = (User)query.getSingleResult();
        } catch (NoResultException e){
            return null;
        }
        return user;
    }

    public User findById(long id) {
        Query query = entityManager.createQuery("select i from User i where i.id = :id")
                .setParameter("id", id);
        User user = (User)query.getSingleResult();
        return user;
    }

     public List<User> getAllUsers() {
        List<?> list = entityManager.createQuery("SELECT p FROM User p").getResultList();
        return (List<User>) list;
    }
}
