package edu.softserve.dao;

import edu.softserve.entity.ResourceType;

public interface ResourceTypeDAO extends GenericDAO<ResourceType, Long> {

    ResourceType findByName(String name);
}
