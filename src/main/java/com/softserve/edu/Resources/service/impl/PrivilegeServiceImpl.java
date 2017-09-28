package com.softserve.edu.Resources.service.impl;

import com.softserve.edu.Resources.dao.PrivilegeDAO;
import com.softserve.edu.Resources.entity.Privilege;
import com.softserve.edu.Resources.service.PrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PrivilegeServiceImpl implements PrivilegeService {

    @Autowired
    PrivilegeDAO privilegeDAO;

    public List<String> getAllPrivileges(){
        List<Privilege> list = privilegeDAO.getAllPrivileges();
        List<String> names = new ArrayList<>();

        for (Privilege x : list) {
            names.add(x.getName());
        }
        return names;
    }

    @Override
    public Privilege getPrivilegeById(Long id) {
        return null;
    }

    @Override
    public void deletePrivilege() {

    }

    @Override
    public Privilege addPrivilege(String s) {
        Privilege privilege = privilegeDAO.addPrivilege(s);
        return privilege;
    }
}