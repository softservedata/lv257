package com.softserve.edu.Resources.service.impl;

import com.softserve.edu.Resources.dao.PrivilegeDAO;
import com.softserve.edu.Resources.entity.Privilege;
import com.softserve.edu.Resources.entity.PrivilegeType;
import com.softserve.edu.Resources.service.PrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrivilegeServiceImpl implements PrivilegeService {

    @Autowired
    PrivilegeDAO privilegeDAO;

    public List<Privilege> getAllPrivileges(){
        /*List<Privilege> list = privilegeDAO.getAllPrivileges();
        List<String> names = new ArrayList<>();

        for (Privilege x : list) {
            names.add(x.getName());
        }
        return names;*/
        return privilegeDAO.getAllPrivileges();
    }

    @Override
    public Privilege getPrivilegeById(Long id) {
        return null;
    }

    @Override
    public Privilege addPrivilege(String s) {
        Privilege privilege = privilegeDAO.addPrivilege(new Privilege(s));
        return privilege;
    }

    @Override
    public Privilege addPrivilege(String s, PrivilegeType privilegeType) {
        Privilege privilege = privilegeDAO.addPrivilege(new Privilege(s, privilegeType));
        return privilege;
    }

    @Override
    public void deletePrivilege() {

    }

    @Override
    public void deleteAllPrivilege() {
        privilegeDAO.deleteAllPrivileges();
    }
}