package com.softserve.edu.Resources.dao;

import com.softserve.edu.Resources.entity.UserDetails;

import java.util.Optional;

public interface UserDetailsDAO {
//    UserDetails getUser(User user);
    Optional<UserDetails> findByEmail(String email);
    UserDetails findByUserId(long id);

//    void delete(User user);

//    List<User> getAllUsers();

    void save(UserDetails userDetails);
}
