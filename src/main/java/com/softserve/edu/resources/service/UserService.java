package com.softserve.edu.services.interfaces;


import com.softserve.edu.entities.User;

import java.util.List;

public interface UserService {

    void addUser(User user);

    void updateUser(User user);

    void deleteUser(User user);

    User getUser(String email);

    List<User> getAllUsers();
}
