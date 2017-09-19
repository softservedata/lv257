package com.softserve.edu.Resources.service.impl;

import com.softserve.edu.Resources.dao.RoleDAO;
import com.softserve.edu.Resources.dao.UserDetailsDAO;
import com.softserve.edu.Resources.entity.User;
import com.softserve.edu.Resources.entity.UserDetails;
import com.softserve.edu.Resources.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    UserDetailsDAO userDetailsDAO;
    @Autowired
    RoleDAO roleDAO;


    @Transactional
    public UserDetails getUserDetailsByEmail(String email){
        UserDetails userDetails = userDetailsDAO.findByEmail(email);
        System.out.println("Privileges extracted (commented)");

        return userDetails;
    }

    @Transactional
    public UserDetails getUserDetailsById (Long id){
        UserDetails userDetails = userDetailsDAO.findById(id);

        return userDetails;
    }

    public List<User> getAllUsersDetails(){
        System.out.println(userDetailsDAO.getAllUsers());

        return userDetailsDAO.getAllUsers();
    }
}
