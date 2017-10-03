package com.softserve.edu.Resources.dao;

import com.softserve.edu.Resources.dto.UserProfileDTO;
//import com.softserve.edu.Resources.entity.UserDetails;

import java.util.Optional;

public interface UserProfileDAO extends GenericDAO<UserProfileDTO, Long>{
    Optional<UserProfileDTO> findByEmail(String email);

    void saveUserProfile(UserProfileDTO userProfileDTO);
};



