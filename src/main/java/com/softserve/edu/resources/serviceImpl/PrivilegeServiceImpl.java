package com.softserve.edu.services.impl;

import com.softserve.edu.dao.interfaces.PrivilegeDAO;
import com.softserve.edu.entities.Privilege;
import com.softserve.edu.services.interfaces.PrivilegeService;

public class PrivilegeServiceImpl implements PrivilegeService {

    private PrivilegeDAO privilegeDAO;

    public PrivilegeServiceImpl(PrivilegeDAO privilegeDAO) {
        this.privilegeDAO = privilegeDAO;
    }

    public Privilege findByName(String name) {
        return null;
    }

    public void delete(Privilege privilege) {

    }
}
