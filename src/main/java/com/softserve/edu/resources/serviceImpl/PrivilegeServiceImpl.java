package com.softserve.edu.resources.serviceImpl;


import com.softserve.edu.resources.dao.PrivilegeDAO;
import com.softserve.edu.resources.entity.Privilege;
import com.softserve.edu.resources.service.PrivilegeService;

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
