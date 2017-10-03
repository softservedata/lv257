package com.softserve.edu.Resources.dao;


import com.softserve.edu.Resources.entity.VerificationToken;

public interface VerificationTokenDAO {

    VerificationToken makePersistent (VerificationToken verificationToken);

    VerificationToken findByToken(String token);
}
