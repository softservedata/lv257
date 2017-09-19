package com.softserve.edu.Resources.dao.impl;

import com.softserve.edu.Resources.dao.ResourceCategoryDAO;
import com.softserve.edu.Resources.entity.ResourceCategory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Optional;

@Repository
public class ResourceCategoryDAOImpl
        extends GenericDAOImpl<ResourceCategory, Long> implements ResourceCategoryDAO {
    @PersistenceContext
    EntityManager entityManager;

    public ResourceCategoryDAOImpl() {
        super(ResourceCategory.class);
    }

    @Override
    public Optional<ResourceCategory> findByName(String name) {
        Query query = entityManager.createQuery("select rc from ResourceCategory rc where rc.categoryName = :name")
                .setParameter("name", name);
        return Optional.ofNullable((ResourceCategory)query.getSingleResult());
    }
}
