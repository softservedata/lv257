package com.softserve.edu.Resources.security.service.impl;

import com.softserve.edu.Resources.entity.PrivilegeType;
import com.softserve.edu.Resources.security.dao.GenericPrivilegeDAO;
import com.softserve.edu.Resources.security.dto.PrivilegeDTO;
import com.softserve.edu.Resources.security.entity.GenericPrivilege;
import com.softserve.edu.Resources.security.entity.ResourcePropertyPrivilege;
import com.softserve.edu.Resources.security.entity.ResourceTypePrivilege;
import com.softserve.edu.Resources.security.entity.SystemPrivilege;
import com.softserve.edu.Resources.security.service.GenericPrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class GenericPrivilegeServiceImpl implements GenericPrivilegeService {

    @Autowired
    GenericPrivilegeDAO genericPrivilegeDAO;

    @Override
    public GenericPrivilege addGenericPrivilege(GenericPrivilege privilege) {
        genericPrivilegeDAO.addGenericPrivilege(privilege);
        return privilege;
    }

    @Override
    public GenericPrivilege updateGenericPrivilege(GenericPrivilege privilege) {
        genericPrivilegeDAO.updateGenericPrivilege(privilege);
        return privilege;
    }

    @Override
    public List<SystemPrivilege> getAllSystemPrivileges() {
        List<GenericPrivilege> genericPrivileges = genericPrivilegeDAO.getAllSystemPrivileges();
        List<SystemPrivilege> systemPrivileges = new ArrayList<>();
        for (GenericPrivilege x: genericPrivileges) {
            if (x instanceof SystemPrivilege)
                systemPrivileges.add((SystemPrivilege)x);
        }
        return systemPrivileges;
    }

    @Override
    public List<ResourceTypePrivilege> getAllResourceTypePrivileges() {
        List<GenericPrivilege> genericPrivileges = genericPrivilegeDAO.getAllResourceTypePrivileges();
        List<ResourceTypePrivilege> resourceTypePrivileges = new ArrayList<>();
        for (GenericPrivilege x: genericPrivileges) {
            if (x instanceof ResourceTypePrivilege)
                resourceTypePrivileges.add((ResourceTypePrivilege)x);
        }
        return resourceTypePrivileges;
    }

    @Override
    public List<ResourcePropertyPrivilege> getAllResourcePropertiesPrivileges()
    {
        List<GenericPrivilege> genericPrivileges = genericPrivilegeDAO.getAllResourceTypePrivileges();
        List<ResourcePropertyPrivilege> resourcePropertyPrivileges = new ArrayList<>();
        for (GenericPrivilege x: genericPrivileges) {
            if (x instanceof ResourcePropertyPrivilege)
                resourcePropertyPrivileges.add((ResourcePropertyPrivilege)x);
        }
        return resourcePropertyPrivileges;
    }

    @Override
    public List<GenericPrivilege> getAllPrivileges() {
        return null;
    }

    @Override
    public GenericPrivilege getPrivilegeById(long id) {
        return null;
    }

    @Override
    public GenericPrivilege convertToPrivilege(PrivilegeDTO privilegeDTO) {
        System.out.println("converting " + privilegeDTO);

        if (privilegeDTO.getPrivilegeType().equals(PrivilegeType.SYSTEM.name())){
            SystemPrivilege privilege = new SystemPrivilege();
            privilege.setName(privilegeDTO.getName());
            privilege.setDescription(privilegeDTO.getDescription());
            privilege.setAccessible(privilegeDTO.isAccessible());
            System.out.println("converting privilege to SystemPrivilege" + privilege);
            return privilege;
        }
        else if (privilegeDTO.getPrivilegeType().equals(PrivilegeType.RESOURCE_TYPE.name())){
            ResourceTypePrivilege privilege = new ResourceTypePrivilege();
            privilege.setName(privilegeDTO.getName());
            privilege.setDescription(privilegeDTO.getDescription());
            privilege.setCreateAccess(privilegeDTO.isCreateAccess());
            privilege.setDeleteAccess(privilegeDTO.isDeleteAccess());
            privilege.setReadAccess(privilegeDTO.isReadAccess());
            privilege.setUpdateAccess(privilegeDTO.isUpdateAccess());
            privilege.setResourceTypeId(privilegeDTO.getResourceTypeId());
            System.out.println("converting privilege to ResTypePrivilege" + privilege);
            return privilege;
        }

        return null;
    }

}

