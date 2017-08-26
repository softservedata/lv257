package com.softserve.edu.resources.serviceImpl;



import com.softserve.edu.resources.dao.RoleDAO;
import com.softserve.edu.resources.entity.Role;
import com.softserve.edu.resources.service.RoleService;

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
