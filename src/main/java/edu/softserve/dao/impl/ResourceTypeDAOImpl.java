package edu.softserve.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import edu.softserve.dao.ResourceTypeDAO;
import edu.softserve.entity.GenericResource;
import edu.softserve.entity.PropertyValue;
import edu.softserve.entity.ResourceProperty;
import edu.softserve.entity.ResourceType;

@Repository
public class ResourceTypeDAOImpl implements ResourceTypeDAO {

    @PersistenceContext
    private EntityManager entityManager;

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<ResourceType> findAll() {

        return entityManager.createQuery("SELECT r FROM ResourceType r").getResultList();
    }

    @Override
    public ResourceType findWithPropertiesByID(int resourceTypeID) {

        return (ResourceType) entityManager
                .createQuery("SELECT r FROM ResourceType r LEFT JOIN FETCH r.properties WHERE r.id =:id")
                .setParameter("id", resourceTypeID).getSingleResult();
    }

    @Override
    public ResourceType findWithPropertiesByTableName(String tableName) {

        return (ResourceType) entityManager
                .createQuery("SELECT r FROM ResourceType r LEFT JOIN FETCH r.properties WHERE r.tableName =:tableName")
                .setParameter("tableName", tableName).getSingleResult();
    }

    @Override
    public List<GenericResource> findResourcesByResourceType(String sqlQuery, Map<String, String> valuesToSearch,
            List<ResourceProperty> resourceProperties) {

        List<GenericResource> genList = new ArrayList<GenericResource>();

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
                    argsTypes[i++] = property.getValueType().getSqlType();
                }
            }

        }
        i = 0;

        List<Map<String, Object>> genResRows = this.jdbcTemplate.queryForList(sqlQuery, args, argsTypes);

        for (Map<String, Object> mapRow : genResRows) {
            GenericResource genRes = new GenericResource();

            genRes.setId(Integer.parseInt(String.valueOf(mapRow.get("id"))));
            genRes.setId_Address(Integer.parseInt(String.valueOf(mapRow.get("id_Address"))));

            Set<PropertyValue> propertyValues = new TreeSet<>();

            for (ResourceProperty property : resourceProperties) {
                PropertyValue propertyValue = new PropertyValue(property,
                        String.valueOf(mapRow.get(property.getColumnName())));
                propertyValues.add(propertyValue);
            }

            genRes.setResourceValues(propertyValues);

            genList.add(genRes);
        }

        // Query query = entityManager.createNamedQuery(namedQuery);
        // Iterator<Map.Entry<String, String>> keyValues =
        // valuesToSearh.entrySet().iterator();
        // while (keyValues.hasNext()) {
        // Map.Entry<String, String> entry = keyValues.next();
        // query.setParameter(entry.getKey(), entry.getValue());
        // }

        return genList;
    }

}
