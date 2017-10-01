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

    void createVerificationTokenForUser(User user, String token);

    VerificationToken getVerificationToken(String VerificationToken);

    void saveRegisteredUser(User user);
}
