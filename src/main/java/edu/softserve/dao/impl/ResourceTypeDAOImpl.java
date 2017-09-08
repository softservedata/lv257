package edu.softserve.dao.impl;

import edu.softserve.entity.ResourceType;
import org.springframework.stereotype.Repository;

@Repository
public class ResourceTypeDAOImpl extends GenericDAOImpl<ResourceType, Long> {
        public ResourceTypeDAOImpl() {
                super(ResourceType.class);
        }
}
