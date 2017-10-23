package com.softserve.edu.Resources.service.impl;

import com.softserve.edu.Resources.dao.RoleDAO;
import com.softserve.edu.Resources.dao.UserDAO;
import com.softserve.edu.Resources.dao.VerificationTokenDAO;
import com.softserve.edu.Resources.dto.UserDTO;
import com.softserve.edu.Resources.entity.User;
import com.softserve.edu.Resources.entity.VerificationToken;
import com.softserve.edu.Resources.exception.UserAlreadyExistException;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    private static Long userId;
    private static Long nonExistingUserId;
    private static Long verificationTokenId;
    private static User user;
    private static User userForSpring;
    private static User userByEmail;
    private static User persistentUser;
    private static UserDTO userDTO;
    private static String email;
    private static String token;
    private static String password;
    private static List<User> allUsers;
    private static VerificationToken persistentVerificationToken;
    private static VerificationToken transientOrEmptyVerificationToken;

    @Mock
    private UserDAO userDAO;

    @Mock
    private RoleDAO roleDAO;

    @Mock
    private VerificationTokenDAO verificationTokenDAO;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeClass
    public static void setUp(){

        userId = 1L;
        nonExistingUserId = 10L;
        verificationTokenId = 20L;
        email = "a@gmail.com";
        token = UUID.randomUUID().toString();
        password = "123456";
        user = new User();
        userForSpring = new User()
                .setUsername(email)
                .setRoles(new ArrayList<>());
        userByEmail = new User();
        persistentUser = new User()
                .setId(userId)
                .setUsername(email)
                .setVerificationToken(new VerificationToken())
                .setRoles(new ArrayList<>())
                .setPassword(password);
        userDTO = new UserDTO();
        userDTO.setEmail(email);
        userDTO.setPassword(password);
        allUsers = new ArrayList<>();
        persistentVerificationToken = new VerificationToken(token)
                .setId(verificationTokenId);
        transientOrEmptyVerificationToken = new VerificationToken();
    }

    @Test
    public void getUserForSpringTest (){
        when(userDAO.findByEmail(email)).thenReturn(userForSpring);
        assertEquals(userService.getUserForSpring(email), userForSpring);
        verify(userDAO, times(1)).findByEmail(email);
    }

    @Test
     public void getUserByIdTest (){

        when(userDAO.findById(userId)).thenReturn(Optional.ofNullable(user));
        assertEquals(userService.getUserById(userId), user);
        verify(userDAO, times(1)).findById(userId);
    }

    @Test
    public void getUserByIdTestNull (){

        Optional<User> emptyUser = Optional.ofNullable(new User());
        when(userDAO.findById(nonExistingUserId)).thenReturn(emptyUser);
        assertEquals(userService.getUserById(nonExistingUserId), emptyUser.get());
        verify(userDAO, times(1)).findById(nonExistingUserId);
    }

    @Test
    public void findByEmailTest() {

        when(userDAO.findByEmail(email)).thenReturn(userByEmail);
        assertEquals(userService.findByEmail(email), userByEmail);
        verify(userDAO, times(1)).findByEmail(email);
    }

    @Test
    public void getAllUsersTest(){

        when(userDAO.getAllUsers()).thenReturn(allUsers);
        assertEquals(userService.getAllUsers(), allUsers);
        verify(userDAO, times(1)).getAllUsers();
    }

    @Test
    public void registerNewUserAccountTest() throws UserAlreadyExistException {

        when(userDAO.makePersistent(any(User.class))).thenReturn(persistentUser);

        User savedUser = userService.registerNewUserAccount(userDTO);
        assertEquals(savedUser, persistentUser);
        verify(userDAO, times(1)).makePersistent(persistentUser);
    }

    @Test
    public void deleteTest() {

        userService.delete(user);
        verify(userDAO, times(1)).makeTransient(user);
    }

    @Test
    public void createVerificationTokenForUserTest() {

        when(verificationTokenDAO.makePersistent(any(VerificationToken.class))).thenReturn(persistentVerificationToken);

        VerificationToken savedVerificationToken = userService.createVerificationTokenForUser(user, token);

        assertEquals(persistentVerificationToken, savedVerificationToken);
        assertEquals(savedVerificationToken.getId(), verificationTokenId);
        verify(verificationTokenDAO, times(1)).makePersistent(any(VerificationToken.class));
    }

    @Test
    public void getVerificationTokenTest() {

        when(verificationTokenDAO.findByToken(token)).thenReturn(transientOrEmptyVerificationToken);
        assertEquals(userService.getVerificationToken(token), transientOrEmptyVerificationToken);
        verify(verificationTokenDAO, times(1)).findByToken(token);
    }

    @Test
    public void deleteVerificationTokenTest() {

        userService.deleteVerificationToken(persistentVerificationToken);
        verify(verificationTokenDAO, times(1)).makeTransient(persistentVerificationToken);
    }
}
