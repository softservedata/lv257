package com.softserve.edu.services;


import com.softserve.edu.dao.impl.RoleDAO;
import com.softserve.edu.entities.Role;

public class RoleService {

    private RoleDAO roleDAO;

    public RoleService(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    public void addRole(Role role){
        roleDAO.add(role);
    }

    public void cloneRoleById(int id){

    }

    public void deleteRoleById(int id){

    }

}
