package com.softserve.edu.Resources.service.impl;


import com.softserve.edu.Resources.dao.RoleDAO;
import com.softserve.edu.Resources.dao.UserDAO;
import com.softserve.edu.Resources.dao.VerificationTokenDAO;
import com.softserve.edu.Resources.dto.UserDTO;
import com.softserve.edu.Resources.entity.Privilege;
import com.softserve.edu.Resources.entity.User;
import com.softserve.edu.Resources.entity.UserDetails;
import com.softserve.edu.Resources.entity.VerificationToken;
import com.softserve.edu.Resources.exception.UserAlreadyExistException;
import com.softserve.edu.Resources.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDAO userDAO;

    @Autowired
    RoleDAO roleDAO;

    @Autowired
    private VerificationTokenDAO verificationTokenDAO;

//    @Transactional
    public User getUserForSpring (String email){
        User user = userDAO.findByEmail(email);
        ArrayList<Privilege> privileges = new ArrayList<>(user.getRole().getPrivileges());
        System.out.println("Privileges extracted");
        return user;
    }

    public User getUserById (Long id){
        User user = userDAO.findById(id);
        return user;
    }

//    public User getUserById (Long id){
//
//        Optional<User> userOptional = userDAO.findById(id);
//        if (userOptional.isPresent()) {
//            User user = userOptional.get();
//        }
//        return user;
//    }

    @Override
    public User findByEmail(String email) {
        return userDAO.findByEmail(email);
    }

    public List<User> getAllUsers(){
        System.out.println(userDAO.getAllUsers());
        return userDAO.getAllUsers();
    }

    public User registerNewUserAccount(final UserDTO userDTO) throws UserAlreadyExistException {

        if (emailExist(userDTO.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email adress: " + userDTO.getEmail());
        }

        final User user = new User();

        System.out.println("setting password from DTO");
        user.setPassword(userDTO.getPassword());
        user.setUsername(userDTO.getEmail());
        user.setRole(roleDAO.findByName("ROLE_USER"));
        user.setUserDetails(new UserDetails());
        return userDAO.makePersistent(user);
    }

    @Override
    public void delete(User user) {
        userDAO.makeTransient(user);
    }

    @Override
    public void createVerificationTokenForUser(User user, String token) {
        final VerificationToken myToken = new VerificationToken(token, user);
        verificationTokenDAO.makePersistent(myToken);
    }

    @Override
    public VerificationToken getVerificationToken(String token) {
        return verificationTokenDAO.findByToken(token);
    }

    @Override
    public void saveRegisteredUser(User user) {
        userDAO.makePersistent(user);
    }

    @Override
    public void deleteVerificationToken(VerificationToken verificationToken) {
        verificationTokenDAO.makeTransient(verificationToken);
    }


    private boolean emailExist(final String email) {

        User user = userDAO.findByEmail(email);

        if (user != null){
            return true;
        }
        return false;
    }

}
