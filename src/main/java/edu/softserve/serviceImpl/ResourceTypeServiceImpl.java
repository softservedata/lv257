package edu.softserve.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.softserve.dao.ResourceTypeDAO;

import edu.softserve.entity.GenericResource;
import edu.softserve.entity.ResourceProperty;
import edu.softserve.entity.ResourceType;
import edu.softserve.service.ResourceTypeService;

@Service
public class ResourceTypeServiceImpl implements ResourceTypeService {

    @Autowired
    private ResourceTypeDAO resourceTypeDao;

    @Override
    @Transactional
    public List<ResourceType> findAll() {
        return resourceTypeDao.findAll();
    }

    @Override
    @Transactional
    public ResourceType findWithPropertiesByID(int resourceTypeID) {
        return resourceTypeDao.findWithPropertiesByID(resourceTypeID);
    }

    @Override
    public List<ResourceProperty> getSpecialResourceProperties(ResourceType resourceWithProperties) {

        List<ResourceProperty> essentialProperties = new ArrayList<>();

        for (ResourceProperty resourceProperty : resourceWithProperties.getProperties()) {
            if (resourceProperty.isEssential()) {
                essentialProperties.add(resourceProperty);
            }
        }

        return essentialProperties;
    }

    @Override
    @Transactional
    public List<GenericResource> findResourcesByResourceType(String query, String tableName,
            Map<String, String> valuesToSearch) {

        List<ResourceProperty> resourceProperties = (List<ResourceProperty>) resourceTypeDao
                .findWithPropertiesByTableName(tableName).getProperties();

        

        return resourceTypeDao.findResourcesByResourceType(query, valuesToSearch, resourceProperties);

    }
    
    public void testHello(){
        System.out.println("Try test");
    }

}
