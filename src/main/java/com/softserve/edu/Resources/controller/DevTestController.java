package com.softserve.edu.Resources.controller;

import com.softserve.edu.Resources.entity.ConstrainedProperty;
import com.softserve.edu.Resources.entity.PrivilegeType;
import com.softserve.edu.Resources.entity.ResourceType;
import com.softserve.edu.Resources.entity.User;
import com.softserve.edu.Resources.service.*;
import com.softserve.edu.Resources.service.impl.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;


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
    ResourceCategoryService resourceCategoryService;

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

    @RequestMapping(value = "/deletedata", method = RequestMethod.GET)
    public String deleteData() {
        privilegeService.deleteAllPrivilege();
        return "devtest/welcome2";
    }

    @RequestMapping(value = "/testdata", method = RequestMethod.GET)
    public String testdata() {



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


        return "devtest/welcome2";
    }

    @RequestMapping( value = "/testsecurity", method = RequestMethod.GET )
    public String testsecurity(Model model, HttpServletRequest request)
    {


        //List<ResourceTypePrivilege> activeResTypePrivileges = genericPrivilegeService.getActiveResourceTypePrivileges(activeResType);

        String email = request.getUserPrincipal().getName();
        User user = userService.getUserForSpring(email);
        //Set<GenericPrivilege> userResTypePrivileges = user.getGenericPrivileges();


        //сравнить два сета и оставить только пересечения

        //разбить сет на три занести в дао и вернуть json

        model.addAttribute( "email", email);
        return "testpages/testSecurity";
    }

    @RequestMapping( value = "/testsecurity2", method = RequestMethod.GET )
    public String testsecurity2(Model model, HttpServletRequest request)
    {
        /*genericPrivilegeService.getSecuredResult(User.class);*/

        return "testpages/testSecurity";
    }
}