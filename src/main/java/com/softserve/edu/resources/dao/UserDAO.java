package com.softserve.edu.resources.dao;



import com.softserve.edu.resources.entity.User;

import java.util.List;

public interface UserDAO {

    void addUser(User user);
    void updateUser(User user);
    void deleteUser(User user);
    User getUser(String email);
    List<User> getAllUsers();
}
