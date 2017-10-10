package com.softserve.edu.Resources.util;

import com.softserve.edu.Resources.dto.SearchDTO;
import com.softserve.edu.Resources.entity.ConstrainedProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.invoke.MethodHandles;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class QueryBuilder {

    static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getName());

    public String lookUpByResouceType(String tableName, Map<String, String> valuesToSearch,
                                      List<ConstrainedProperty> allResourceProperties) {

        StringBuilder createQuery = new StringBuilder();
        createQuery.append("SELECT gr.id, gr.id_address");

        createQuery.append(allResourceProperties.isEmpty() ? " " : ", ");

        Iterator<ConstrainedProperty> iter = allResourceProperties.iterator();
        while (iter.hasNext()) {
            createQuery.append("gr." + iter.next().getProperty().getColumnName() + "");
            if (iter.hasNext()) {
                createQuery.append(", ");
            } else {
                createQuery.append(" ");
            }

        }

        createQuery.append("FROM " + tableName + " gr");

        if (!valuesToSearch.values().isEmpty() || !valuesToSearch.isEmpty()) {
            createQuery.append(" WHERE ");

            Iterator<Map.Entry<String, String>> entries = valuesToSearch.entrySet().iterator();
            while (entries.hasNext()) {
                Map.Entry<String, String> entry = entries.next();

                createQuery.append("gr." + entry.getKey() + " = ?");
                if (entries.hasNext()) {
                    createQuery.append(" AND ");
                }

            }
        }
        return createQuery.toString();
    }

    /**
     *  Retrieves all data from dto objects and builds jpql query.
     *
     * @param searchDTO - object containing all necessary data to build query, like
     *                  entity name and it's fields and values.
     * @return valid query or empty string
     */
    public String buildQuery(SearchDTO searchDTO) {
        logger.info("Building query to search");

        StringBuilder stringBuilder = new StringBuilder();
        String ownerType = searchDTO.getEntityType();

        logger.info("Searching this type of entity: " + ownerType);

        String ownerTypeFirstChar = ownerType.substring(0, 1).toLowerCase();

        Map<String, String> fieldsAndValues = searchDTO.getFieldsAndValues();

        // e.g. - "SELECT a FROM Address a"
        stringBuilder.append("SELECT " + ownerTypeFirstChar + " FROM " + ownerType + " " + ownerTypeFirstChar);

        Set<Map.Entry<String, String>> entries = fieldsAndValues.entrySet();

        // get rid of empty entry value
        // from not empty values, build where-clause
        String whereClause = entries
                .stream()
                .filter(entry -> !entry.getValue().isEmpty())
                .map(entry -> entry.getKey() + "=\'" + entry.getValue() + "\'")
                .collect(Collectors.joining(" AND "));

        // all fields are empty, return empty string
        // if not, append "WHERE" key-word and whereClause string
        if (whereClause.isEmpty()){
            return "";
        } else {
            stringBuilder.append(" WHERE ");
            stringBuilder.append(whereClause);
        }

        String readyQuery = stringBuilder.toString();

        logger.info("Query to send to the DAO layer: " + readyQuery);

        return readyQuery;
    }

}
