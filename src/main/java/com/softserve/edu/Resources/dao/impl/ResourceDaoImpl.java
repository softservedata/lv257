package com.softserve.edu.Resources.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import com.softserve.edu.Resources.entity.*;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.softserve.edu.Resources.dao.ResourceDao;
import com.softserve.edu.Resources.dto.GroupedResourceCount;

@Repository
public class ResourceDaoImpl implements ResourceDao {

    @PersistenceContext
    private EntityManager entityManager;

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private List<GenericResource> modelingGenericResourcesFromDb(List<Map<String, Object>> resultFromDB,
            List<ConstrainedProperty> resourceProperties) {

        List<GenericResource> resourcesList = new ArrayList<GenericResource>();
        for (Map<String, Object> mapRow : resultFromDB) {
            GenericResource genResource = new GenericResource();

            genResource.setId(Integer.parseInt(String.valueOf(mapRow.get("id"))));

            Set<PropertyValue> propertyValues = new TreeSet<>();

            for (ConstrainedProperty constrainedProperty : resourceProperties) {
                String value = String.valueOf(mapRow.get(constrainedProperty.getProperty().getColumnName()));
                PropertyValue propertyValue = new PropertyValue(constrainedProperty, value);
                propertyValues.add(propertyValue);
            }

            genResource.setPropertyValues(propertyValues);

            resourcesList.add(genResource);
        }

        return resourcesList;
    }

    @Override
    public List<GenericResource> findResourcesByResourceType(String sqlQuery, Map<String, String> valuesToSearch,
            List<ConstrainedProperty> resourceProperties) {

        List<Map<String, Object>> resultFromDB = new ArrayList<>();

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

            resultFromDB = jdbcTemplate.queryForList(sqlQuery, args, argsTypes);

        } else {
            resultFromDB = jdbcTemplate.queryForList(sqlQuery);
        }

        // it also possible to make with named parameters jdbcTemplate
        List<GenericResource> resourcesList = modelingGenericResourcesFromDb(resultFromDB, resourceProperties);

        return resourcesList;

    }

    @Override
    public List<GenericResource> findResourcesByOwnerAndResourcesType(String sqlQuery,
            List<ConstrainedProperty> resourceProperties, List<Long> resourcesIds) {

        List<Map<String, Object>> resultFromDB = new ArrayList<>();

        NamedParameterJdbcTemplate namedParamJdbc = new NamedParameterJdbcTemplate(jdbcTemplate);
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("ids", resourcesIds);
        resultFromDB = namedParamJdbc.queryForList(sqlQuery, parameters);

        List<GenericResource> resourcesList = modelingGenericResourcesFromDb(resultFromDB, resourceProperties);

        return resourcesList;
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
        return entityManager
                .createQuery(
                        "SELECT ro.resource.id FROM ResourceOwning ro "
                                + "WHERE ro.resourceType.typeName = :resourceTypeName AND ro.owner.id = :id",
                        Long.class)
                .setParameter("resourceTypeName", resourceTypeName).setParameter("id", ownerId).getResultList();

    }


    @Override
    public void addResource(Resource resource) {
        entityManager.persist(resource);
    }

    @Override
    public void addResourceOwning(ResourceOwning resourceOwning) {
        entityManager.persist(resourceOwning);
    }

    @Override
    public void addResourceImpl(String query) {
        entityManager.createNativeQuery(query).executeUpdate();
    }
}
