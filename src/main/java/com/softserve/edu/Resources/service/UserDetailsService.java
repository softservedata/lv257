package com.softserve.edu.Resources.service;

import com.softserve.edu.Resources.dao.RoleDAO;
import com.softserve.edu.Resources.dao.UserDetailsDAO;
import com.softserve.edu.Resources.entity.User;
import com.softserve.edu.Resources.entity.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserDetailsService {

    @Autowired
    UserDetailsDAO userDetailsDAO;
    @Autowired
    RoleDAO roleDAO;


    @Transactional
    public UserDetails getUserByEmail(String email){
        UserDetails userDetails = userDetailsDAO.findByEmail(email);/*
        System.out.println("User extracted");
       Role role = user.getRole();
        System.out.println("Role extracted");*/
//        ArrayList<Privilege> privileges = new ArrayList<>(user.getRole().getPrivileges());
        System.out.println("Privileges extracted (commented)");
        return userDetails;
    }

    @Transactional
    public UserDetails getUserById (Long id){
        UserDetails userDetails = userDetailsDAO.findById(id);

        return userDetails;
    }

    public List<User> getAllUsers(){
        System.out.println(userDetailsDAO.getAllUsers());
        return userDetailsDAO.getAllUsers();
    }
/*
todo comment registerNewUserAccount at UserDetailsService
 */
//
//    public User registerNewUserAccount(final UserDTO userDTO) /*throws UserAlreadyExistException */{
//        /*if (emailExist(userDTO.getEmail())) {
//            //throw new UserAlreadyExistException("There is an account with that email adress: " + userDTO.getEmail());
//            throw new UserAlreadyExistException("There is an account with that email adress: " + userDTO.getEmail());
//        }*/
//        final User user = new User();
//
//        System.out.println("setting password from DTO");
//        user.setPassword(userDTO.getPassword());
//        user.setUsername(userDTO.getEmail());
//        //user.setUsing2FA(userDTO.isUsing2FA());
//        user.setRole(roleDAO.findByName("ROLE_USER"));
//        return userDAO.addUser(user);
//    }
//
    /*
todo comment emailExist at UserDetailsService
 */
//    private boolean emailExist(final String email) {
//        return userDAO.findByEmail(email) != null;
//    }

}
