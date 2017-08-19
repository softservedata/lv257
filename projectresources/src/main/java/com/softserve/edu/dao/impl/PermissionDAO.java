package com.softserve.edu.dao.impl;

import com.softserve.edu.entities.Permissions;
import org.hibernate.Session;

public class PermissionDAO extends crudDAOImpl<Permissions> {
    public PermissionDAO(Session session) {
        super(Permissions.class, session);
    }
}
