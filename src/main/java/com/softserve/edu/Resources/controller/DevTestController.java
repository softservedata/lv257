package com.softserve.edu.Resources.controller;

import com.softserve.edu.Resources.entity.*;
import com.softserve.edu.Resources.service.PrivilegeService;
import com.softserve.edu.Resources.service.ResourceTypeService;
import com.softserve.edu.Resources.service.UserDetailsService;
import com.softserve.edu.Resources.service.UserService;
import com.softserve.edu.Resources.service.impl.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


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
    PasswordEncoder encoder;
/*
    @Autowired
    ResourceCategoryService categoryService;

    @Autowired
    ResourceCategoryService resourceCategoryService;*/

    @Autowired
    ResourceTypeService resourceTypeService;

    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    @RequestMapping( value = "/endPoints", method = RequestMethod.GET )
    public String getEndPointsInView(Model model)
    {
        model.addAttribute( "endPoints", requestMappingHandlerMapping.getHandlerMethods().keySet() );
        return "devtest/endPoints";
    }

    @RequestMapping(value = "/welcome2", method = RequestMethod.GET)
    public String deleteData() {

        return "devtest/welcome2";
    }

    @RequestMapping(value = "/testdata", method = RequestMethod.GET)
    public String testdata() {
        addTestPrivileges();
        addTestRoles();
        addTestUsers();

        return "devtest/welcome2";
    }

    private void addTestPrivileges(){
        privilegeService.deleteAllPrivilege();
        //adding system privileges
        privilegeService.addPrivilege("Read users information","Allows to get all user information from the system", PrivilegeType.SYSTEM);
        privilegeService.addPrivilege("Create and update users","Allows to create new and update existing (requires roles)", PrivilegeType.SYSTEM);
        privilegeService.addPrivilege("Read roles","Allows to get all roles information from the system", PrivilegeType.SYSTEM);
        privilegeService.addPrivilege("Create and update roles","Allows to create and update roles", PrivilegeType.SYSTEM);
        privilegeService.addPrivilege("Send request","Send request for new Resource Type or Category  Creation ", PrivilegeType.SYSTEM);
        privilegeService.addPrivilege("Manage Resource Types and Categories","Allows to perform add, edit, clone operations for Resource Types and Categories", PrivilegeType.SYSTEM);
        privilegeService.addPrivilege("Read Resource Type and Category","Allows to get information about Resource Types and Categories", PrivilegeType.SYSTEM);
        privilegeService.addPrivilege("Review requests history","Allows to review history of requests to create Resource Type or Category", PrivilegeType.SYSTEM);
        privilegeService.addPrivilege("Request management","Allows to change request status or delete request", PrivilegeType.SYSTEM);
        privilegeService.addPrivilege("Look up","Allows to look up resources information from database", PrivilegeType.SYSTEM);
        privilegeService.addPrivilege("Manage Resource records","General privilege that allows to perform edit, delete, update operations for Resource records", PrivilegeType.SYSTEM);
        privilegeService.addPrivilege("Read Resource records","General privilege that allows to get Resource records from DB. Additionally resource type privileges should be configured", PrivilegeType.SYSTEM);
        privilegeService.addPrivilege("Access to resources management panel","General privilege that allows to enter to Resource panel", PrivilegeType.SYSTEM);
        privilegeService.addPrivilege("Access to administration panel","General privilege that allows to enter to Administration panel", PrivilegeType.SYSTEM);


        //adding type and property privileges from existing instantiated resource types and their properties
        for (ResourceType item: resourceTypeService.getTypes()) {
            if (item.isInstantiated()){
                String c = /*"type:"+*/item.getTableName()+":create";
                String r = /*"type:"+*/item.getTableName()+":read";
                String u = /*"type:"+*/item.getTableName()+":update";
                String d = /*"type:"+*/item.getTableName()+":delete";
                privilegeService.addPrivilege(c,PrivilegeType.RESOURCE_TYPE);
                privilegeService.addPrivilege(r,PrivilegeType.RESOURCE_TYPE);
                privilegeService.addPrivilege(u,PrivilegeType.RESOURCE_TYPE);
                privilegeService.addPrivilege(d,PrivilegeType.RESOURCE_TYPE);

                for (ConstrainedProperty property: item.getProperties()) {
                    String rp = /*"property:" + */item.getTableName() + ":" + property.getProperty().getColumnName()+":read";
                    String up = /*"property:" + */item.getTableName() + ":" + property.getProperty().getColumnName() + ":update";
                    privilegeService.addPrivilege(rp,PrivilegeType.PROPERTY);
                    privilegeService.addPrivilege(up,PrivilegeType.PROPERTY);
                }

            }
        }
    }
    private void addTestRoles(){
        roleService.deleteAllRoles();
        List<Privilege> allPrivileges = privilegeService.getAllPrivileges();
        //ROLE_ADMIN all privileges
        Role role1 = new Role();
        role1.setName("ROLE_ADMIN");
        role1.setDescription("Admin with all privileges");
        role1.setPrivileges(allPrivileges);
        roleService.updateRole(role1);

        Role role2 = new Role();
        role2.setName("ROLE_RESOURCE_ADMIN");
        role2.setDescription("Resource admin is responsible for managing resource type and their properties and managing requests from registrars");
        List<Privilege> resAdminPrivileges = new ArrayList<>();
        resAdminPrivileges.add(privilegeService.getPrivilegeByName("Read Resource Type and Category"));
        resAdminPrivileges.add(privilegeService.getPrivilegeByName("Look up"));
        resAdminPrivileges.add(privilegeService.getPrivilegeByName("Manage Resource Types and Categories"));
        resAdminPrivileges.add(privilegeService.getPrivilegeByName("Review requests history"));
        resAdminPrivileges.add(privilegeService.getPrivilegeByName("Request management"));
        resAdminPrivileges.add(privilegeService.getPrivilegeByName("Access to resources management panel"));
        for (Privilege x : allPrivileges){
            if (x.getPrivilegeType().equals(PrivilegeType.RESOURCE_TYPE) || x.getPrivilegeType().equals(PrivilegeType.PROPERTY)){
                resAdminPrivileges.add(x);
            }
        }
        role2.setPrivileges(resAdminPrivileges);
        roleService.updateRole(role2);

        Role role3 = new Role();
        role3.setName("ROLE_REGISTRAR");
        role3.setDescription("Registrar is responsible for managing resource records");
        List<Privilege> registrarPrivileges = new ArrayList<>();
        registrarPrivileges.add(privilegeService.getPrivilegeByName("Read Resource Type and Category"));
        registrarPrivileges.add(privilegeService.getPrivilegeByName("Manage Resource Types and Categories"));
        registrarPrivileges.add(privilegeService.getPrivilegeByName("Review requests history"));
        registrarPrivileges.add(privilegeService.getPrivilegeByName("Manage Resource records"));
        registrarPrivileges.add(privilegeService.getPrivilegeByName("Read Resource records"));
        registrarPrivileges.add(privilegeService.getPrivilegeByName("Look up"));
        registrarPrivileges.add(privilegeService.getPrivilegeByName("Access to resources management panel"));
        for (Privilege x : allPrivileges){
            if (x.getPrivilegeType().equals(PrivilegeType.RESOURCE_TYPE) || x.getPrivilegeType().equals(PrivilegeType.PROPERTY)){
                registrarPrivileges.add(x);
            }
        }
        role3.setPrivileges(registrarPrivileges);
        roleService.updateRole(role3);

        Role role4 = new Role();
        role4.setName("ROLE_SYSTEM_ADMIN");
        role4.setDescription("System admin is responsible for users, roles and privileges management");
        List<Privilege> sysAdminPrivileges = new ArrayList<>();
        sysAdminPrivileges.add(privilegeService.getPrivilegeByName("Read users information"));
        sysAdminPrivileges.add(privilegeService.getPrivilegeByName("Read Resource Type and Category"));
        sysAdminPrivileges.add(privilegeService.getPrivilegeByName("Read roles"));
        sysAdminPrivileges.add(privilegeService.getPrivilegeByName("Create and update roles"));
        sysAdminPrivileges.add(privilegeService.getPrivilegeByName("Access to administration panel"));
        role4.setPrivileges(sysAdminPrivileges);
        roleService.updateRole(role4);

        Role role5 = new Role();
        role5.setName("ROLE_USER");
        role5.setDescription("Regular user with basic privileges");
        List<Privilege> userPrivileges = new ArrayList<>();
        userPrivileges.add(privilegeService.getPrivilegeByName("Read Resource Type and Category"));
        userPrivileges.add(privilegeService.getPrivilegeByName("Read Resource records"));
        userPrivileges.add(privilegeService.getPrivilegeByName("Look up"));
        role5.setPrivileges(userPrivileges);
        roleService.updateRole(role5);
    }
    private void addTestUsers(){

        User newUser1 = userService.findByEmail("user");
        if (newUser1 == null){
            newUser1 = new User();
            newUser1.setRoles(new HashSet<>());
        }
        newUser1.setUsername("user");
        newUser1.setEnabled(true);
        newUser1.setPassword(encoder.encode("123"));

        newUser1.getRoles().add(roleService.findByName("ROLE_USER"));
        userService.saveRegisteredUser(newUser1);

        User newUser2 = userService.findByEmail("admin");
        if (newUser2 == null){
            newUser2 = new User();
            newUser2.setRoles(new HashSet<>());
        }
        newUser2.setUsername("admin");
        newUser2.setEnabled(true);
        newUser2.setPassword(encoder.encode("123"));
        newUser2.getRoles().add(roleService.findByName("ROLE_ADMIN"));
        userService.saveRegisteredUser(newUser2);

        User newUser3 = userService.findByEmail("system@admin");
        if (newUser3 == null){
            newUser3 = new User();
            newUser3.setRoles(new HashSet<>());
        }
        newUser3.setUsername("system@admin");
        newUser3.setEnabled(true);
        newUser3.setPassword(encoder.encode("123"));
        newUser3.getRoles().add(roleService.findByName("ROLE_SYSTEM_ADMIN"));
        userService.saveRegisteredUser(newUser3);

        User newUser4 = userService.findByEmail("resource@admin");
        if (newUser4 == null){
            newUser4 = new User();
            newUser4.setRoles(new HashSet<>());
        }
        newUser4.setUsername("resource@admin");
        newUser4.setEnabled(true);
        newUser4.setPassword(encoder.encode("123"));
        newUser4.getRoles().add(roleService.findByName("ROLE_RESOURCE_ADMIN"));
        userService.saveRegisteredUser(newUser4);

        User newUser5  = userService.findByEmail("registrar");
        if (newUser5 == null){
            newUser5 = new User();
            newUser5.setRoles(new HashSet<>());
        }
        newUser5.setUsername("registrar");
        newUser5.setEnabled(true);
        newUser5.setPassword(encoder.encode("123"));
        newUser5.getRoles().add(roleService.findByName("ROLE_REGISTRAR"));
        userService.saveRegisteredUser(newUser5);
    }

}