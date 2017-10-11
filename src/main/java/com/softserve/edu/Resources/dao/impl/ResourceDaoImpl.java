package com.softserve.edu.Resources.dao.impl;

import com.softserve.edu.Resources.dao.ResourceDao;
import com.softserve.edu.Resources.entity.ConstrainedProperty;
import com.softserve.edu.Resources.entity.GenericResource;
import com.softserve.edu.Resources.entity.PropertyValue;
import com.softserve.edu.Resources.entity.ResourceProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.*;

@Repository
public class ResourceDaoImpl implements ResourceDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<GenericResource> findResourcesByResourceType(String sqlQuery, Map<String, String> valuesToSearch,
                                                             List<ConstrainedProperty> resourceProperties) {

        List<GenericResource> genResList = new ArrayList<GenericResource>();
        List<Map<String, Object>> genResRows = new ArrayList<>();

        if (!valuesToSearch.isEmpty()) {

            Object[] args = new Object[valuesToSearch.size()];

            args = valuesToSearch.values().toArray();

            int[] argsTypes = new int[valuesToSearch.size()];
            int i = 0;
            for (String keyValue : valuesToSearch.keySet()) {
                for (ConstrainedProperty constrainedProperty : resourceProperties) {
                    ResourceProperty property = constrainedProperty.getProperty();
                    if (property.getColumnName().equals(keyValue)) {
                        argsTypes[i++] = property.getValueType().sqlType;
                        break;
                    }
                }

            }
            i = 0;

            genResRows = jdbcTemplate.queryForList(sqlQuery, args, argsTypes);

        } else {
            genResRows = jdbcTemplate.queryForList(sqlQuery);
        }

        // it also possible to make with named parameters jdbcTemplate

        for (Map<String, Object> mapRow : genResRows) {
            GenericResource genResource = new GenericResource();

            genResource.setId(Integer.parseInt(String.valueOf(mapRow.get("id"))));
            genResource.setId_Address(Integer.parseInt(String.valueOf(mapRow.get("id_address"))));

            Set<PropertyValue> propertyValues = new TreeSet<>();

            for (ConstrainedProperty constrainedProperty : resourceProperties) {
                String value = String.valueOf(mapRow.get(constrainedProperty.getProperty().getColumnName()));
                PropertyValue propertyValue = new PropertyValue(constrainedProperty, value);
                propertyValues.add(propertyValue);
            }

            genResource.setPropertyValues(propertyValues);

            genResList.add(genResource);
        }

        return genResList;

    }

}
