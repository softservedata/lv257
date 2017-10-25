package com.softserve.edu.Resources.service.impl;


import com.softserve.edu.Resources.dao.RoleDAO;
import com.softserve.edu.Resources.dao.UserDAO;
import com.softserve.edu.Resources.dao.VerificationTokenDAO;
import com.softserve.edu.Resources.dto.UserDTO;
import com.softserve.edu.Resources.entity.*;
import com.softserve.edu.Resources.exception.UserAlreadyExistException;
import com.softserve.edu.Resources.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    private VerificationTokenDAO verificationTokenDAO;

    @Transactional
    public User getUserForSpring (String email){
        User user = userDAO.findByEmail(email);
        ArrayList<Privilege> privileges = new ArrayList<>();
        for (Role role: user.getRoles()) {
            privileges.addAll(role.getPrivileges());
        }
        return user;
    }

    public User getUserById (Long id){

        Optional<User> userOptional = userDAO.findById(id);

        User user = null;
        if (userOptional.isPresent()) {
            user = userOptional.get();
        }
        return user;
    }

    @Override
    public User findByEmail(String email) {
        return userDAO.findByEmail(email);
    }

    public List<User> getAllUsers(){
        List<User> users = userDAO.getAllUsers();
        return users;
    }

    public User registerNewUserAccount(final UserDTO userDTO) throws UserAlreadyExistException {

        if (emailExist(userDTO.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email adress: " + userDTO.getEmail());
        }

        final User user = new User();
        user.setRoles(new ArrayList<>());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setUsername(userDTO.getEmail());
        user.getRoles().add(roleDAO.findByName("ROLE_USER"));
        user.setUserDetails(new UserDetails());
        return userDAO.makePersistent(user);
    }

    @Override
    public void delete(User user) {
        userDAO.makeTransient(user);
    }

    @Override
    public VerificationToken createVerificationTokenForUser(User user, String token) {
        final VerificationToken myToken = new VerificationToken(token, user);
        return verificationTokenDAO.makePersistent(myToken);
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

    @Override
    @Transactional
    public List<User> getUsersWithRoles(String... roleNames) {
        return userDAO.getUsersWithRoles(roleNames);
    }
}