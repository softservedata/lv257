package com.softserve.edu.Resources.dao;

import com.softserve.edu.Resources.entity.ResourceCategory;

public interface ResourceCategoryDAO extends GenericDAO<ResourceCategory, Long> {

    ResourceCategory findByName(String name);
}
