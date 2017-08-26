package com.softserve.edu.resources.service;


import com.softserve.edu.resources.entity.User;

import java.util.List;

public interface UserService {

    void addUser(User user);

    void updateUser(User user);

    void deleteUser(User user);

    User getUser(String email);

    List<User> getAllUsers();
}
