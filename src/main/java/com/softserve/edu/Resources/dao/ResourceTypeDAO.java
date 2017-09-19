package com.softserve.edu.Resources.dao;

import com.softserve.edu.Resources.entity.ResourceType;

public interface ResourceTypeDAO extends GenericDAO<ResourceType, Long> {

    ResourceType findByName(String name);
}
