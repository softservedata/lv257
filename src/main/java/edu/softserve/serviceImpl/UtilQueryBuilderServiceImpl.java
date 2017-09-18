package edu.softserve.serviceImpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.softserve.dao.ResourceTypeDAO;
import edu.softserve.entity.ResourceProperty;
import edu.softserve.entity.ResourceType;
import edu.softserve.service.UtilQueryBuilderService;

@Service
public class UtilQueryBuilderServiceImpl implements UtilQueryBuilderService {

    
    @Autowired
    ResourceTypeDAO resourceTypeDao;
    
    @Transactional
    @Override
    public String queryForLookUpByResouceType(String tableName, Map<String, String> valuesToSearch) {
        
        ResourceType resourceType = resourceTypeDao.findWithPropertiesByTableName(tableName);
        
        System.out.println(resourceType.toString());
        
        List <ResourceProperty> allResourceProperties = new ArrayList<ResourceProperty>();
        allResourceProperties.addAll(resourceType.getProperties());

        System.out.println(allResourceProperties);
        
        
        StringBuilder createQuery = new StringBuilder();
        createQuery.append("SELECT gr.id, gr.id_Address");
        
        createQuery.append(allResourceProperties.isEmpty() ? " " : ", ");
        
        Iterator<ResourceProperty> iter = allResourceProperties.iterator();
        while (iter.hasNext()){
            createQuery.append("gr."+ iter.next().getColumnName() + "");
            if (iter.hasNext()){
                createQuery.append(", ");
            } else {
                createQuery.append(" ");
            }
            
        }
       
        createQuery.append("FROM " + tableName + " gr");
        createQuery.append(" WHERE ");

        Iterator<Map.Entry<String, String>> entries = valuesToSearch.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, String> entry = entries.next();
            createQuery.append("gr." + entry.getKey() + " = ?");
            if (entries.hasNext()) {
                createQuery.append(" AND ");
            }
        }

        

        return createQuery.toString();
    }

}
