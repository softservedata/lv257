package com.softserve.edu.resources.dao.impl;

import org.hibernate.Session;

import com.softserve.edu.resources.entities.Features;

public class FeaturesDAO extends crudDAOImpl<Features> {
    public FeaturesDAO(Session session) {
        super(Features.class, session);
    }
}
