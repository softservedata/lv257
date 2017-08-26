package com.softserve.edu.services.impl;


import com.softserve.edu.dao.interfaces.RoleDAO;
import com.softserve.edu.entities.Role;
import com.softserve.edu.services.interfaces.RoleService;

import java.util.List;

public class RoleServiceImpl implements RoleService {

    private RoleDAO roleDAO;

    public RoleServiceImpl(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    public Role findByName(String name) {
        return roleDAO.findByName(name);
    }

    public void delete(Role role) {
        roleDAO.delete(role);
    }

    public List<Role> getAllRoles() {
        return roleDAO.getAllRoles();
    }
}
