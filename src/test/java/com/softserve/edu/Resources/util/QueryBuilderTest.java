package com.softserve.edu.Resources.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.softserve.edu.Resources.entity.ResourceProperty;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class QueryBuilderTest {

    
    @Before
    public void setUp() throws Exception {
    }
     
    
    @Test
    @Parameters(method = "test1")
    public void testLookUpByResouceType(String tablename, Map<String, String> inputValues1,
            List<ResourceProperty> allResProperties, String result1)  throws Exception{
        QueryBuilder qb = new QueryBuilder();
        assertThat(qb.lookUpByResouceType(tablename, inputValues1, allResProperties), is(result1));
    }
    
    private Object[] test1() {
        
        Map<String, String> inputValues1 = new TreeMap<>();
        inputValues1.put("Model", "Mazda");
        inputValues1.put("Year", "2005");
        
        Map<String, String> inputValues2 = new TreeMap<>();
        inputValues2.put("Model", "Mazda");
        
        Map<String, String> inputValues3 = new TreeMap<>();
        
        List<ResourceProperty> allResProperties1 = new ArrayList<>();
        allResProperties1.add(new ResourceProperty("Model"));
        allResProperties1.add(new ResourceProperty("Year"));
        
        List<ResourceProperty> allResProperties2 = new ArrayList<>();
        allResProperties2.add(new ResourceProperty("Model"));
      
        String result1 = "SELECT gr.id, gr.id_address, gr.Model, gr.Year FROM Cars gr WHERE gr.Model = ? AND gr.Year = ?";
        String result2 = "SELECT gr.id, gr.id_address, gr.Model, gr.Year FROM Cars gr WHERE gr.Model = ?";
        String result3 = "SELECT gr.id, gr.id_address, gr.Model FROM Cars gr WHERE gr.Model = ?";
        String result4 = "SELECT gr.id, gr.id_address, gr.Model FROM Cars gr WHERE gr.Model = ? AND gr.Year = ?";
        String result5 = "SELECT gr.id, gr.id_address, gr.Model FROM Cars gr";
        
        return new Object[]{
                     new Object[]{"Cars", inputValues1, allResProperties1, result1},
                     new Object[]{"Cars", inputValues2, allResProperties1, result2},
                     new Object[]{"Cars", inputValues2, allResProperties2, result3},
                     new Object[]{"Cars", inputValues1, allResProperties2, result4},
                     new Object[]{"Cars", inputValues3, allResProperties2, result5}
                };
    }
    
    

}
