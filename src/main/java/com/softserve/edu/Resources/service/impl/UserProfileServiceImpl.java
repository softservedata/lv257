package com.softserve.edu.Resources.service.impl;

import com.softserve.edu.Resources.dao.RoleDAO;
import com.softserve.edu.Resources.dao.UserDAO;
import com.softserve.edu.Resources.dao.UserDetailsDAO;
import com.softserve.edu.Resources.dto.UserProfileDTO;
import com.softserve.edu.Resources.entity.User;
import com.softserve.edu.Resources.entity.UserDetails;
import com.softserve.edu.Resources.service.UserDetailsService;
import com.softserve.edu.Resources.service.UserProfileService;
import com.softserve.edu.Resources.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.Optional;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    UserDetailsDAO userDetailsDAO;
    @Autowired
    UserDAO userDAO;
    @Autowired
    RoleDAO roleDAO;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public Optional<UserDetails> getUserDetailsByDTO(UserProfileDTO userProfileDTO) {
        long id = userProfileDTO.getId();
        Optional<UserDetails> createdUserDetailsEntity = userDetailsDAO.findByUserId(id);
        createdUserDetailsEntity.get().setFirstName(userProfileDTO.getFirstName());

        return createdUserDetailsEntity;
    }

    @Override
    @Transactional
    public UserProfileDTO createUserProfileDTO(Principal principal){
        String userName = principal.getName();
        User user = userService.findByEmail(userName);
        Optional<UserDetails> details = userDetailsService.getUserDetailsByUserId(user.getId());
        UserProfileDTO userProfileDTO = new UserProfileDTO();
        userProfileDTO.setId(details.get().getId());
        userProfileDTO.setFirstName(details.get().getFirstName());
        userProfileDTO.setMiddleName(details.get().getMiddleName());
        userProfileDTO.setSecondName(details.get().getSecondName());
        userProfileDTO.setPassportNumber(details.get().getPassportNumber());
        userProfileDTO.setPassportSeries(details.get().getPassportSeries());
        userProfileDTO.setPhone(details.get().getPhone());
        userProfileDTO.setIdAddress(details.get().getIdAddress());
        userProfileDTO.setBankId(details.get().getBankId());
/*        userProfileDTO.setIssuedBy(details.get().getIssuedBy());

        int year = 2017;
        int month = 10;
        int day = 1;
        GregorianCalendar calendar = new GregorianCalendar(year, month, day);
        Date date = calendar.getTime();
        userProfileDTO.setDateOfIssue(date);
//        userProfileDTO.setDateOfIssue(details.get().getDateOfIssue());*/

        userProfileDTO.setUser(details.get().getUser());
        System.out.println();
        return userProfileDTO;
    }
    @Transactional
    public UserProfileDTO getUserProfileByEmail(String email){
        UserProfileDTO userProfileDTO = new UserProfileDTO();

        System.out.println("Privileges extracted (commented)");

        return userProfileDTO;
    }

    @Transactional
    public UserProfileDTO getUserProfileByUserId(Long id){
        UserProfileDTO userProfileDTO = new UserProfileDTO();
//        userProfileDTO.setFirstName("String");
//        userProfileDTO.getFirstName(user.getFirstName(id));
        return userProfileDTO;
    }

    @Override
    @Transactional
    public void saveUserProfile(UserProfileDTO userProfileDTO) {

        Optional<UserDetails> userDetails = getUserDetailsByDTO(userProfileDTO);

        userDetails.get().setFirstName(userProfileDTO.getFirstName());
        userDetails.get().setMiddleName(userProfileDTO.getMiddleName());
        userDetails.get().setSecondName(userProfileDTO.getSecondName());
        userDetails.get().setPassportNumber(userProfileDTO.getPassportNumber());
        userDetails.get().setPassportSeries(userProfileDTO.getPassportSeries());
        userDetails.get().setPhone(userProfileDTO.getPhone());
        userDetails.get().setIdAddress(userProfileDTO.getIdAddress());
        userDetails.get().setBankId(userProfileDTO.getBankId());
        userDetails.get().setUser(userDAO.findById(userProfileDTO.getId()));
    }
}
