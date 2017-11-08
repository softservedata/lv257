package com.softserve.edu.Resources.service;

import com.softserve.edu.Resources.dto.UserProfileDTO;
import com.softserve.edu.Resources.entity.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;

public interface UserProfileService {

    @Transactional
    UserProfileDTO createPasswordDTO(Principal principal);

    @Transactional
    UserProfileDTO createUserProfileDTO(Principal principal);

    @Transactional
    UserDetails getUserDetailsByDTO(UserProfileDTO userProfileDTO);

    @Transactional
    UserProfileDTO getUserProfileByEmail(String email);

    UserProfileDTO getUserProfileByUserId(Long id);

    @Transactional
    void saveUserProfile(UserProfileDTO userDetailsDTO);
}
