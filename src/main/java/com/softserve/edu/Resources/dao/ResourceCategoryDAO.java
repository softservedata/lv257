package com.softserve.edu.Resources.dao;

import com.softserve.edu.Resources.entity.ResourceCategory;

import java.util.Optional;

public interface ResourceCategoryDAO extends GenericDAO<ResourceCategory, Long> {

    Optional<ResourceCategory> findByName(String name);
}
