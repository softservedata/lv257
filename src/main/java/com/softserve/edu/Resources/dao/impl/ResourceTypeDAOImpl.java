package com.softserve.edu.Resources.dao.impl;

import com.softserve.edu.Resources.dao.ResourceTypeDAO;
import com.softserve.edu.Resources.entity.ResourceProperty;
import com.softserve.edu.Resources.entity.ResourceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Optional;

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
    public List<String> getInstanceNames() {
        String queryInstanceNames = "select rp.typeName from RestourceType rp where rp.instantiated = true";
        return em.createQuery(queryInstanceNames, String.class).getResultList();
    }

    @Override
    public List<ResourceType> getInstances() {
        String queryInstance = "select rp from RestourceType rp where rp.instantiated = true";
        return em.createQuery(queryInstance, ResourceType.class).getResultList();
    }
}
