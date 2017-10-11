package com.softserve.edu.Resources.service;

import com.softserve.edu.Resources.entity.User;
import com.softserve.edu.Resources.entity.UserDetails;

import java.util.List;
import java.util.Optional;

public interface UserDetailsService {

    public Optional<UserDetails> getUserDetailsByEmail(String email);

    public Optional<UserDetails> getUserDetailsByUserId(Long id);

//    public List<User> getAllUsersDetails();
}
