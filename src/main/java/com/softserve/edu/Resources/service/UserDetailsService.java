package com.softserve.edu.Resources.service;

import com.softserve.edu.Resources.entity.User;
import com.softserve.edu.Resources.entity.UserDetails;

import java.util.List;

public interface UserDetailsService {

    public UserDetails getUserDetailsByEmail(String email);

    public UserDetails getUserDetailsById (Long id);

    public List<User> getAllUsersDetails();
}
