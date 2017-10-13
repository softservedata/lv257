package com.softserve.edu.Resources.service.impl;

import com.softserve.edu.Resources.dao.ResourceDao;
import com.softserve.edu.Resources.dao.ResourceTypeDAO;
import com.softserve.edu.Resources.dto.GenericResourceDTO;
import com.softserve.edu.Resources.entity.ConstrainedProperty;
import com.softserve.edu.Resources.entity.GenericResource;
import com.softserve.edu.Resources.entity.ResourceType;
import com.softserve.edu.Resources.service.ResourceService;
import com.softserve.edu.Resources.util.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    ResourceTypeDAO resourceTypeDAO;

    @Autowired
    ResourceDao resourceDao;

    @Autowired
    QueryBuilder queryBuilder;

    @Transactional
    @Override
    public List<GenericResource> findResourcesByResourceType(GenericResourceDTO resourceDTO) {

        long resourceTypeId = resourceDTO.getId();

        ResourceType resourceType = resourceTypeDAO.findWithPropertiesByID(resourceTypeId);

        String tableName = resourceType.getTableName();

        List<ConstrainedProperty> resourceProperties = new ArrayList<>(resourceType.getProperties());

        Map<String, String> valuesToSearch = resourceDTO.getResourcePropertyValue();

        String queryForDao = queryBuilder.lookUpByResouceType(tableName, valuesToSearch, resourceProperties);

        return resourceDao.findResourcesByResourceType(queryForDao, valuesToSearch, resourceProperties);
    }

}
