package com.softserve.edu.Resources.service.impl;

import com.softserve.edu.Resources.dao.ResourceDao;
import com.softserve.edu.Resources.dao.ResourceTypeDAO;
import com.softserve.edu.Resources.dto.GenericResourceDTO;
import com.softserve.edu.Resources.dto.GroupedResourceCount;
import com.softserve.edu.Resources.dto.ResourceImplDTO;
import com.softserve.edu.Resources.dto.ValidationErrorDTO;
import com.softserve.edu.Resources.entity.*;
import com.softserve.edu.Resources.exception.ResourceNotFoundException;
import com.softserve.edu.Resources.service.OwnerService;
import com.softserve.edu.Resources.service.ResourceService;
import com.softserve.edu.Resources.util.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    ResourceTypeDAO resourceTypeDAO;

    @Autowired
    ResourceDao resourceDao;

    @Autowired
    OwnerService ownerService;

    @Autowired
    QueryBuilder queryBuilder;

    @Transactional
    @Override
    public List<GenericResource> findResourcesByResourceType(GenericResourceDTO resourceDTO)
            throws ResourceNotFoundException {

        long resourceTypeId = resourceDTO.getId();

        ResourceType resourceType = resourceTypeDAO.findWithPropertiesByID(resourceTypeId);

        if (resourceType == null) {
            throw new ResourceNotFoundException("No infromation was found by your request");
        }

        String tableName = resourceType.getTableName();

        List<ConstrainedProperty> resourceProperties = new ArrayList<>(resourceType.getProperties());

        Map<String, String> valuesToSearch = resourceDTO.getResourcePropertyValues();

        

        /*
         * validation of keyValues: do such columnNames exists for this resource
         * type
         */
        // TODO: use security service to get allowed properties to search for
        // the client
        Set<String> propertyColumnNames = valuesToSearch.keySet();
        boolean columnNameExist = false;
        for (String columnName : propertyColumnNames) {
            for (ConstrainedProperty constrainedProperty : resourceProperties) {
                if (columnName.equalsIgnoreCase(constrainedProperty.getProperty().getColumnName())) {
                    columnNameExist = true;
                    break;
                } else {
                    columnNameExist = false;
                }
            }
            if (!columnNameExist) {
                throw new ResourceNotFoundException("You've requested wrong data.");
            }
        }

        /*
         * validation of client inputs: do they matches with pattern for
         * concrete resource property
         */
        boolean match = false;
        for (Map.Entry<String, String> columnNamesAndInputs : valuesToSearch.entrySet()) {
            for (ConstrainedProperty constrainedProperty : resourceProperties) {
                if (columnNamesAndInputs.getKey().equalsIgnoreCase(constrainedProperty.getProperty().getColumnName())) {
                    match = columnNamesAndInputs.getValue().matches(constrainedProperty.getProperty().getPattern());
                    break;
                }
            }
            if (!match) {
                throw new ResourceNotFoundException("You've requested wrong data.");
            }
        }

        String queryForDao = queryBuilder.queryForJdbcTemplate(tableName, valuesToSearch, resourceProperties);

        return resourceDao.findResourcesByResourceType(queryForDao, valuesToSearch, resourceProperties);
    }

    @Transactional
    @Override
    public List<GroupedResourceCount> findResourcesCountGroupedByResourceTypeForOwner(String ownerId) {

        return resourceDao.findResourcesCountGroupedByResourceTypeForOwner(Long.parseLong(ownerId));

    }

    @Transactional
    @Override
    public List<GenericResource> findResourcesByOwnerAndType(long ownerId, String resourceTypeName)
            throws ResourceNotFoundException {

        List<Long> resourcesIds = resourceDao.findResourcesIdsByOwner(ownerId, resourceTypeName);

        Optional<ResourceType> resourceType = resourceTypeDAO.findByTypeName(resourceTypeName);

        String tableName;
        if (resourceType.isPresent()) {
            tableName = resourceType.get().getTableName();
        } else {
            throw new ResourceNotFoundException("You've requested wrong data.");
        }
        
        List<ConstrainedProperty> resourceProperties = new ArrayList<>(resourceType.get().getProperties());
        
        System.out.println(resourceProperties);

        String sqlQuery = queryBuilder.namedQueryForLookingByResourcesIds(tableName, resourceProperties);
        
        return resourceDao.findResourcesByOwnerAndResourcesType(sqlQuery, resourceProperties, resourcesIds);
    }
    
    @Transactional
    public GenericResourceDTO findResourceByTypeAndId(long resourceTypeId, long resourceId){
        
        ResourceType resourceType = resourceTypeDAO.findWithPropertiesByID(resourceTypeId);

        if (resourceType == null) {
            throw new ResourceNotFoundException("No infromation was found by your request");
        }

        String tableName = resourceType.getTableName();

        List<ConstrainedProperty> resourceProperties = new ArrayList<>(resourceType.getProperties());
        
        String sqlQuery = queryBuilder.namedQueryForLookingByResourceId(tableName, resourceProperties);
        
        GenericResourceDTO genericResource = resourceDao.findById(resourceId, sqlQuery, resourceProperties);
        
        genericResource.setOwners(
                resourceDao.getOwnersForGenericResourceByResourceTypeAndResource(resourceTypeId, resourceId));
        
        genericResource.setAddress(resourceDao.findAddressForGenericResourceByResourceId(resourceId));
        
        return genericResource;
    }
    

    @Transactional
    @Override
    public void addResource(Resource resource) {
        resourceDao.addResource(resource);
    }

    @Transactional
    @Override
    public void addResourceOwnings(Resource resource, ResourceImplDTO resourceImplDTO) {
        long[] ownerIds = resourceImplDTO.getOwnerIds();
        Arrays.stream(ownerIds)
                .forEach(ownerId -> {
                    Owner ownerById = ownerService.getOwnerById(ownerId);
                    Optional<ResourceType> resourceTypeById = resourceTypeDAO.findById(resourceImplDTO.getResourceTypeId());
                    ResourceOwning resourceOwning = new ResourceOwning();
                    resourceOwning.setOwner(ownerById);
                    resourceOwning.setResourceType(resourceTypeById.get());
                    resourceOwning.setResource(resource);

                    resourceDao.addResourceOwning(resourceOwning);
                });
    }

    @Transactional
    @Override
    public void addResourceImpl(Resource resource, ResourceType resourceType, Map<String, String> propertiesAndValues) {
        String readyQuery = queryBuilder.buildInsertResourceImplQuery(resourceType, propertiesAndValues);
        System.out.println(readyQuery);
        resourceDao.addResourceImpl(readyQuery, resourceType, resource.getId(),propertiesAndValues);
    }

    @Override
    public ValidationErrorDTO validateResourceImpl(ResourceImplDTO resourceImplDTO) {
        ValidationErrorDTO validationErrorDTO = new ValidationErrorDTO();

        Map<String, String> propertiesAndValues = resourceImplDTO.getPropertiesAndValues();
        long resourceTypeId = resourceImplDTO.getResourceTypeId();
        ResourceType resourceTypeWithProperties = resourceTypeDAO.findWithPropertiesByID(resourceTypeId);

        Set<ConstrainedProperty> properties = resourceTypeWithProperties.getProperties();

        properties.forEach(constrainedProperty -> {
            String columnName = constrainedProperty.getProperty().getColumnName();
            String columnValue = propertiesAndValues.get(columnName);

            if ((columnValue == null || columnValue.isEmpty()) && constrainedProperty.isRequired()){
                validationErrorDTO.addFieldError(columnName, "This field is required");
            } else if(!columnValue.matches(constrainedProperty.getProperty().getPattern())){
                validationErrorDTO.addFieldError(columnName, "This field do not matches valid format");
            }
        });


        return validationErrorDTO;
    }
    
    
}
