package com.softserve.edu.services.impl;

import com.softserve.edu.dao.interfaces.UserDetailsDAO;
import com.softserve.edu.entities.User;
import com.softserve.edu.services.interfaces.UserDetailsService;

public class UserDetailsServiceImpl implements UserDetailsService {

    private UserDetailsDAO userDetailsDAO;

    public UserDetailsServiceImpl(UserDetailsDAO userDetailsDAO) {
        this.userDetailsDAO = userDetailsDAO;
    }

    public void updateUserDetails(User user) {
        userDetailsDAO.updateUserDetails(user);
    }
}
