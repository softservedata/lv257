package edu.softserve.dao;

import edu.softserve.entity.User;

public interface UserDAO {

    User findByEmail(String email);

    void delete(User user);
}
