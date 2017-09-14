package edu.softserve.dao;

import edu.softserve.entity.ResourceCategory;

public interface ResourceCategoryDAO extends GenericDAO<ResourceCategory, Long> {

    ResourceCategory findByName(String name);
}
