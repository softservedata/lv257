package com.softserve.edu.dao.impl;

import com.softserve.edu.entities.Role;
import org.hibernate.Session;

public class RoleDAO extends crudDAOImpl<Role> {
    public RoleDAO(Session session) {
        super(Role.class, session);
    }
}
