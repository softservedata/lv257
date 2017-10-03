package com.softserve.edu.Resources.dao.impl;

//import edu.softserve.dao.UserDAO;
//import edu.softserve.entity.User;

import com.softserve.edu.Resources.dao.UserDetailsDAO;
import com.softserve.edu.Resources.dao.UserProfileDAO;
import com.softserve.edu.Resources.dto.UserProfileDTO;
import com.softserve.edu.Resources.entity.UserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.lang.invoke.MethodHandles;
import java.util.Optional;

@Repository
@Qualifier("userDrtailsDAO")
@Transactional
public class UserProfileDAOImpl extends GenericDAOImpl<UserProfileDTO, Long> implements UserProfileDAO {

    static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getName());

    protected UserProfileDAOImpl() {
        super(UserProfileDTO.class, LOGGER);
    }

    @Override
//    public Optional<UserDetails> findByEmail(String email) {
    public Optional<UserProfileDTO> findByEmail(String email) {
        String queryUserProfile = "select i from User i where i.username = :username";
        return querySingleResult(queryUserProfile, "username", email);
    }

    @Override
//    public Optional<UserDetails> findByEmail(String email) {
    public Optional<UserProfileDTO> findById(String email) {
        String queryUserProfile = "select i from User i where i.username = :username";
        return querySingleResult(queryUserProfile, "username", email);
    }

//    @Override
    public void saveUserProfile(UserProfileDTO userProfileDTO) {
//        final UserDetails userDetailsToEntity1 = makePersistent(userProfileDTO);
        final UserProfileDTO userDetailsToEntity2 = makePersistent(userProfileDTO);
    }

}