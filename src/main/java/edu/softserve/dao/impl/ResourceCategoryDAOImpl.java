package edu.softserve.dao.impl;

import edu.softserve.dao.ResourceCategoryDAO;
import edu.softserve.entity.ResourceCategory;
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
        Query query = entityManager.createQuery("select i from ResourceCategory i where i.name = :name")
                .setParameter("name", name);
        return (ResourceCategory) query.getSingleResult();
    }
}
