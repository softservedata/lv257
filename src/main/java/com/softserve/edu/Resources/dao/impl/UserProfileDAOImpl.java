package com.softserve.edu.Resources.dao.impl;

//import edu.softserve.dao.UserDAO;
//import edu.softserve.entity.User;

//import com.softserve.edu.Resources.dao.UserDetailsDAO;
import com.softserve.edu.Resources.dao.UserProfileDAO;
import com.softserve.edu.Resources.entity.UserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.invoke.MethodHandles;
import java.util.Optional;

@Repository
@Qualifier("userProfileDAO")
@Transactional
public class UserProfileDAOImpl extends GenericDAOImpl<UserDetails, Long> implements UserProfileDAO {

    static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getName());

    @PersistenceContext
    private EntityManager entityManager;

    protected UserProfileDAOImpl() {
        super(UserDetails.class, LOGGER);
    }

    @Override
    public Optional<UserDetails> findByEmail(String email) {
        String queryUserDetails = "select i from User i where i.username = :username";
        return querySingleResult(queryUserDetails, "username", email);
    }



/*    public Optional<UserDetails> modifyUserDetails(UserDetails userDetails) {
        String queryUserDetails = "select i from User i where i.username = :username";
        return querySingleResult(queryUserDetails, "username", email);
    }*/

//    @Override
//    public void save(UserProfileDTO userProfileDTO) {
    public void saveProfile(UserDetails userDetails) {
        final UserDetails userDetailsToEntity = makePersistent(userDetails);
        System.out.println("===================save(UserProfileDTO userProfileDTO)========================");
        System.out.println(userDetails);
//        final UserProfileDTO userDetailsToEntity2 = makePersistent(userProfileDTO);
    }


/*    @Override
    public User addUser(User user) {
        entityManager.persist(user);
        return user;
    }*/

}