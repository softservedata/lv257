package com.softserve.edu.Resources.service;

import com.softserve.edu.Resources.entity.UserDetails;

import java.util.Optional;

public interface UserDetailsService {

    Optional<UserDetails> getUserDetailsByEmail(String email);

    Optional<UserDetails> getUserDetailsByUserId(Long id);

    public void saveOrUpdate(UserDetails userDetails);

}
