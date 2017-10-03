package com.softserve.edu.Resources.util;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.softserve.edu.Resources.entity.ResourceProperty;

@Component
public class QueryBuilder {

    public String lookUpByResouceType(String tableName, Map<String, String> valuesToSearch,
            List<ResourceProperty> allResourceProperties) {

        StringBuilder createQuery = new StringBuilder();
        createQuery.append("SELECT gr.id, gr.id_address");

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

}
