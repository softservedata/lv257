package com.softserve.edu.resources.daoImpl;


import com.softserve.edu.resources.dao.UserDetailsDAO;
import com.softserve.edu.resources.entity.User;
import org.hibernate.SessionFactory;

public class UserDetailsDAOImpl implements UserDetailsDAO {

    private SessionFactory sessionFactory;

    public UserDetailsDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void updateUserDetails(User user) {

    }
}
