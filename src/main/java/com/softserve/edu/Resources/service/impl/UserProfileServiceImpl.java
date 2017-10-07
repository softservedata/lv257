package com.softserve.edu.Resources.service.impl;

import com.softserve.edu.Resources.dao.RoleDAO;
import com.softserve.edu.Resources.dao.UserDetailsDAO;
import com.softserve.edu.Resources.dto.UserProfileDTO;
import com.softserve.edu.Resources.entity.UserDetails;
import com.softserve.edu.Resources.service.UserDetailsService;
import com.softserve.edu.Resources.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    UserDetailsDAO userDetailsDAO;
//    @Autowired
//    UserProfileDTO userProfileDTO;
    @Autowired
    RoleDAO roleDAO;


    @Transactional
    public UserProfileDTO getUserProfileByEmail(String email){
        UserProfileDTO userProfileDTO = new UserProfileDTO();

        System.out.println("Privileges extracted (commented)");

        return userProfileDTO;
    }

    @Transactional
    public UserProfileDTO getUserProfileByUserId(Long id){
        UserProfileDTO userProfileDTO = new UserProfileDTO();
//        userProfileDTO.setFirstName();

//        userProfileDTO.setFirstName(user.getFirstName(id));
        return userProfileDTO;
    }

    @Override
    public void saveOrUpdate(UserProfileDTO userProfileDTO) {
        userProfileDTO.setFirstName(userProfileDTO.getFirstName());
        System.out.println("======UserDetailsServiceImpl=========");
        System.out.println(userProfileDTO);

    }

}
