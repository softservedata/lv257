/*
package com.softserve.edu.Resources.dao.impl;

import com.google.gson.Gson;
import com.softserve.edu.Resources.config.DBConfig;
import com.softserve.edu.Resources.dao.UserDetailsDAO;
import com.softserve.edu.Resources.entity.User;
import com.softserve.edu.Resources.entity.UserDetails;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {UserDetailsDAOImplTest.TestPersistenceConfigs.class})
public class UserDetailsDAOImplTest {

    @PersistenceContext
    private EntityManager entityManager;

    private UserDetailsDAO userDetailsDao;

    @Before
    public void init(){
        userDetailsDao = new UserDetailsDAOImpl(entityManager);

    }

    @Test
    @Transactional
    public void testFindAll(){
        List<UserDetails> initialUsers = userDetailsDao.getAllUserDetails();
        assertTrue(initialUsers.isEmpty());
        System.out.println("============TEST=============");
        // TODO: Can be static
        final User user1 = of("jonny300", "passWorD", "John", "Doe");
        final User user2 = of("anka", "passWorD","Anna", "Maria");
        final User user3 = of("j.travolta", "passWorD", "John", "Travolta");
        List<User> expectedUsers = Arrays.asList(user1, user2, user3);

        expectedUsers.forEach(u -> entityManager.persist(u));
        entityManager.flush();

        final List<UserDetails> actualUsers = userDetailsDao.getAllUserDetails();
        assertTrue(actualUsers.size() == 3);
        assertEquals(expectedUsers, actualUsers);
    }

    @Transactional
    @Test
    public void testFindById(){
        final User user1 = of("jonny300", "passWorD", "John", "Doe");

        entityManager.persist(user1);
        entityManager.flush();

        final Optional<UserDetails> actualUserDetails = userDetailsDao.findByUserId(user1.getId());
        assertNotNull(actualUserDetails);
        assertEquals(user1.getUserDetails(), actualUserDetails);
    }

    @After
    public void cleanup(){
        // TODO: check if clenup works correctly between test methods calls
        entityManager.createNativeQuery("TRUNCATE TABLE user_details;").executeUpdate();
        entityManager.createNativeQuery("TRUNCATE TABLE user_account;").executeUpdate();
    }

    public static User of(String username, String password, String firstName, String secondName){
        final User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        final UserDetails userDetails = new UserDetails();
        System.out.println("============TEST=============");
        userDetails.setFirstName(firstName);
        userDetails.setSecondName(secondName);

        user.setUserDetails(userDetails);

        return user;
    }


    // TODO: move to test utils and use it from all test DAO classes
    // TODO: check other beans whether it's properly to use them for tests
    @Configuration
    public static class TestPersistenceConfigs extends DBConfig {
        @Override
        public DataSource getDataSource() {
            EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
            builder.setType(EmbeddedDatabaseType.H2);
            return builder.build();
        }
    }
}*/
