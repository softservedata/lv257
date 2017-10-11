package com.softserve.edu.Resources.service;

import com.softserve.edu.Resources.dto.UserProfileDTO;
import com.softserve.edu.Resources.entity.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.Optional;

public interface UserProfileService {

    @Transactional
    Optional<UserDetails> getUserDetailsByDTO(UserProfileDTO userProfileDTO);
//    UserDetails createUserDetailsEntity(UserProfileDTO userProfileDTO);

    @Transactional
    UserProfileDTO createUserProfileDTO(Principal principal);

//    public UserProfileDTO createUserProfileDTO(long id);

    @Transactional
    UserProfileDTO getUserProfileByEmail(String email);

    UserProfileDTO getUserProfileByUserId(Long id);

//    void saveOrUpdate(UserProfileDTO userProfileDTO);

    @Transactional
    void saveUserProfile(UserProfileDTO userDetailsDTO);
}
