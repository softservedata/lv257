package com.softserve.edu.Resources.util;

import com.softserve.edu.Resources.dto.SearchOwnerDTO;
import com.softserve.edu.Resources.entity.ConstrainedProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.invoke.MethodHandles;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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

    public String findOwnerQuery(SearchOwnerDTO searchOwnerDTO) {
        logger.info("Building query to search owners");

        StringBuilder stringBuilder = new StringBuilder();
        String ownerType = searchOwnerDTO.getOwnerType();

        logger.info("Searching this type og owner: " + ownerType);

        String ownerTypeFirstChar = String.valueOf(ownerType.charAt(0)).toLowerCase();

        Map<String, String> fieldsAndValues = searchOwnerDTO.getFieldsAndValues();

        stringBuilder.append("SELECT " + ownerTypeFirstChar + " FROM " + ownerType + " " + ownerTypeFirstChar);
        stringBuilder.append(" WHERE ");

        Iterator<Map.Entry<String, String>> entries = fieldsAndValues.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, String> entry = entries.next();
            stringBuilder.append(entry.getKey() + "=\'" + entry.getValue() + "\' ");
            if (entries.hasNext()) {
                stringBuilder.append("AND ");
            }
        }

        String readyQuery = stringBuilder.toString();

        logger.info("Query to send to the DAO layer: " + readyQuery);

        return readyQuery;
    }

}
