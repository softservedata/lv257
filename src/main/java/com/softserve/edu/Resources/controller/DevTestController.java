package com.softserve.edu.Resources.controller;

import com.softserve.edu.Resources.service.PrivilegeService;
import com.softserve.edu.Resources.service.UserDetailsService;
import com.softserve.edu.Resources.service.UserService;
import com.softserve.edu.Resources.service.impl.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


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

    @RequestMapping(value = "/testdata", method = RequestMethod.GET)
    public String userInfo() {

        privilegeService.addPrivilege("user:*");
        privilegeService.addPrivilege("user:create");
        privilegeService.addPrivilege("user:delete:*");
        privilegeService.addPrivilege("user:delete:{id}");
        privilegeService.addPrivilege("user:read:*:*");
        privilegeService.addPrivilege("user:read:*:userId");
        privilegeService.addPrivilege("user:read:*:email");
        privilegeService.addPrivilege("user:read:*:password");
        privilegeService.addPrivilege("user:read:*:enabled");
        privilegeService.addPrivilege("user:read:*:secret");
        privilegeService.addPrivilege("user:read:*:roles");
        privilegeService.addPrivilege("user:read:*:privileges");
        privilegeService.addPrivilege("user:read:{id}:userId");
        privilegeService.addPrivilege("user:read:{id}:email");
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
        privilegeService.addPrivilege("privilege:update");

        return "welcome2";
    }
}