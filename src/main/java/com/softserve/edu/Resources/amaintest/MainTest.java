package com.softserve.edu.Resources.amaintest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.softserve.edu.Resources.config.ApplicationContextConfig;
import com.softserve.edu.Resources.dao.ResourceDao;
import com.softserve.edu.Resources.dao.ResourceTypeDAO;
import com.softserve.edu.Resources.entity.GenericResource;
import com.softserve.edu.Resources.entity.ResourceProperty;
import com.softserve.edu.Resources.entity.ResourceType;
import com.softserve.edu.Resources.entity.ValueType;
import com.softserve.edu.Resources.service.ResourceTypeService;
import com.softserve.edu.Resources.service.QueryBuilderService;
import com.softserve.edu.Resources.service.impl.TestService;



public class MainTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(ApplicationContextConfig.class);
        ctx.refresh();

        TestService testService = ctx.getBean(TestService.class);
        testService.test();

        ResourceTypeService resTypeService = ctx.getBean(ResourceTypeService.class);
        resTypeService.testHello();

        // EntityManager em = ctx.getBean(EntityManager.class);

        ResourceTypeDAO resTypeDao = ctx.getBean(ResourceTypeDAO.class);

        ResourceType resTypeTest = resTypeDao.findWithPropertiesByID(1L);
        System.out.println("Database works: get id of resourceType" + resTypeTest.getId());
        
        
        
        ResourceDao resourceDao = ctx.getBean(ResourceDao.class);

        String sqlQuery = "SELECT c.id, c.id_Address, c.Model, c.Year FROM cars c WHERE c.Model = ? AND c.Year = ?";

        Map<String, String> valuesToSearch = new TreeMap<>();
        valuesToSearch.put("Model", "Mazda");
        valuesToSearch.put("Year", "2005");

        List<ResourceProperty> resourceProperties = new ArrayList<>();

        ResourceProperty resProp1 = new ResourceProperty();
        resProp1.setColumnName("Model");
        resProp1.setValueType(ValueType.STRING);

        ResourceProperty resProp2 = new ResourceProperty();
        resProp2.setColumnName("Year");
        resProp2.setValueType(ValueType.INTEGER);

        resourceProperties.add(resProp1);
        resourceProperties.add(resProp2);

        List<GenericResource> genResList = resourceDao.findResourcesByResourceType(sqlQuery, valuesToSearch,
                resourceProperties);
        
        for (GenericResource genericResource : genResList) {
            System.out.println(genericResource.toString());
        }
        
        QueryBuilderService utilService = ctx.getBean(QueryBuilderService.class);
       
        String tableName = "cars";
        
        
        String sql2Test = utilService.lookUpByResouceType(tableName, valuesToSearch);
        System.out.println(sql2Test);
        
        List<GenericResource> genericResources = resourceDao.findResourcesByResourceType(sql2Test, valuesToSearch, resourceProperties);
        
        for (GenericResource genericResource : genericResources) {
            System.out.println(genericResource);
        }

    }

}
