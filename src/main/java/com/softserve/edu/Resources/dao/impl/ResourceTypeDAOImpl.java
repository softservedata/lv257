package com.softserve.edu.Resources.dao.impl;

import com.softserve.edu.Resources.dao.ResourceTypeDAO;
import com.softserve.edu.Resources.entity.GenericResource;
import com.softserve.edu.Resources.entity.PropertyValue;
import com.softserve.edu.Resources.entity.ResourceProperty;
import com.softserve.edu.Resources.entity.ResourceType;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.sql.DataSource;

@Repository("resourceTypeDAO")
public class ResourceTypeDAOImpl extends GenericDAOImpl<ResourceType, Long> implements ResourceTypeDAO {

    static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getName());


    public ResourceTypeDAOImpl() {
        super(ResourceType.class, LOGGER);
    }

    @Override
    public Optional<ResourceType> findByName(String name) {
        final String queryByName = "select i from ResourceType i where i.name = :name";
        return querySingleResult(queryByName, "name", name);
    }

    @Override
    public List<ResourceType> findByProperty(ResourceProperty property) {
        return null;
    }

    @Override
    public void create(ResourceType resourceType) {

    }

    @Override
    public List<String> getInstanceNames() {
        String queryInstanceNames = "select rp.typeName from ResourceType rp where rp.instantiated = true";
        return em.createQuery(queryInstanceNames, String.class).getResultList();
    }

    @Override
    public List<ResourceType> getInstances() {
        String queryInstance = "select rp from ResourceType rp where rp.instantiated = true";
        return em.createQuery(queryInstance, ResourceType.class).getResultList();
    }



    @Override
    public ResourceType findWithPropertiesByID(Long resourceTypeID) {
        return (ResourceType) em.createQuery("SELECT r FROM ResourceType r LEFT JOIN FETCH r.properties WHERE r.id =:id")
                .setParameter("id", resourceTypeID).getSingleResult();
    }


    
    
    
    
    
}
