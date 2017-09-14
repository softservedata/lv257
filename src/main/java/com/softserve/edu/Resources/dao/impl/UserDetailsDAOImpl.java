package com.softserve.edu.Resources.dao.impl;

//import edu.softserve.dao.UserDAO;
//import edu.softserve.entity.User;

import com.softserve.edu.Resources.dao.UserDetailsDAO;
import com.softserve.edu.Resources.entity.User;
import com.softserve.edu.Resources.entity.UserDetails;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
/*todo CHANGE from "userDAO" to "userDrtailsDAO"*/
//@Qualifier("userDAO")
@Qualifier("userDrtailsDAO")
@Transactional
public class UserDetailsDAOImpl implements UserDetailsDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public UserDetailsDAOImpl() {
    }

    /*@Override
    public User getUser(User user) {
        return null;
    }*/

    @Override
    public UserDetails getUser(User user) {
        return null;
    }

    @Override
    public UserDetails findByEmail(String email) {
        Query query = entityManager.createQuery("select i from User i where i.username = :username")
                .setParameter("username", email);
        UserDetails userDetails = (UserDetails)query.getSingleResult();
        return userDetails;
    }
/* todo changed Query query = entityManager.createQuery("select i from UserDetails i where i.id = :id").setParameter("id", id);
 */
    @Override
    public UserDetails findById(long id) {
        /*
                Query query = entityManager.createQuery("select i from User i where i.id = :id")
                .setParameter("id", id);
         */
        Query query = entityManager.createQuery("select i from UserDetails i where i.id = :id")
                .setParameter("id", id);
        UserDetails userDetails = (UserDetails) query.getSingleResult();
        return userDetails;
    }

    @Override
    public void delete(User user) {

    }

/*    @Override
    public User addUser(User user) {
        entityManager.persist(user);
        return user;
    }*/

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
        List<?> list = entityManager.createQuery("SELECT p FROM User p").getResultList();
        return (List<User>) list;
    }
}