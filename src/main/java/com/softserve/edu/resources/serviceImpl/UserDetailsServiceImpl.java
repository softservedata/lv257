package com.softserve.edu.resources.serviceImpl;


import com.softserve.edu.resources.dao.UserDetailsDAO;
import com.softserve.edu.resources.entity.User;
import com.softserve.edu.resources.service.UserDetailsService;

public class UserDetailsServiceImpl implements UserDetailsService {

    private UserDetailsDAO userDetailsDAO;

    public UserDetailsServiceImpl(UserDetailsDAO userDetailsDAO) {
        this.userDetailsDAO = userDetailsDAO;
    }

    public void updateUserDetails(User user) {
        userDetailsDAO.updateUserDetails(user);
    }
}
