package edu.softserve.dao;

import edu.softserve.entity.User;

import java.util.List;

public interface UserDetailsDAO {
    User getUser(User user);
    User findByEmail(String email);
    User findById(long id);

    void delete(User user);

    List<User> getAllUsers();
}
