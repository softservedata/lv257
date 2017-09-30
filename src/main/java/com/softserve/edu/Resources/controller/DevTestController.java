package com.softserve.edu.Resources.controller;

import com.softserve.edu.Resources.entity.PrivilegeType;
import com.softserve.edu.Resources.service.PrivilegeService;
import com.softserve.edu.Resources.service.UserDetailsService;
import com.softserve.edu.Resources.service.UserService;
import com.softserve.edu.Resources.service.impl.ResourceCategoryService;
import com.softserve.edu.Resources.service.impl.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;


@Controller
@Transactional
public class DevTestController {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    PrivilegeService privilegeService;

    @Autowired
    ResourceCategoryService categoryService;

    @Autowired
    ResourceCategoryService resourceCategoryService;

    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    @RequestMapping( value = "/endPoints", method = RequestMethod.GET )
    public String getEndPointsInView(Model model)
    {
        model.addAttribute( "endPoints", requestMappingHandlerMapping.getHandlerMethods().keySet() );
        return "endPoints";
    }

    @RequestMapping(value = "/testdata", method = RequestMethod.GET)
    public String userInfo() {

        privilegeService.deleteAllPrivilege();
        privilegeService.addPrivilege("", PrivilegeType.SYSTEM);
        privilegeService.addPrivilege("", PrivilegeType.SYSTEM);
        privilegeService.addPrivilege("", PrivilegeType.SYSTEM);
        privilegeService.addPrivilege("", PrivilegeType.SYSTEM);
        privilegeService.addPrivilege("", PrivilegeType.SYSTEM);
        privilegeService.addPrivilege("", PrivilegeType.SYSTEM);
        privilegeService.addPrivilege("", PrivilegeType.SYSTEM);
        privilegeService.addPrivilege("", PrivilegeType.SYSTEM);
        privilegeService.addPrivilege("", PrivilegeType.SYSTEM);
        privilegeService.addPrivilege("", PrivilegeType.SYSTEM);
        privilegeService.addPrivilege("", PrivilegeType.SYSTEM);
        privilegeService.addPrivilege("", PrivilegeType.SYSTEM);
        privilegeService.addPrivilege("", PrivilegeType.SYSTEM);
        /*privilegeService.addPrivilege("user:*", PrivilegeType.SYSTEM);
        privilegeService.addPrivilege("user:create", PrivilegeType.SYSTEM);
        privilegeService.addPrivilege("user:delete:*", PrivilegeType.SYSTEM);
        privilegeService.addPrivilege("user:delete:{id}", PrivilegeType.SYSTEM);
        privilegeService.addPrivilege("user:read:*:*", PrivilegeType.SYSTEM);
        privilegeService.addPrivilege("user:read:*:userId", PrivilegeType.SYSTEM);
        privilegeService.addPrivilege("user:read:*:email", PrivilegeType.SYSTEM);
        privilegeService.addPrivilege("user:read:*:password", PrivilegeType.SYSTEM);
        privilegeService.addPrivilege("user:read:*:enabled", PrivilegeType.SYSTEM);
        privilegeService.addPrivilege("user:read:*:secret", PrivilegeType.SYSTEM);
        privilegeService.addPrivilege("user:read:*:roles", PrivilegeType.SYSTEM);
        privilegeService.addPrivilege("user:read:*:privileges", PrivilegeType.SYSTEM);
        privilegeService.addPrivilege("user:read:{id}:userId", PrivilegeType.SYSTEM);
        privilegeService.addPrivilege("user:read:{id}:email", PrivilegeType.SYSTEM);
        privilegeService.addPrivilege("user:read:{id}:password");
        privilegeService.addPrivilege("user:read:{id}:enabled");
        privilegeService.addPrivilege("user:read:{id}:secret");
        privilegeService.addPrivilege("user:read:{id}:roles");
        privilegeService.addPrivilege("user:read:{id}:privileges");
        privilegeService.addPrivilege("user:update:*:*");
        privilegeService.addPrivilege("user:update:*:userId");
        privilegeService.addPrivilege("user:update:*:email");
        privilegeService.addPrivilege("user:update:*:password");
        privilegeService.addPrivilege("user:update:*:enabled");
        privilegeService.addPrivilege("user:update:*:secret");
        privilegeService.addPrivilege("user:update:*:roles");
        privilegeService.addPrivilege("user:update:*:privileges");
        privilegeService.addPrivilege("user:update:{id}:userId");
        privilegeService.addPrivilege("user:update:{id}:email");
        privilegeService.addPrivilege("user:update:{id}:password");
        privilegeService.addPrivilege("user:update:{id}:enabled");
        privilegeService.addPrivilege("user:update:{id}:secret");
        privilegeService.addPrivilege("user:update:{id}:roles");
        privilegeService.addPrivilege("user:update:{id}:privileges");
        privilegeService.addPrivilege("role:*");
        privilegeService.addPrivilege("role:create");
        privilegeService.addPrivilege("role:delete");
        privilegeService.addPrivilege("role:read");
        privilegeService.addPrivilege("role:update");
        privilegeService.addPrivilege("privilege:*");
        privilegeService.addPrivilege("privilege:create");
        privilegeService.addPrivilege("privilege:delete");
        privilegeService.addPrivilege("privilege:read");
        privilegeService.addPrivilege("privilege:update");*/
        privilegeService.addPrivilege("privilege:REsType1:read", PrivilegeType.RESOURCE_TYPE);
        privilegeService.addPrivilege("privilege:REsType1:create", PrivilegeType.RESOURCE_TYPE);
        privilegeService.addPrivilege("privilege:REsType1:update", PrivilegeType.RESOURCE_TYPE);
        privilegeService.addPrivilege("privilege:REsType1:delete", PrivilegeType.RESOURCE_TYPE);
        privilegeService.addPrivilege("privilege:REsType2:read", PrivilegeType.RESOURCE_TYPE);
        privilegeService.addPrivilege("privilege:REsType2:create", PrivilegeType.RESOURCE_TYPE);
        privilegeService.addPrivilege("privilege:REsType2:update", PrivilegeType.RESOURCE_TYPE);
        privilegeService.addPrivilege("privilege:REsType2:delete", PrivilegeType.RESOURCE_TYPE);
        privilegeService.addPrivilege("privilege:REsType3:read", PrivilegeType.RESOURCE_TYPE);
        privilegeService.addPrivilege("privilege:REsType3:create", PrivilegeType.RESOURCE_TYPE);
        privilegeService.addPrivilege("privilege:REsType3:update", PrivilegeType.RESOURCE_TYPE);
        privilegeService.addPrivilege("privilege:REsType3:delete", PrivilegeType.RESOURCE_TYPE);
        privilegeService.addPrivilege("privilege:REsType4:read", PrivilegeType.RESOURCE_TYPE);
        privilegeService.addPrivilege("privilege:REsType4:create", PrivilegeType.RESOURCE_TYPE);
        privilegeService.addPrivilege("privilege:REsType4:update", PrivilegeType.RESOURCE_TYPE);
        privilegeService.addPrivilege("privilege:REsType4:delete", PrivilegeType.RESOURCE_TYPE);
        privilegeService.addPrivilege("privilege:REsType4:delete", PrivilegeType.RESOURCE_TYPE);
        privilegeService.addPrivilege("privilege:REsType1:prop1:read", PrivilegeType.PROPERTY);
        privilegeService.addPrivilege("privilege:REsType1:prop1:update", PrivilegeType.PROPERTY);
        privilegeService.addPrivilege("privilege:REsType1:prop2:read", PrivilegeType.PROPERTY);
        privilegeService.addPrivilege("privilege:REsType1:prop2:update", PrivilegeType.PROPERTY);
        privilegeService.addPrivilege("privilege:REsType1:prop3:read", PrivilegeType.PROPERTY);
        privilegeService.addPrivilege("privilege:REsType1:prop3:update", PrivilegeType.PROPERTY);
        privilegeService.addPrivilege("privilege:REsType1:prop4:read", PrivilegeType.PROPERTY);
        privilegeService.addPrivilege("privilege:REsType1:prop4:update", PrivilegeType.PROPERTY);
        privilegeService.addPrivilege("privilege:REsType1:prop5:read", PrivilegeType.PROPERTY);
        privilegeService.addPrivilege("privilege:REsType1:prop5:update", PrivilegeType.PROPERTY);
        privilegeService.addPrivilege("privilege:REsType1:prop6:read", PrivilegeType.PROPERTY);
        privilegeService.addPrivilege("privilege:REsType1:prop6:update", PrivilegeType.PROPERTY);
        privilegeService.addPrivilege("privilege:REsType1:prop7:read", PrivilegeType.PROPERTY);
        privilegeService.addPrivilege("privilege:REsType1:prop7:update", PrivilegeType.PROPERTY);
        privilegeService.addPrivilege("privilege:REsType2:prop1:read", PrivilegeType.PROPERTY);
        privilegeService.addPrivilege("privilege:REsType2:prop1:update", PrivilegeType.PROPERTY);
        privilegeService.addPrivilege("privilege:REsType2:prop2:read", PrivilegeType.PROPERTY);
        privilegeService.addPrivilege("privilege:REsType2:prop2:update", PrivilegeType.PROPERTY);
        privilegeService.addPrivilege("privilege:REsType2:prop3:read", PrivilegeType.PROPERTY);
        privilegeService.addPrivilege("privilege:REsType2:prop3:update", PrivilegeType.PROPERTY);
        privilegeService.addPrivilege("privilege:REsType2:prop4:read", PrivilegeType.PROPERTY);
        privilegeService.addPrivilege("privilege:REsType2:prop4:update", PrivilegeType.PROPERTY);
        privilegeService.addPrivilege("privilege:REsType2:prop5:read", PrivilegeType.PROPERTY);
        privilegeService.addPrivilege("privilege:REsType2:prop5:update", PrivilegeType.PROPERTY);
        privilegeService.addPrivilege("privilege:REsType2:prop6:read", PrivilegeType.PROPERTY);
        privilegeService.addPrivilege("privilege:REsType2:prop6:update", PrivilegeType.PROPERTY);
        privilegeService.addPrivilege("privilege:REsType2:prop7:read", PrivilegeType.PROPERTY);
        privilegeService.addPrivilege("privilege:REsType2:prop7:update", PrivilegeType.PROPERTY);

        categoryService.insertCategoriesTEMPORARY();

        return "welcome2";
    }
}