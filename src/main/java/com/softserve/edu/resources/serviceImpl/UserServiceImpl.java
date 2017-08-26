package com.softserve.edu.services.impl;

import com.softserve.edu.dao.interfaces.UserDAO;
import com.softserve.edu.entities.User;
import com.softserve.edu.services.interfaces.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }


    public void addUser(User user) {
        userDAO.addUser(user);
    }

    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    public void deleteUser(User user) {

        userDAO.deleteUser(user);
    }

    public User getUser(String email) {
        return userDAO.getUser(email);
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }
}
