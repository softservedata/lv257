package com.softserve.edu.Resources.service.impl;

import com.softserve.edu.Resources.dao.ResourceDao;
import com.softserve.edu.Resources.dao.ResourceTypeDAO;
import com.softserve.edu.Resources.dto.GenericResourceDTO;
import com.softserve.edu.Resources.entity.ConstrainedProperty;
import com.softserve.edu.Resources.entity.GenericResource;
import com.softserve.edu.Resources.entity.ResourceType;
import com.softserve.edu.Resources.util.QueryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;


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
        
        Set <ConstrainedProperty> resourceProperties = new HashSet<>();

        when(resourceTypeMock.getProperties()).thenReturn(resourceProperties);

        @SuppressWarnings("unchecked")
        Map<String, String> mapMock = mock(Map.class);

        when(genResDTOMock.getResourcePropertyValues()).thenReturn(mapMock);

        String queryForDao = "Select";

        when(queryBuildeMock.queryForJdbcTemplate(anyString(), anyMap(), anyList())).thenReturn(queryForDao);

        List<GenericResource> listGenResMock = mock(List.class);

        when(resourceDaoMock.findResourcesByResourceType(anyString(), anyMap(), anyList())).thenReturn(listGenResMock);

        List<GenericResource> retrievedListGenRes = resServiceImpl.findResourcesByResourceType(genResDTOMock);
        
        verify(resourceTypeDAOMock, times(1)).findWithPropertiesByID(testId);
        verify(queryBuildeMock, times(1)).queryForJdbcTemplate(eq(tableName), eq(mapMock), anyList());
        verify(resourceDaoMock, times(1)).findResourcesByResourceType(eq(queryForDao), eq(mapMock), anyList());
        
        assertNotNull(retrievedListGenRes);
        assertThat(retrievedListGenRes, is(listGenResMock));
        
        
    }

}
