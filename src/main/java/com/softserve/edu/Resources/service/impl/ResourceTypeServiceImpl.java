package com.softserve.edu.Resources.service.impl;

import com.softserve.edu.Resources.dao.ResourceTypeDAO;
import com.softserve.edu.Resources.entity.ResourceProperty;
import com.softserve.edu.Resources.entity.ResourceType;
import com.softserve.edu.Resources.service.ResourceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service("resourceTypeService")
@Transactional
public class ResourceTypeServiceImpl implements ResourceTypeService {

    @Autowired
    ResourceTypeDAO resourceTypeDAO;

    @Override
    public List<ResourceType> getTypes() {
        return resourceTypeDAO.findAll();
    }

    @Override
    public long getTypeCount() {
        return resourceTypeDAO.getCount();
    }

    @Override
    public ResourceType add(ResourceType resourceType) {
        return resourceTypeDAO.makePersistent(resourceType);
    }

    @Override
    public ResourceType update(ResourceType resourceType) {
        return resourceTypeDAO.makePersistent(resourceType);
//        return resourceTypeDAO.update(resourceType);
    }

    @Override
    public void remove(ResourceType resourceType) {
        resourceTypeDAO.makeTransient(resourceType);
    }

    @Override
    public Optional<ResourceType> get(String typeName) {
        return resourceTypeDAO.findByName(typeName);
    }

    @Override
    public void create(String typeName) {
        final Optional<ResourceType> resourceType = resourceTypeDAO.findByName(typeName);
        resourceType.ifPresent(this::create);
    }

    @Override
    public void createBatch(List<String> typeNames) {
        typeNames.forEach(this::create);
    }

    @Override
    public void create(ResourceType type) {
        resourceTypeDAO.create(type);
    }

    @Override
    public List<ResourceType> getInstances() {
        return resourceTypeDAO.getInstances();
    }

    public ResourceTypeService instantiate(Map<ResourceType, String> instances) {
        return this;
    }

    @Override
    public int getInstancesCount() {
        return getInstances().size();
    }



    @Override
    public ResourceType findWithPropertiesByID(Long ID) {
        return resourceTypeDAO.findWithPropertiesByID(ID);
    }

    @Override
    public List<ResourceProperty> getSearchableProperties(ResourceType resourceWithProperties) {
        List<ResourceProperty> searchableProperties = new ArrayList<>();

        for (ResourceProperty resourceProperty : resourceWithProperties.getProperties()) {
            if (resourceProperty.isSearchable()) {
                searchableProperties.add(resourceProperty);
            }
        }

        return searchableProperties;
    }

   

    @Override
    public void testHello() {
        System.out.println("Yeah, it works!");
    }
    
    
    
    

}
