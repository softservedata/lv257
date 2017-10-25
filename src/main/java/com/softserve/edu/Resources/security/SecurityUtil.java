package com.softserve.edu.Resources.security;

import com.softserve.edu.Resources.entity.*;
import com.softserve.edu.Resources.service.ResourceTypeService;
import com.softserve.edu.Resources.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Service
public class SecurityUtil {

    @Autowired
    UserService userService;
    @Autowired
    ResourceTypeService resourceTypeService;

    public List<ConstrainedProperty> filterSearchableProperties(ResourceType resourceType){

        List<ConstrainedProperty> filteredData = new ArrayList<>();
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userService.findByEmail(currentUserName);
        Collection<Role> roles = user.getRoles();
        Collection<Privilege> privileges = new HashSet<>();

        //getting all privileges for current Resource Type
        for (Role x : roles){
            for (Privilege y : x.getPrivileges()){
                if (y.getPrivilegeType().equals(PrivilegeType.PROPERTY) && y.getName().substring(0,y.getName().indexOf(':')+1).equals(resourceType.getTableName()))
                    privileges.add(y);
            }
        }

        for (ConstrainedProperty cp : resourceTypeService.getSearchableProperties(resourceType)){
            for (Privilege item : privileges){
                String[] cards = item.getName().split(":");
                if (cards[2].equals("read") && cp.getProperty().getColumnName().equals(cards[1])){
                    filteredData.add(cp);
                }

            }
        }


        return filteredData;
    }
}
