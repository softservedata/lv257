package edu.softserve;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import edu.softserve.config.ApplicationContextConfig;
import edu.softserve.dao.ResourceTypeDAO;
import edu.softserve.entity.GenericResource;
import edu.softserve.entity.ResourceProperty;
import edu.softserve.entity.ResourceType;
import edu.softserve.entity.ValueType;
import edu.softserve.service.ResourceTypeService;
import edu.softserve.service.UtilQueryBuilderService;
import edu.softserve.serviceImpl.TestService;

public class MainTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(ApplicationContextConfig.class);
        ctx.refresh();

        TestService testService = ctx.getBean(TestService.class);
        testService.test();

        ResourceTypeService resService = ctx.getBean(ResourceTypeService.class);
        resService.testHello();

        // EntityManager em = ctx.getBean(EntityManager.class);

        ResourceTypeDAO resTypeDao = ctx.getBean(ResourceTypeDAO.class);

        ResourceType resTypeTest = resTypeDao.findWithPropertiesByID(1L);

        System.out.println("Database works: get id of resourceType" + resTypeTest.getId());

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

        List<GenericResource> genResList = resTypeDao.findResourcesByResourceType(sqlQuery, valuesToSearch,
                resourceProperties);
        
        for (GenericResource genericResource : genResList) {
            System.out.println(genericResource.toString());
        }
        
        UtilQueryBuilderService utilService = ctx.getBean(UtilQueryBuilderService.class);
       
        String tableName = "cars";
        
        
        String sql2Test = utilService.queryForLookUpByResouceType(tableName, valuesToSearch);
        
        List<GenericResource> genericResources = resTypeDao.findResourcesByResourceType(sql2Test, valuesToSearch, resourceProperties);
        
        for (GenericResource genericResource : genericResources) {
            System.out.println(genericResource);
        }

    }

}
