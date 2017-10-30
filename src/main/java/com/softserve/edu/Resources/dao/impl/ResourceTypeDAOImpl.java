package com.softserve.edu.Resources.dao.impl;

import com.softserve.edu.Resources.dao.ResourceTypeDAO;
import com.softserve.edu.Resources.entity.ResourceProperty;
import com.softserve.edu.Resources.entity.ResourceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.lang.invoke.MethodHandles;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

        ResourceType resourceType = null;
        try {
            resourceType = (ResourceType) em.createQuery("SELECT r FROM ResourceType r LEFT JOIN FETCH r.properties WHERE r.id =:id")
                    .setParameter("id", resourceTypeID).getSingleResult();
        } catch (NoResultException e) {

            String warn = "No resourceType found with Id:" + resourceTypeID + "";

            LOGGER.warn(warn, e);

        }

        return resourceType;
    }

    @Override
    public Optional<ResourceType> findByTypeName(String typeName) {
        final String queryByTypeName = "select i from ResourceType i where i.typeName = :name";
        return querySingleResult(queryByTypeName, "name", typeName);
    }

    @Override
    public Optional<ResourceType> findById(Long id, boolean doFetch) {
        if (!doFetch)
            return super.findById(id);
        // fetch with loadgraph hint
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.loadgraph",  em.getEntityGraph("TypesProperties"));
        return Optional.ofNullable(em.find(ResourceType.class, id, properties));
    }
}
