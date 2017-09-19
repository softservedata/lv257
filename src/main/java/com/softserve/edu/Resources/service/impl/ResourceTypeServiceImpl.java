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
    public void remove(ResourceType resourceType) {
        resourceTypeDAO.makeTransient(resourceType);
    }

    @Override
    public Optional<ResourceType> get(String typeName) {
        return resourceTypeDAO.findByName(typeName);
    }

    @Override
    public void create(String typeName) {
        /*ResourceType type = types.get(typeName);
        if (type != null&& !instances.containsKey(typeName)) {
            System.out.printf("Creating ResourceTable '%s':%n", type.getName());
            type.getProperties().forEach(rp -> System.out.printf("adding field '%s'%n", rp.getTitle()));
            System.out.println("done.");instances.put(type, type.getTableName());
        }*/

    }

    @Transactional
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

    @Transactional
    @Override
    public ResourceType findWithPropertiesByID(Long ID) {
        return resourceTypeDAO.findWithPropertiesByID(ID);
    }

    @Override
    public List<ResourceProperty> getSpecialResourcePropertiesByResType(ResourceType resourceWithProperties) {
        List<ResourceProperty> essentialProperties = new ArrayList<>();

        for (ResourceProperty resourceProperty : resourceWithProperties.getProperties()) {
            if (resourceProperty.isEssential()) {
                essentialProperties.add(resourceProperty);
            }
        }

        return essentialProperties;
    }

   

    @Override
    public void testHello() {
        System.out.println("Try test");
    }
    
    
    
    

}
