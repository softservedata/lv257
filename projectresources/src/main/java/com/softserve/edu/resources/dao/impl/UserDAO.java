package com.softserve.edu.resources.dao.impl;

import org.hibernate.Session;

import com.softserve.edu.resources.entities.User;

public class UserDAO extends crudDAOImpl<User> {

    public UserDAO(Session session) {
        super(User.class, session);
    }
}
