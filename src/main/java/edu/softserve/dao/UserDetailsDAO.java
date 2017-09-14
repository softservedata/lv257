package edu.softserve.dao;

import edu.softserve.entity.User;
import edu.softserve.entity.UserDetails;

import java.util.List;

public interface UserDetailsDAO {
    UserDetails getUser(User user);
    UserDetails findByEmail(String email);
    UserDetails findById(long id);

    void delete(User user);

    List<User> getAllUsers();
}
