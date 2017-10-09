package com.softserve.edu.Resources.dao;

import com.softserve.edu.Resources.entity.User;

import java.util.List;

public interface UserDAO {

    User makePersistent(User user);

    User findByEmail(String email);

    User findById(long id);

//    Optional<User> findById(Long id);

    void makeTransient(User user);

    List<User> getAllUsers();
}
