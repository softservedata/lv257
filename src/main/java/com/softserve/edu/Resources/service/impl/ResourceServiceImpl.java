package com.softserve.edu.Resources.service.impl;

import com.softserve.edu.Resources.dao.ResourceDao;
import com.softserve.edu.Resources.dao.ResourceTypeDAO;
import com.softserve.edu.Resources.entity.GenericResource;
import com.softserve.edu.Resources.entity.ResourceProperty;
import com.softserve.edu.Resources.service.ResourceService;
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

//    public ResourceServiceImpl() {
//    }

    @Transactional
    @Override
    public List<GenericResource> findResourcesByResourceType(String query, String tableName,
            Map<String, String> valuesToSearch) {
        
        List<ResourceProperty> resourceProperties = new ArrayList<>(
                resourceTypeDAO.findWithPropertiesByTableName(tableName).getProperties());
        System.out.println(resourceProperties);

        return resourceDao.findResourcesByResourceType(query, valuesToSearch, resourceProperties);
    }
    
}
