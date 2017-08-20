package com.softserve.edu.resources.dao.impl;

import org.hibernate.Session;

import com.softserve.edu.resources.entities.Permissions;

public class PermissionDAO extends crudDAOImpl<Permissions> {
    public PermissionDAO(Session session) {
        super(Permissions.class, session);
    }
}
