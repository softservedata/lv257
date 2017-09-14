package com.softserve.edu.Resources.dao.impl;

import com.softserve.edu.Resources.entity.ResourceType;
import com.softserve.edu.Resources.dao.ResourceTypeDAO;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class ResourceTypeDAOImpl extends GenericDAOImpl<ResourceType, Long> implements ResourceTypeDAO {
    @PersistenceContext
    EntityManager entityManager;

    public ResourceTypeDAOImpl() {
        super(ResourceType.class);
    }

    @Override
    public ResourceType findByName(String name) {
        Query query = entityManager.createQuery("select i from ResourceType i where i.name = :name")
                .setParameter("name", name);
        return (ResourceType) query.getSingleResult();
    }
}
