package com.softserve.edu.Resources.dao;

import com.softserve.edu.Resources.dto.UserProfileDTO;
import com.softserve.edu.Resources.entity.UserDetails;

import java.util.Optional;

public interface UserProfileDAO extends GenericDAO<UserDetails, Long>{
    Optional<UserDetails> findByEmail(String email);

//    void save(UserProfileDTO userDetails);
    void saveProfile(UserDetails userDetails);

//    UserDetails makePersistent(UserProfileDTO userProfileDTO);
}



