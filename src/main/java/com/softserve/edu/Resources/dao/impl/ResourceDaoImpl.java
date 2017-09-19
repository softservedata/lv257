package com.softserve.edu.Resources.dao.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.softserve.edu.Resources.dao.ResourceDao;
import com.softserve.edu.Resources.entity.GenericResource;
import com.softserve.edu.Resources.entity.PropertyValue;
import com.softserve.edu.Resources.entity.ResourceProperty;

@Repository
public class ResourceDaoImpl implements ResourceDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<GenericResource> findResourcesByResourceType(String sqlQuery, Map<String, String> valuesToSearch,
            List<ResourceProperty> resourceProperties) {

        List<GenericResource> genResList = new ArrayList<GenericResource>();

        Object[] args = new Object[valuesToSearch.size()];

        int i = 0;

        for (Map.Entry<String, String> entry : valuesToSearch.entrySet()) {
            args[i++] = entry.getValue();
        }

        int[] argsTypes = new int[valuesToSearch.size()];
        i = 0;
        for (Map.Entry<String, String> entry : valuesToSearch.entrySet()) {
            for (ResourceProperty property : resourceProperties) {
                if (property.getColumnName().equals(entry.getKey())) {
                    argsTypes[i++] = property.getValueType().getSqlType(); /* if it works?*/
                    break;
                }
            }

        }
        i = 0;

        List<Map<String, Object>> genResRows = jdbcTemplate.queryForList(sqlQuery, args, argsTypes);

        for (Map<String, Object> mapRow : genResRows) {
            GenericResource genResource = new GenericResource();

            genResource.setId(Integer.parseInt(String.valueOf(mapRow.get("id"))));
            genResource.setId_Address(Integer.parseInt(String.valueOf(mapRow.get("id_Address"))));

            Set<PropertyValue> propertyValues = new HashSet<>();

            for (ResourceProperty property : resourceProperties) {
                PropertyValue propertyValue = new PropertyValue(property,
                        String.valueOf(mapRow.get(property.getColumnName())));
                propertyValues.add(propertyValue);
            }

            genResource.setPropertyValues(propertyValues);

            genResList.add(genResource);
        }

        return genResList;

    }

}
