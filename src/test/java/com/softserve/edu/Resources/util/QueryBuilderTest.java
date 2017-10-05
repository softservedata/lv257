package com.softserve.edu.Resources.util;

import com.softserve.edu.Resources.dto.SearchOwnerDTO;
import com.softserve.edu.Resources.entity.ResourceProperty;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

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


    @Test
    @Parameters(method = "searchOwnerDTOTestData")
    public void testBuildSearchOwnerQuery(SearchOwnerDTO searchOwnerDTO, String expectedQuery)  throws Exception{
        QueryBuilder qb = new QueryBuilder();
        System.out.println(qb.findOwnerQuery(searchOwnerDTO));
        assertEquals(expectedQuery, qb.findOwnerQuery(searchOwnerDTO));
    }

    private Object[] searchOwnerDTOTestData() {

        SearchOwnerDTO searchOwnerDTO_1 = new SearchOwnerDTO();
        Map<String, String> fieldsANdValues = new TreeMap<>();

        fieldsANdValues.put("first_name", "Oleh");
        fieldsANdValues.put("last_name", "Tsebak");
        searchOwnerDTO_1.setOwnerType("Person");
        searchOwnerDTO_1.setFieldsAndValues(fieldsANdValues);

        SearchOwnerDTO searchOwnerDTO_2 = new SearchOwnerDTO();
        Map<String, String> fieldsANdValues_2 = new TreeMap<>();

        fieldsANdValues_2.put("passport_series", "KC");
        fieldsANdValues_2.put("passport_number", "163498");
        searchOwnerDTO_2.setOwnerType("Person");
        searchOwnerDTO_2.setFieldsAndValues(fieldsANdValues_2);

        SearchOwnerDTO searchOwnerDTO_3 = new SearchOwnerDTO();
        Map<String, String> fieldsANdValues_3 = new TreeMap<>();

        fieldsANdValues_3.put("organization_form", "TzOV");
        fieldsANdValues_3.put("full_name", "Sunshine");
        fieldsANdValues_3.put("short_name", "Sunny");
        searchOwnerDTO_3.setOwnerType("Company");
        searchOwnerDTO_3.setFieldsAndValues(fieldsANdValues_3);

        SearchOwnerDTO searchOwnerDTO_4 = new SearchOwnerDTO();
        Map<String, String> fieldsANdValues_4 = new TreeMap<>();

        fieldsANdValues_4.put("organization_form", "");
        fieldsANdValues_4.put("full_name", "");
        fieldsANdValues_4.put("short_name", "Sunny");
        searchOwnerDTO_4.setOwnerType("Company");
        searchOwnerDTO_4.setFieldsAndValues(fieldsANdValues_4);

        SearchOwnerDTO searchOwnerDTO_5 = new SearchOwnerDTO();
        Map<String, String> fieldsANdValues_5 = new TreeMap<>();

        fieldsANdValues_5.put("passport_series", "");
        fieldsANdValues_5.put("passport_number", "");
        searchOwnerDTO_5.setOwnerType("Person");
        searchOwnerDTO_5.setFieldsAndValues(fieldsANdValues_5);




        String result1 = "SELECT p FROM Person p WHERE first_name=\'Oleh\' AND last_name=\'Tsebak\' ";
        String result2 = "SELECT p FROM Person p WHERE passport_number=\'163498\' AND passport_series=\'KC\' ";
        String result3 = "SELECT c FROM Company c WHERE full_name=\'Sunshine\' AND organization_form=\'TzOV\' AND short_name=\'Sunny\' ";
        String result4 = "SELECT c FROM Company c WHERE full_name=\'\' AND organization_form=\'\' AND short_name=\'Sunny\' ";
        String result5 = "SELECT p FROM Person p WHERE passport_number=\'\' AND passport_series=\'\' ";



        return new Object[]{
                new Object[]{searchOwnerDTO_1, result1},
                new Object[]{searchOwnerDTO_2, result2},
                new Object[]{searchOwnerDTO_3, result3},
                new Object[]{searchOwnerDTO_4, result4},
                new Object[]{searchOwnerDTO_5, result5}
        };
    }


}
