package com.softserve.edu.Resources.dao;

import com.softserve.edu.Resources.entity.UserDetails;

import java.util.Optional;

public interface UserDetailsDAO {
    Optional<UserDetails> findByEmail(String email);
    Optional<UserDetails> findByUserId(long id);
    void save(UserDetails userDetails);
}
