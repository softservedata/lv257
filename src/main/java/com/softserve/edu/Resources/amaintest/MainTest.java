package com.softserve.edu.Resources.amaintest;

import com.softserve.edu.Resources.config.ApplicationConfig;
import com.softserve.edu.Resources.entity.GenericResource;
import com.softserve.edu.Resources.service.ResourceService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.softserve.edu.Resources.config.ApplicationConfig;
import com.softserve.edu.Resources.dao.ResourceDao;
import com.softserve.edu.Resources.dao.ResourceTypeDAO;
import com.softserve.edu.Resources.dto.GenericResourceDTO;
import com.softserve.edu.Resources.entity.GenericResource;
import com.softserve.edu.Resources.entity.ResourceProperty;
import com.softserve.edu.Resources.entity.ResourceType;
import com.softserve.edu.Resources.entity.ValueType;
import com.softserve.edu.Resources.service.ResourceService;
import com.softserve.edu.Resources.service.ResourceTypeService;
import com.softserve.edu.Resources.service.impl.TestService;
import com.softserve.edu.Resources.util.QueryBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MainTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(ApplicationConfig.class);
        ctx.refresh();

        TestService testService = ctx.getBean(TestService.class);
        System.out.println("Is TestService Bean is created in container? " + testService.test());

        ResourceTypeService resTypeService = ctx.getBean(ResourceTypeService.class);
        System.out.println("Try another bean ResourceTypeService method: ");
        resTypeService.testHello();
        System.out.println();

        System.out.println("Checking if there is connection to database and queries work with EntityManager:");
        ResourceTypeDAO resTypeDao = ctx.getBean(ResourceTypeDAO.class);
        System.out.println("Hibernate select query:");
        ResourceType resTypeTest = resTypeDao.findWithPropertiesByID(1L);
        System.out.println();
        System.out.println("Database works! get name of resourceType- result: >" + resTypeTest.getName());
        System.out.println();

        System.out.println("Checking if Spring+JDBC(jdbcTemplate)/ResourceDaoImpl class works: " + "\n"
                + " -method 'findResorucesByResourceType(String sqlQuery, Map <String,String> valuesToSearch, List<ResourceProperty>");
        ResourceDao resourceDao = ctx.getBean(ResourceDao.class);
        String sqlQuery = "SELECT c.id, c.id_Address, c.Model, c.Year FROM cars c WHERE c.Model = ? AND c.Year = ?";
        System.out.println(" a) first parapeter for method, sqlQuery: " + "\n" + sqlQuery);

        Map<String, String> valuesToSearch = new TreeMap<>();
        valuesToSearch.put("Model", "Mazda");
        valuesToSearch.put("Year", "2005");

        System.out.println(" b) second parameter is a map with key/value: ");
        for (Map.Entry<String, String> entry : valuesToSearch.entrySet()) {
            System.out.println("Key: " + entry.getKey() + "/ Value: " + entry.getValue());
        }

        List<ResourceProperty> resourceProperties = new ArrayList<>();

        ResourceProperty resProp1 = new ResourceProperty();
        resProp1.setColumnName("Model");
        resProp1.setValueType(ValueType.STRING);

        ResourceProperty resProp2 = new ResourceProperty();
        resProp2.setColumnName("Year");
        resProp2.setValueType(ValueType.INTEGER);

        resourceProperties.add(resProp1);
        resourceProperties.add(resProp2);

        System.out.println(
                " c) third parameter is a List of all resource Properties of special resource, which we querrying");
        for (ResourceProperty resourceProperty : resourceProperties) {
            System.out.println("ColumnName: " + resourceProperty.getColumnName() + ", ValueType: "
                    + resourceProperty.getValueTypeName());
        }
        List<GenericResource> genResList = resourceDao.findResourcesByResourceType(sqlQuery, valuesToSearch,
                resourceProperties);

        System.out.println();
        System.out.println("Result from database. List of GenericResources by query and input Values ");
        for (GenericResource genericResource : genResList) {
            System.out.println(genericResource.toString());
        }
        System.out.println();
        System.out.println();

        System.out.println(
                "Checking if QueryBuilder works: method 'lookUpByResourceType( String tableName, Map<String,String> valuesToSearch)':"
                        + "\n");
        QueryBuilder utilService = ctx.getBean(QueryBuilder.class);

        String tableName = "cars";
        System.out.println(" a)first parameter is tableName: " + tableName);
        System.out.println(" b)second parameter is a Map with the same key/value as previous Map");
        String sql2Test = utilService.lookUpByResouceType(tableName, valuesToSearch, resourceProperties);
        System.out.println();
        System.out.println("Result of 'lookUpByResourceType' method is: " + "\n" + sql2Test);

        List<GenericResource> genericResources = resourceDao.findResourcesByResourceType(sql2Test, valuesToSearch,
                resourceProperties);
        System.out.println("Result from database. List of GenericResources by query and input values: ");
        for (GenericResource genericResource : genericResources) {
            System.out.println(genericResource);
        }

        System.out.println();
        System.out.println();

        System.out.println(
                "Checking if ResourceServiceImpl works: method 'findResourcesByResourceType(String query, String tableName, Map<String, String> valuesToSearch)':"
                        + "\n" + " with ResourceDaoImpl method 'findResourcesByResType' ");
        System.out.println("Also it checks the method of ResourceTypeDaoImpl 'findWithPropertiesByTableName'");
        
        ResourceService resService = ctx.getBean(ResourceService.class);
        GenericResourceDTO  genResDto = new GenericResourceDTO(1, valuesToSearch);

        List<GenericResource> listGenRes2 = resService.findResourcesByResourceType(genResDto);
        
        for (GenericResource genericResource : listGenRes2) {
            System.out.println(genericResource);
        }

    }

}
