package com.softserve.edu.Resources.service;

import com.softserve.edu.Resources.dto.UserProfileDTO;
import com.softserve.edu.Resources.entity.UserDetails;

import java.util.Optional;

public interface UserProfileService {

    UserProfileDTO getUserProfileByEmail(String email);

    UserProfileDTO getUserProfileByUserId(Long id);

    void saveOrUpdate(UserProfileDTO userProfileDTO);

}
