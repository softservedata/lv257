package com.softserve.edu.dao.impl;

import com.softserve.edu.entities.Features;
import org.hibernate.Session;

public class FeaturesDAO extends crudDAOImpl<Features> {
    public FeaturesDAO(Session session) {
        super(Features.class, session);
    }
}
