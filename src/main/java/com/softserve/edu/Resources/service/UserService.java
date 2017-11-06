package com.softserve.edu.Resources.service;


import com.softserve.edu.Resources.dto.UserDTO;
import com.softserve.edu.Resources.entity.User;
import com.softserve.edu.Resources.entity.VerificationToken;
import com.softserve.edu.Resources.exception.UserAlreadyExistException;

import java.util.List;

public interface UserService {

    User getUserForSpring (String email);

    User getUserById (Long id);

    User findByEmail(String email);

    List<User> getAllUsers();

    User registerNewUserAccount(final UserDTO userDTO) throws UserAlreadyExistException;

    void delete(User user);

    VerificationToken createVerificationTokenForUser(User user, String token);

    VerificationToken getVerificationToken(String verificationToken);

    void saveRegisteredUser(User user);

    void deleteVerificationToken(VerificationToken verificationToken);

    List<User> getUsersWithRoles(String... roleNames);

    boolean emailExist(final String email);

    User getUser(String username);
}
