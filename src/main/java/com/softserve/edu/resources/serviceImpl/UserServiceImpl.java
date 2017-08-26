package com.softserve.edu.resources.serviceImpl;



import com.softserve.edu.resources.dao.UserDAO;
import com.softserve.edu.resources.entity.User;
import com.softserve.edu.resources.service.UserService;

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
