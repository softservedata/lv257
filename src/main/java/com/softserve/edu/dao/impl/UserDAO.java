package com.softserve.edu.dao.impl;

import com.softserve.edu.entities.User;
import org.hibernate.Session;

public class UserDAO extends crudDAOImpl<User> {

    public UserDAO(Session session) {
        super(User.class, session);
    }
}
