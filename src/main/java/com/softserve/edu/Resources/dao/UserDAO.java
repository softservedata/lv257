package com.softserve.edu.Resources.dao;

import com.softserve.edu.Resources.entity.User;

import java.util.List;

public interface UserDAO {

    User addUser(User user);

    User findByEmail(String email);

    User findById(long id);

    void delete(User user);

    List<User> getAllUsers();
}
