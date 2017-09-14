package com.softserve.edu.Resources.dao.impl;

import com.softserve.edu.Resources.dao.ResourceCategoryDAO;
import com.softserve.edu.Resources.entity.ResourceCategory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class ResourceCategoryDAOImpl
        extends GenericDAOImpl<ResourceCategory, Long> implements ResourceCategoryDAO {
    @PersistenceContext
    EntityManager entityManager;

    public ResourceCategoryDAOImpl() {
        super(ResourceCategory.class);
    }

    @Override
    public ResourceCategory findByName(String name) {
        Query query = entityManager.createQuery("select i from ResourceCategory i where i.categoryName = :name")
                .setParameter("name", name);
        return (ResourceCategory) query.getSingleResult();
    }
}
