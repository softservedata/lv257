package com.softserve.edu.Resources.util;

import com.softserve.edu.Resources.dto.SearchDTO;
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
    public void testSearchDTOQuery(SearchDTO searchDTO, String expectedQuery)  throws Exception{
        QueryBuilder qb = new QueryBuilder();
        assertEquals(expectedQuery, qb.buildQuery(searchDTO));
    }

    private Object[] searchOwnerDTOTestData() {

        SearchDTO searchDTO_1 = new SearchDTO();
        Map<String, String> fieldsANdValues = new TreeMap<>();

        fieldsANdValues.put("first_name", "Oleh");
        fieldsANdValues.put("last_name", "Tsebak");
        searchDTO_1.setEntityType("Person");
        searchDTO_1.setFieldsAndValues(fieldsANdValues);

        SearchDTO searchDTO_2 = new SearchDTO();
        Map<String, String> fieldsANdValues_2 = new TreeMap<>();

        fieldsANdValues_2.put("passport_series", "KC");
        fieldsANdValues_2.put("passport_number", "163498");
        searchDTO_2.setEntityType("Person");
        searchDTO_2.setFieldsAndValues(fieldsANdValues_2);

        SearchDTO searchDTO_3 = new SearchDTO();
        Map<String, String> fieldsANdValues_3 = new TreeMap<>();

        fieldsANdValues_3.put("organization_form", "TzOV");
        fieldsANdValues_3.put("full_name", "Sunshine");
        fieldsANdValues_3.put("short_name", "Sunny");
        searchDTO_3.setEntityType("Company");
        searchDTO_3.setFieldsAndValues(fieldsANdValues_3);

        SearchDTO searchDTO_4 = new SearchDTO();
        Map<String, String> fieldsANdValues_4 = new TreeMap<>();

        fieldsANdValues_4.put("organization_form", "");
        fieldsANdValues_4.put("full_name", "");
        fieldsANdValues_4.put("short_name", "Sunny");
        searchDTO_4.setEntityType("Company");
        searchDTO_4.setFieldsAndValues(fieldsANdValues_4);

        SearchDTO searchDTO_5 = new SearchDTO();
        Map<String, String> fieldsANdValues_5 = new TreeMap<>();

        fieldsANdValues_5.put("passport_series", "");
        fieldsANdValues_5.put("passport_number", "");
        searchDTO_5.setEntityType("Person");
        searchDTO_5.setFieldsAndValues(fieldsANdValues_5);

        SearchDTO searchDTO_6 = new SearchDTO();
        Map<String, String> fieldsANdValues_6 = new TreeMap<>();

        fieldsANdValues_6.put("region", "Lvivskiy");
        fieldsANdValues_6.put("locality", "Boryslav");
        searchDTO_6.setEntityType("Address");
        searchDTO_6.setFieldsAndValues(fieldsANdValues_6);

        SearchDTO searchDTO_7 = new SearchDTO();
        Map<String, String> fieldsANdValues_7 = new TreeMap<>();

        fieldsANdValues_7.put("region", "");
        fieldsANdValues_7.put("locality", "Boryslav");
        searchDTO_7.setEntityType("Address");
        searchDTO_7.setFieldsAndValues(fieldsANdValues_7);

        SearchDTO searchDTO_8 = new SearchDTO();
        Map<String, String> fieldsANdValues_8 = new TreeMap<>();

        fieldsANdValues_8.put("region", "");
        fieldsANdValues_8.put("locality", "");
        searchDTO_8.setEntityType("Address");
        searchDTO_8.setFieldsAndValues(fieldsANdValues_8);


        String result1 = "SELECT p FROM Person p WHERE first_name=\'Oleh\' AND last_name=\'Tsebak\'";
        String result2 = "SELECT p FROM Person p WHERE passport_number=\'163498\' AND passport_series=\'KC\'";
        String result3 = "SELECT c FROM Company c WHERE full_name=\'Sunshine\' AND organization_form=\'TzOV\' AND short_name=\'Sunny\'";
        String result4 = "SELECT c FROM Company c WHERE short_name=\'Sunny\'";
        String result5 = "";
        String result6 = "SELECT a FROM Address a WHERE locality=\'Boryslav\' AND region=\'Lvivskiy\'";
        String result7 = "SELECT a FROM Address a WHERE locality=\'Boryslav\'";
        String result8 = "";

        return new Object[]{
                new Object[]{searchDTO_1, result1},
                new Object[]{searchDTO_2, result2},
                new Object[]{searchDTO_3, result3},
                new Object[]{searchDTO_4, result4},
                new Object[]{searchDTO_5, result5},
                new Object[]{searchDTO_6, result6},
                new Object[]{searchDTO_7, result7},
                new Object[]{searchDTO_8, result8},
        };
    }


}
