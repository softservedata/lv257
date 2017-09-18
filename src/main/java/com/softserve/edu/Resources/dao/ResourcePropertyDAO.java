package com.softserve.edu.Resources.dao;

import com.softserve.edu.Resources.entity.ResourceProperty;

import java.util.List;
import java.util.Optional;

/**
 *
 */
public interface ResourcePropertyDAO extends GenericDAO<ResourceProperty, Long>{

    Optional<ResourceProperty> findByTitleAndUnits(String title, String units);

    Optional<ResourceProperty> findByDescription(String description);

    List<ResourceProperty> findByTitle(String title);

    List<ResourceProperty> findByUnits(String title);
}
