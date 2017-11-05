package com.softserve.edu.Resources.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.softserve.edu.Resources.dao.ResourceDao;
import com.softserve.edu.Resources.dto.GenericResourceDTO;
import com.softserve.edu.Resources.dto.GroupedResourceCount;
import com.softserve.edu.Resources.entity.Address;
import com.softserve.edu.Resources.entity.ConstrainedProperty;
import com.softserve.edu.Resources.entity.GenericResource;
import com.softserve.edu.Resources.entity.Owner;
import com.softserve.edu.Resources.entity.PropertyValue;
import com.softserve.edu.Resources.entity.Resource;
import com.softserve.edu.Resources.entity.ResourceOwning;
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
                        + "ro.resourceType.typeName, ro.resourceType.id, COUNT(ro) ) FROM ResourceOwning ro WHERE ro.owner.id = :id "
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
    public GenericResourceDTO findById(long resourceId, String sqlQuery, List<ConstrainedProperty> resourceProperties) {

        NamedParameterJdbcTemplate npjdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        MapSqlParameterSource paramaters = new MapSqlParameterSource();
        paramaters.addValue("id", resourceId);
        Map<String, Object> resultFromDB = npjdbcTemplate.queryForMap(sqlQuery, paramaters);

        GenericResourceDTO genResource = new GenericResourceDTO();

        genResource.setId(Integer.parseInt(String.valueOf(resultFromDB.get("id"))));

        Map<String, String> propertyValues = new TreeMap<>();

        for (ConstrainedProperty constrainedProperty : resourceProperties) {
            String key = constrainedProperty.getProperty().getTitle();
            String value = String.valueOf(resultFromDB.get(constrainedProperty.getProperty().getColumnName()));
            propertyValues.put(key, value);
        }

        genResource.setResourcePropertyValues(propertyValues);

        return genResource;
    }

    public List<Owner> getOwnersForGenericResourceByResourceTypeAndResource(long resourceTypeId, long resourceId) {

        @SuppressWarnings("unchecked")
        List<Owner> owners = (List<Owner>) entityManager
                .createQuery(
                        "SELECT ro.owner FROM ResourceOwning ro "
                                + "WHERE ro.resourceType.id = :resourceTypeId AND ro.resource.id = :resourceId",
                        Owner.class)
                .setParameter("resourceTypeId", resourceTypeId).setParameter("resourceId", resourceId).getResultList();

        return owners;
    }

    public Address findAddressForGenericResourceByResourceId(long resourceId) {
        return (Address) entityManager
                .createQuery("SELECT r.address FROM Resource r WHERE r.id = :resourceId", Address.class)
                .setParameter("resourceId", resourceId).getSingleResult();
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
