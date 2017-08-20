package com.softserve.edu.resources.dao.impl;

import org.hibernate.Session;

import com.softserve.edu.resources.entities.UsersRoles;

public class UsersRolesDAO extends crudDAOImpl<UsersRoles> {
    public UsersRolesDAO(Session session) {
        super(UsersRoles.class, session);
    }
}
