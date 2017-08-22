package com.softserve.edu.dao.impl;

import com.softserve.edu.entities.User;
import org.hibernate.Session;

import java.util.List;

public class UserDAO extends crudDAOImpl<User> {

    public UserDAO(Session session) {
        super(User.class, session);
    }

    public List<User> getUserByFirstName(String firstName){
        return null;
    }
}
