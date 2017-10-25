package com.softserve.edu.Resources.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.softserve.edu.Resources.dao.ResourceDao;
import com.softserve.edu.Resources.dto.GroupedResourceCount;
import com.softserve.edu.Resources.entity.ConstrainedProperty;
import com.softserve.edu.Resources.entity.GenericResource;
import com.softserve.edu.Resources.entity.PropertyValue;
import com.softserve.edu.Resources.entity.ResourceProperty;

@Repository
public class ResourceDaoImpl implements ResourceDao {

    @PersistenceContext
    private EntityManager entityManager;

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

    @SuppressWarnings("unchecked")
    @Override
    public List<GroupedResourceCount> findResourcesCountGroupedByResourceTypeForOwner(Long ownerId) {

        return (List<GroupedResourceCount>) entityManager
                .createQuery("SELECT new com.softserve.edu.Resources.dto.GroupedResourceCount( "
                        + "ro.resourceType.typeName, COUNT(ro) ) FROM ResourceOwning ro WHERE ro.owner.id = :id "
                        + "GROUP BY ro.resourceType.typeName")
                .setParameter("id", ownerId).getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Long> findResourcesIdsByOwner(long ownerId, String resourceTypeName) {
        // TODO Auto-generated method stub
        return entityManager.createQuery("SELECT ro.resource.id FROM ResourceOwning ro "
                + "WHERE ro.resourceType.typeName = :resourceTypeName AND ro.owner.id = :id", Long.class)
                .setParameter("resourceTypeName", resourceTypeName).setParameter("id", ownerId).getResultList();
        
        
    }

    
    
    
}
