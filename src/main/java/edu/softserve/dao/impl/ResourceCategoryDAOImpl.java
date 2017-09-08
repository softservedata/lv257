package edu.softserve.dao.impl;

import edu.softserve.entity.ResourceCategory;
import org.springframework.stereotype.Repository;

@Repository
public class ResourceCategoryDAOImpl
        extends GenericDAOImpl<ResourceCategory, Long> {
        public ResourceCategoryDAOImpl() {
                super(ResourceCategory.class);
        }
}
