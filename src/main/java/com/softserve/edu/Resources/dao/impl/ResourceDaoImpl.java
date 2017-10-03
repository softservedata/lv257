package com.softserve.edu.Resources.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
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
        List<Map<String, Object>> genResRows = new ArrayList<>();

        if (!valuesToSearch.isEmpty()) {

            Object[] args = new Object[valuesToSearch.size()];

            args = valuesToSearch.values().toArray();

            int[] argsTypes = new int[valuesToSearch.size()];
            int i = 0;
            for (String keyValue : valuesToSearch.keySet()) {
                for (ResourceProperty property : resourceProperties) {
                    if (property.getColumnName().equals(keyValue)) {
                        argsTypes[i++] = property.getValueType().getSqlType();
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
