package com.softserve.edu.Resources.service.impl;

import com.softserve.edu.Resources.service.ResourceService;
import com.softserve.edu.Resources.service.ResourceTypeService;
import com.softserve.edu.Resources.dao.ResourceDao;
import com.softserve.edu.Resources.dao.ResourceTypeDAO;
import com.softserve.edu.Resources.entity.GenericResource;

import com.softserve.edu.Resources.entity.ResourceProperty;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ResourceServiceImpl implements ResourceService {

//    private Collection<GenericResource> resources = new HashSet<>();
//    private ResourceTypeService typeService = new ResourceTypeServiceImpl();
    
    @Autowired
    ResourceTypeDAO resourceTypeDAO;
    
    @Autowired
    ResourceDao resourceDao;

    public ResourceServiceImpl() {
    }

//    @Override
//    public Collection<GenericResource> getResources() {
//        return Collections.unmodifiableCollection(resources);
//    }

   

//    @Override
//    public Collection<ResourceType> getResourceTypes() {
//        return resources.stream().map(GenericResource::getType).collect(Collectors.toSet());
//    }

//    @Override
//    public GenericResource addResource(ResourceType resourceType, Set<PropertyValue> resourceValues) {
//        typeService.add(resourceType);
//        GenericResource resource = new GenericResource(resourceType, resourceValues);
//        resources.add(resource);
//        return resource;
//    }
    
    @Transactional
    @Override
    public List<GenericResource> findResourcesByResourceType(String query, String tableName,
            Map<String, String> valuesToSearch) {
        
        List<ResourceProperty> resourceProperties = new ArrayList<>(
                resourceTypeDAO.findWithPropertiesByTableName(tableName).getProperties());

        return resourceDao.findResourcesByResourceType(query, valuesToSearch, resourceProperties);
    }
    
}
