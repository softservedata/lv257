package com.softserve.edu.Resources.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.softserve.edu.Resources.entity.ResourceType;
import com.softserve.edu.Resources.service.ResourceTypeService;

public class LookUpControllerTest {

    @InjectMocks
    LookUpController lookUpController;
    
    @Mock
    ResourceTypeService resourceTypeService;
    
    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(lookUpController).build();
    }

//    @Test
    public void testLoadResourceTypes() throws Exception{
        List <ResourceType> resourceTypes = new ArrayList<>();
        ResourceType resType1 = new ResourceType();
        resType1.setId(1L);
        resType1.setTypeName("Cars");
        resType1.setTableName("cars");
        ResourceType resType2 = new ResourceType();
        resType1.setId(2L); 
        resType1.setTypeName("Building");
        resType1.setTableName("building");
        resourceTypes.add(resType1);
        resourceTypes.add(resType2);
       
        when(resourceTypeService.getInstances()).thenReturn(resourceTypes);
        mockMvc.perform(get("/lookUp/resourceTypes"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].typeName", is("Cars")))
                .andExpect(jsonPath("$[0].tableName", is("cars")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].typeName", is("Building")))
                .andExpect(jsonPath("$[1].tableName", is("building")));
        
        verify(resourceTypeService, times(1)).getInstances();
        verifyNoMoreInteractions(resourceTypeService);
    }

//    @Test
//    public void testLoadSpecResourceProperty() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    public void testGetValuesFromForm() {
//        fail("Not yet implemented");
//    }

}
