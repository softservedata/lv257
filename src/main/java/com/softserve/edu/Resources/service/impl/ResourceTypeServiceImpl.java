package com.softserve.edu.Resources.service.impl;

import com.softserve.edu.Resources.dao.ResourceTypeDAO;
import com.softserve.edu.Resources.dto.ConstrainedPropertyBrief;
import com.softserve.edu.Resources.dto.ResourceTypeUpdate;
import com.softserve.edu.Resources.entity.ConstrainedProperty;
import com.softserve.edu.Resources.entity.ResourceCategory;
import com.softserve.edu.Resources.entity.ResourceProperty;
import com.softserve.edu.Resources.entity.ResourceType;
import com.softserve.edu.Resources.exception.InvalidResourceCategoryException;
import com.softserve.edu.Resources.exception.InvalidResourceTypeException;
import com.softserve.edu.Resources.service.PropertyService;
import com.softserve.edu.Resources.service.ResourceCategoryService;
import com.softserve.edu.Resources.service.ResourceTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.invoke.MethodHandles;
import java.util.*;
import java.util.stream.Collectors;

@Service("resourceTypeService")
@Transactional
public class ResourceTypeServiceImpl implements ResourceTypeService {

    public static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getName());

    private final ResourceTypeDAO resourceTypeDAO;

    private final ResourceCategoryService resourceCategoryService;

    private final PropertyService propertyService;

    @Autowired
    public ResourceTypeServiceImpl(ResourceTypeDAO resourceTypeDAO,
                                   ResourceCategoryService resourceCategoryService,
                                   PropertyService propertyService) {
        this.resourceTypeDAO = resourceTypeDAO;
        this.resourceCategoryService = resourceCategoryService;
        this.propertyService = propertyService;
    }

    @Override
    public List<ResourceType> getTypes() {
        return resourceTypeDAO.findAll();
    }

    @Override
    public long getTypeCount() {
        return resourceTypeDAO.getCount();
    }

    @Override
    public ResourceType save(ResourceTypeUpdate resourceTypeUpdate) {
        long id = resourceTypeUpdate.getId();
        ResourceType resourceType;
        if (id == 0) {
            resourceType = new ResourceType();
        } else {
            Optional<ResourceType> existentResourceType = resourceTypeDAO.findById(id);
            if (!existentResourceType.isPresent())
                throw new InvalidResourceTypeException(String.format("ResourceType with id \"%d\" not found", id));
            resourceType = existentResourceType.get();
        }

        long categoryId = resourceTypeUpdate.getCategoryId();
        Optional<ResourceCategory> category = resourceCategoryService.findCategoryById(categoryId);

        if (!category.isPresent())
            throw new InvalidResourceCategoryException(String.format("Category \"%s\" not found", categoryId));

        resourceType.setTypeName(resourceTypeUpdate.getTypeName())
                .setTableName(resourceTypeUpdate.getTableName())
                .setCategory(category.get());

        Set<Long> updatedPropertyIDs = resourceTypeUpdate.getProperties().stream()
                                               .map(ConstrainedPropertyBrief::getId)
                                               .collect(Collectors.toSet());

        Set<Long> availablePropertyIDs = propertyService.getPropertyIDs();
        Set<Long> validIDs = new HashSet<>(updatedPropertyIDs);

        validIDs.removeIf(propId -> !availablePropertyIDs.contains(propId));

        if (availablePropertyIDs.size() != validIDs.size()) {
            resourceTypeUpdate.getProperties().removeIf(cpb -> !validIDs.contains(cpb.getId()));
            LOGGER.warn("A few invalid properties requested - %s", updatedPropertyIDs.removeAll(validIDs));
        }

        Set<ResourceProperty> updatedTypeProperties = validIDs.stream()
                                                              .map(propertyService::getPropertyById)
                                                              .filter(Optional::isPresent)
                                                              .map(Optional::get)
                                                              .collect(Collectors.toSet());

        Set<ConstrainedProperty> typeProperties
                = resourceTypeUpdate.getProperties().stream()
                          .map(cpb -> new ConstrainedProperty()
                                              .setProperty(propertyService.getPropertyById(cpb.getId()).get())
                                              .setRequired(cpb.isRequired())
                                              .setSearchable(cpb.isSearchable()))
                          .collect(Collectors.toSet());

        resourceType.setProperties(typeProperties);
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
    public List<ConstrainedProperty> getSearchableProperties(ResourceType resourceWithProperties) {
        return resourceWithProperties.getProperties().stream()
                       .filter(ConstrainedProperty::isSearchable)
                       .collect(Collectors.toList());
    }

}
