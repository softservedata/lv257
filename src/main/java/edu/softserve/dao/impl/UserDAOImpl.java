package edu.softserve.dao.impl;

import edu.softserve.dao.UserDAO;
import edu.softserve.entity.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Qualifier("userDAO")
@Transactional
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public UserDAOImpl() {
    }

    @Override
    public User findByEmail(String email) {
        Query query = entityManager.createQuery("select i from User i where i.username = :username")
                .setParameter("username", email);
        User user = (User)query.getSingleResult();
        return user;
    }

    @Override
    public User findById(long id) {
        Query query = entityManager.createQuery("select i from User i where i.id = :id")
                .setParameter("id", id);
        User user = (User)query.getSingleResult();
        return user;
    }

    @Override
    public void delete(User user) {

    }

    @Override
    public User addUser(User user) {
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
        List<?> list = entityManager.createQuery("SELECT p FROM User p").getResultList();
        return (List<User>) list;
    }
}
