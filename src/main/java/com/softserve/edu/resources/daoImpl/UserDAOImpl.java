package com.softserve.edu.resources.daoImpl;


import com.softserve.edu.resources.dao.UserDAO;
import com.softserve.edu.resources.entity.User;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserDAOImpl implements UserDAO {

    private SessionFactory sessionFactory;

    public UserDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addUser(User user) {

    }

    public void updateUser(User user) {

    }

    public void deleteUser(User user) {

    }

    public User getUser(String email) {
        return null;
    }

    public List<User> getAllUsers() {
        return null;
    }

    public void updateRole() {

    }
}
