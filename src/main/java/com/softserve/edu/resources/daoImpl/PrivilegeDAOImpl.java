package com.softserve.edu.dao.impl;

import com.softserve.edu.dao.interfaces.PrivilegeDAO;
import com.softserve.edu.entities.Privilege;
import org.hibernate.SessionFactory;

public class PrivilegeDAOImpl implements PrivilegeDAO {


    private SessionFactory sessionFactory;

    public PrivilegeDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Privilege findByName(String name) {
        return null;
    }

    public void delete(Privilege privilege) {

    }
}
