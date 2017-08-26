package com.softserve.edu.dao.interfaces;

import com.softserve.edu.entities.User;

import java.util.List;

public interface UserDAO {

    void addUser(User user);
    void updateUser(User user);
    void deleteUser(User user);
    User getUser(String email);
    List<User> getAllUsers();
}
