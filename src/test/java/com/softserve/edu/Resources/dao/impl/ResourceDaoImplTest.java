package com.softserve.edu.Resources.dao.impl;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.mockito.Matchers;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;

import com.softserve.edu.Resources.entity.GenericResource;
import com.softserve.edu.Resources.entity.PropertyValue;
import com.softserve.edu.Resources.entity.ResourceProperty;
import com.softserve.edu.Resources.entity.ValueType;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class ResourceDaoImplTest {

    @Mock
    JdbcTemplate jdbcTemplate;

    @InjectMocks
    ResourceDaoImpl resourceDaoImpl;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void setUpMock() throws Exception {

    }

    @Test
    @Parameters(method = "test1")
    public void testFindResourcesByResourceType(String query, Map<String, String> inputValues,
            List<ResourceProperty> resourceProperties, Object[] args, int[] argsTypes,
            List<Map<String, Object>> resultSet, List<GenericResource> listGenRes) {

        when(jdbcTemplate.queryForList(any(), any(), (int[]) any())).thenReturn(resultSet);
        when(jdbcTemplate.queryForList(anyString())).thenReturn(resultSet);
        
        List<GenericResource> retrievedListGenRes = resourceDaoImpl.findResourcesByResourceType(query, inputValues,
                resourceProperties);
        if (inputValues.isEmpty()){
            verify(jdbcTemplate, times(1)).queryForList(query);
        } else {
            verify(jdbcTemplate, times(1)).queryForList(query, args, argsTypes);
        }
       
        
        assertEquals(listGenRes, retrievedListGenRes);
        assertThat(retrievedListGenRes, is(listGenRes));
        

    }

    private Object[] test1() {

        String result1 = "SELECT gr.id, gr.id_address, gr.Model, gr.Year FROM Cars gr WHERE gr.Model = ? AND gr.Year = ?";
        String result2 = "SELECT gr.id, gr.id_address, gr.Model, gr.Year FROM Cars gr WHERE gr.Model = ?";
        String result3 = "SELECT gr.id, gr.id_address, gr.Model FROM Cars gr WHERE gr.Model = ?";
        String result4 = "SELECT gr.id, gr.id_address, gr.Model FROM Cars gr WHERE gr.Model = ? AND gr.Year = ?";
        String result5 = "SELECT gr.id, gr.id_address, gr.Model FROM Cars gr";
        String result6 = "SELECT gr.id, gr.id_address, gr.Model, gr.Year FROM Cars";

        Map<String, String> inputValues1 = new TreeMap<>();
        inputValues1.put("Model", "Mazda");
        inputValues1.put("Year", "2005");
        Map<String, String> inputValues6 = new TreeMap<>();
        

        List<ResourceProperty> resourceProperties1 = new ArrayList<>();
        resourceProperties1.add(new ResourceProperty("Model").setValueType(ValueType.STRING));
        resourceProperties1.add(new ResourceProperty("Year").setValueType(ValueType.INTEGER));

        Object[] args1 = inputValues1.values().toArray();

        int[] argsTypes1 = new int[inputValues1.size()];
        argsTypes1[0] = resourceProperties1.get(0).getValueType().getSqlType();
        argsTypes1[1] = resourceProperties1.get(1).getValueType().getSqlType();

        List<Map<String, Object>> resultSet1 = new ArrayList<>();
        Map<String, Object> map1 = new TreeMap<>();
        map1.put("id", 1);
        map1.put("id_address", 1);
        map1.put("Model", "Mazda");
        map1.put("Year", 2005);
        Map<String, Object> map2 = new TreeMap<>();
        map2.put("id", 2);
        map2.put("id_address", 2);
        map2.put("Model", "Mazda");
        map2.put("Year", 2005);
        resultSet1.add(map1);
        resultSet1.add(map2);
        
        List<Map<String, Object>> resultSet6 = new ArrayList<>();
        Map<String, Object> map3 = new TreeMap<>();
        map3.put("id", 1);
        map3.put("id_address", 1);
        map3.put("Model", "Mazda");
        map3.put("Year", 2005);
        Map<String, Object> map4 = new TreeMap<>();
        map4.put("id", 2);
        map4.put("id_address", 2);
        map4.put("Model", "Volvo");
        map4.put("Year", 2002);
        resultSet6.add(map3);
        resultSet6.add(map4);

        List<GenericResource> listGenRes1 = new ArrayList<>();
        GenericResource genRes1 = new GenericResource();
        genRes1.setId(1);
        genRes1.setId_Address(1);
        Set<PropertyValue> propertyValues1 = new TreeSet<>();
        propertyValues1.add(new PropertyValue(resourceProperties1.get(0), "Mazda"));
        propertyValues1.add(new PropertyValue(resourceProperties1.get(1), "2005"));
        genRes1.setPropertyValues(propertyValues1);

        GenericResource genRes2 = new GenericResource();
        genRes2.setId(2);
        genRes2.setId_Address(2);
        Set<PropertyValue> propertyValues2 = new TreeSet<>();
        propertyValues2.add(new PropertyValue(resourceProperties1.get(0), "Mazda"));
        propertyValues2.add(new PropertyValue(resourceProperties1.get(1), "2005"));
        genRes2.setPropertyValues(propertyValues2);

        listGenRes1.add(genRes1);
        listGenRes1.add(genRes2);

        List<GenericResource> listGenRes6 = new ArrayList<>();
        GenericResource genRes3 = new GenericResource();
        genRes3.setId(1);
        genRes3.setId_Address(1);
        Set<PropertyValue> propertyValues3 = new TreeSet<>();
        propertyValues3.add(new PropertyValue(resourceProperties1.get(0), "Mazda"));
        propertyValues3.add(new PropertyValue(resourceProperties1.get(1), "2005"));
        genRes3.setPropertyValues(propertyValues3);

        GenericResource genRes4 = new GenericResource();
        genRes4.setId(2);
        genRes4.setId_Address(2);
        Set<PropertyValue> propertyValues4 = new TreeSet<>();
        propertyValues4.add(new PropertyValue(resourceProperties1.get(0), "Volvo"));
        propertyValues4.add(new PropertyValue(resourceProperties1.get(1), "2002"));
        genRes4.setPropertyValues(propertyValues4);

        listGenRes6.add(genRes3);
        listGenRes6.add(genRes4);

        return new Object[] { new Object[] { result1, inputValues1, resourceProperties1, args1, argsTypes1,
                resultSet1, listGenRes1},
                 new Object[] { result6, inputValues6, resourceProperties1, args1, argsTypes1,
                        resultSet6, listGenRes6}
                // new Object[] { "Cars", inputValues2, allResProperties1,
                // result2 },
                // new Object[] { "Cars", inputValues2, allResProperties2,
                // result3 },
                // new Object[] { "Cars", inputValues1, allResProperties2,
                // result4 },
                // new Object[] { "Cars", inputValues3, allResProperties2,
                // result5 }
        };
    }

}
