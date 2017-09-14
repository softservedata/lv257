package com.softserve.edu.Resources.dao;

import com.softserve.edu.Resources.entity.User;
import com.softserve.edu.Resources.entity.UserDetails;

import java.util.List;

public interface UserDetailsDAO {
    UserDetails getUser(User user);
    UserDetails findByEmail(String email);
    UserDetails findById(long id);

    void delete(User user);

    List<User> getAllUsers();
}
