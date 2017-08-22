package com.softserve.edu.services;

import com.softserve.edu.dao.impl.UserDAO;
import com.softserve.edu.entities.User;

import java.util.List;

public class UserService {

    private UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void addUser(User user){
        userDAO.add(user);
    }

    public void updateUser(User user){
        userDAO.update(user);
    }

    public void deleteUserById(long id){
        userDAO.deleteById(id);
    }

    public void delete(User user) {
        userDAO.delete(user);
    }

    public User getById(Long id) {
        return userDAO.getById(id);
    }

    public List getAll() {
        return userDAO.getAll();
    }

    public List<User> getUserByFirstName(String firstName){

        return userDAO.getUserByFirstName(firstName);
    }

    public void updateRole(){

    }

    public void updatePass(){

    }
}
