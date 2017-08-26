package com.softserve.edu.dao.impl;

import com.softserve.edu.dao.interfaces.UserDetailsDAO;
import com.softserve.edu.entities.User;
import org.hibernate.SessionFactory;

public class UserDetailsDAOImpl implements UserDetailsDAO {

    private SessionFactory sessionFactory;

    public UserDetailsDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void updateUserDetails(User user) {

    }
}
