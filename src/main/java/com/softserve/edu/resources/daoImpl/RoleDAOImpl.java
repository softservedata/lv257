package com.softserve.edu.resources.daoImpl;


import com.softserve.edu.resources.dao.RoleDAO;
import com.softserve.edu.resources.entity.Role;
import org.hibernate.SessionFactory;

import java.util.List;

public class RoleDAOImpl implements RoleDAO {

    private SessionFactory sessionFactory;

    public RoleDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Role findByName(String name) {
        return null;
    }

    public void delete(Role role) {

    }

    public List<Role> getAllRoles() {
        return null;
    }
}
