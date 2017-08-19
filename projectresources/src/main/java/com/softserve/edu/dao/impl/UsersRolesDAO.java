package com.softserve.edu.dao.impl;

import com.softserve.edu.entities.UsersRoles;
import org.hibernate.Session;

public class UsersRolesDAO extends crudDAOImpl<UsersRoles> {
    public UsersRolesDAO(Session session) {
        super(UsersRoles.class, session);
    }
}
