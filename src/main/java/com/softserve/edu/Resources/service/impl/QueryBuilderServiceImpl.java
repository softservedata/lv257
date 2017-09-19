package com.softserve.edu.Resources.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.edu.Resources.dao.ResourceTypeDAO;
import com.softserve.edu.Resources.entity.ResourceProperty;
import com.softserve.edu.Resources.entity.ResourceType;
import com.softserve.edu.Resources.service.QueryBuilderService;

@Service
public class QueryBuilderServiceImpl implements QueryBuilderService {

    @Autowired
    ResourceTypeDAO resourceTypeDao;

    @Transactional
    @Override
    public String lookUpByResouceType(String tableName, Map<String, String> valuesToSearch) {

        ResourceType resourceType = resourceTypeDao.findWithPropertiesByTableName(tableName);

        List<ResourceProperty> allResourceProperties = new ArrayList<>(resourceType.getProperties());

        System.out.println(allResourceProperties);

        StringBuilder createQuery = new StringBuilder();
        createQuery.append("SELECT gr.id, gr.id_Address");

        createQuery.append(allResourceProperties.isEmpty() ? " " : ", ");

        Iterator<ResourceProperty> iter = allResourceProperties.iterator();
        while (iter.hasNext()) {
            createQuery.append("gr." + iter.next().getColumnName() + "");
            if (iter.hasNext()) {
                createQuery.append(", ");
            } else {
                createQuery.append(" ");
            }

        }

        createQuery.append("FROM " + tableName + " gr");
        createQuery.append(" WHERE ");

        Iterator<Map.Entry<String, String>> entries = valuesToSearch.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, String> entry = entries.next();
            createQuery.append("gr." + entry.getKey() + " = ?");
            if (entries.hasNext()) {
                createQuery.append(" AND ");
            }
        }

        return createQuery.toString();
    }

}
