package com.softserve.edu.Resources.service.impl;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.softserve.edu.Resources.dao.ResourceDao;
import com.softserve.edu.Resources.dao.ResourceTypeDAO;
import com.softserve.edu.Resources.dto.GenericResourceDTO;
import com.softserve.edu.Resources.entity.GenericResource;
import com.softserve.edu.Resources.entity.ResourceProperty;
import com.softserve.edu.Resources.entity.ResourceType;
import com.softserve.edu.Resources.util.QueryBuilder;

import static org.mockito.Mockito.*;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


@RunWith(MockitoJUnitRunner.class)
public class ResourceServiceImplTest {
    
    @Mock
    ResourceTypeDAO resourceTypeDAOMock;
    
    @Mock
    ResourceDao resourceDaoMock;
    
    @Mock
    QueryBuilder queryBuildeMock;
    
    @Mock
    GenericResourceDTO genResDTOMock;
    
    @Mock
    ResourceType resourceTypeMock;
    
    @InjectMocks
    ResourceServiceImpl resServiceImpl;
    

    @SuppressWarnings("unchecked")
    @Test
    public void testFindResourcesByResourceType() {

        long testId = 1;
        when(genResDTOMock.getId()).thenReturn(testId);
        when(resourceTypeDAOMock.findWithPropertiesByID(anyLong())).thenReturn(resourceTypeMock);
    
        String tableName = "cars";

        when(resourceTypeMock.getTableName()).thenReturn(tableName);
        
        Set <ResourceProperty> resourceProperties = new HashSet<>();

        when(resourceTypeMock.getProperties()).thenReturn(resourceProperties);

        @SuppressWarnings("unchecked")
        Map<String, String> mapMock = mock(Map.class);

        when(genResDTOMock.getResourcePropertyValue()).thenReturn(mapMock);

        String queryForDao = "Select";

        when(queryBuildeMock.lookUpByResouceType(anyString(), anyMap(), anyList())).thenReturn(queryForDao);

        List<GenericResource> listGenResMock = mock(List.class);

        when(resourceDaoMock.findResourcesByResourceType(anyString(), anyMap(), anyList())).thenReturn(listGenResMock);

        List<GenericResource> retrievedListGenRes = resServiceImpl.findResourcesByResourceType(genResDTOMock);
        
        verify(resourceTypeDAOMock, times(1)).findWithPropertiesByID(testId);
        verify(queryBuildeMock, times(1)).lookUpByResouceType(eq(tableName), eq(mapMock), anyList());
        verify(resourceDaoMock, times(1)).findResourcesByResourceType(eq(queryForDao), eq(mapMock), anyList());
        
        assertNotNull(retrievedListGenRes);
        assertThat(retrievedListGenRes, is(listGenResMock));
        
        
    }

}
