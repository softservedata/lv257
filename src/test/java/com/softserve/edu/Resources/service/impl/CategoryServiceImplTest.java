package com.softserve.edu.Resources.service.impl;

import com.softserve.edu.Resources.dao.ResourceCategoryDAO;
import com.softserve.edu.Resources.dto.ResourceCategoryDTO;
import com.softserve.edu.Resources.dto.ResourceTypeDTO;
import com.softserve.edu.Resources.entity.ResourceCategory;
import com.softserve.edu.Resources.entity.ResourceType;
import com.softserve.edu.Resources.exception.CycleDependencyException;
import com.softserve.edu.Resources.exception.InvalidResourceCategoryException;
import com.softserve.edu.Resources.exception.RemovingCategoriesWithTypesException;
import com.sun.org.apache.regexp.internal.RE;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceImplTest {

    private static long categoryId;
    private static String categoryName;
    private static ResourceCategory persistedCategory;
    private static ResourceCategory rootCategory1;
    private static ResourceCategory rootCategory2;
    private static ResourceCategory branchCategory1;
    private static ResourceCategory branchCategory2;
    private static ResourceCategory leafCategory_1_1;
    private static ResourceCategory leafCategory_1_2;
    private static ResourceCategory leafCategory_1_3;
    private static ResourceCategory leafCategory_2_1;
    private static ResourceCategory leafCategory_2_2;
    private static List<ResourceCategory> allCategories;
    private static List<ResourceCategory> rootCategories;

    private static ResourceType type1;
    private static ResourceType type2;
    private static ResourceType type3;

    private static ResourceCategoryDTO rootCategory1DTO;
    private static ResourceCategoryDTO rootCategory2DTO;
    private static ResourceCategoryDTO branchCategory1DTO;
    private static ResourceCategoryDTO branchCategory2DTO;
    private static ResourceCategoryDTO leafCategory_1_1DTO;
    private static ResourceCategoryDTO leafCategory_1_2DTO;
    private static ResourceCategoryDTO leafCategory_1_3DTO;
    private static ResourceCategoryDTO leafCategory_2_1DTO;
    private static ResourceCategoryDTO leafCategory_2_2DTO;

    private static ResourceTypeDTO type1DTO;
    private static ResourceTypeDTO type2DTO;

    @Mock
    private ResourceCategoryDAO categoryDAO;

    @Spy
    @InjectMocks
    private ResourceCategoryServiceImpl categoryServiceSpy;

    @BeforeClass
    public static void setUp() {
        categoryId = 10L;
        categoryName = "branchCategory1";

        rootCategory1 = new ResourceCategory("rootCategory1", null).setId(1L);
        branchCategory1 = new ResourceCategory("branchCategory1", rootCategory1).setId(2L);
        branchCategory2 = new ResourceCategory("branchCategory2", rootCategory1).setId(3L);
        leafCategory_1_1 = new ResourceCategory("leafCategory_1_1", branchCategory1).setId(4L);
        leafCategory_1_2 = new ResourceCategory("leafCategory_1_2", branchCategory1).setId(5L);
        leafCategory_1_3 = new ResourceCategory("leafCategory_1_3", branchCategory1).setId(6L);
        leafCategory_2_1 = new ResourceCategory("leafCategory_2_1", branchCategory2).setId(7L);
        leafCategory_2_2 = new ResourceCategory("leafCategory_2_2", branchCategory2).setId(8L);
        rootCategory2 = new ResourceCategory("rootCategory2", null).setId(9L);

        branchCategory1.setChildrenCategories(new HashSet<>(Arrays.asList(leafCategory_1_1, leafCategory_1_2, leafCategory_1_3)));
        branchCategory2.setChildrenCategories(new HashSet<>(Arrays.asList(leafCategory_2_1, leafCategory_2_2)));
        rootCategory1.setChildrenCategories(new HashSet<>(Arrays.asList(branchCategory1, branchCategory2)));

        type1 = new ResourceType("type1").setId(101L).setTableName("type1")
                .setCategory(leafCategory_1_3).setInstantiated(true);
        type2 = new ResourceType("type2").setId(102L).setTableName("type2")
                .setCategory(leafCategory_1_3).setInstantiated(false);
        type3 = new ResourceType("type3").setId(103L).setTableName("type3")
                .setCategory(leafCategory_1_1).setInstantiated(true);

        leafCategory_1_3.setResourceTypes(new HashSet<>(Arrays.asList(type1, type2)));
        leafCategory_1_1.setResourceTypes(new HashSet<>(Arrays.asList(type3)));

        allCategories = Arrays.asList(rootCategory1, rootCategory2, branchCategory1, branchCategory2, leafCategory_1_1,
                leafCategory_1_2, leafCategory_1_3, leafCategory_2_1, leafCategory_2_2);
        rootCategories = Arrays.asList(rootCategory1, rootCategory2);

        persistedCategory = new ResourceCategory("persistedCategory", branchCategory1).setId(categoryId);

        rootCategory1DTO = new ResourceCategoryDTO("rootCategory1", null).setId(1L);
        branchCategory1DTO = new ResourceCategoryDTO("branchCategory1", rootCategory1DTO).setId(2L);
        branchCategory2DTO = new ResourceCategoryDTO("branchCategory2", rootCategory1DTO).setId(3L);
        leafCategory_1_1DTO = new ResourceCategoryDTO("leafCategory_1_1", branchCategory1DTO).setId(4L);
        leafCategory_1_2DTO = new ResourceCategoryDTO("leafCategory_1_2", branchCategory1DTO).setId(5L);
        leafCategory_1_3DTO = new ResourceCategoryDTO("leafCategory_1_3", branchCategory1DTO).setId(6L);
        leafCategory_2_1DTO = new ResourceCategoryDTO("leafCategory_2_1", branchCategory2DTO).setId(7L);
        leafCategory_2_2DTO = new ResourceCategoryDTO("leafCategory_2_2", branchCategory2DTO).setId(8L);
        rootCategory2DTO = new ResourceCategoryDTO("rootCategory2", null).setId(9L);

        branchCategory1DTO.setChildrenCategories(new HashSet<>(Arrays.asList(leafCategory_1_1DTO,
                leafCategory_1_2DTO, leafCategory_1_3DTO)));
        branchCategory2DTO.setChildrenCategories(new HashSet<>(Arrays.asList(leafCategory_2_1DTO,
                leafCategory_2_2DTO)));
        rootCategory1DTO.setChildrenCategories(new HashSet<>(Arrays.asList(branchCategory1DTO, branchCategory2DTO)));

        type1DTO = new ResourceTypeDTO(type1);
        type2DTO = new ResourceTypeDTO(type2);

        leafCategory_1_3DTO.setWithResourceTypes(true);
        leafCategory_1_3DTO.setInstantiatedResourceTypes(new HashSet<>(Arrays.asList(type1DTO)));
    }

    @Test
    public void findCategoryByIdTest() {
        when(categoryDAO.findById(categoryId)).thenReturn(Optional.ofNullable(rootCategory1));
        assertEquals(categoryServiceSpy.findCategoryById(categoryId), Optional.ofNullable(rootCategory1));
        verify(categoryDAO, times(1)).findById(categoryId);
    }

    @Test
    public void findCategoryByNameTest() {
        when(categoryDAO.findByName(categoryName)).thenReturn(Optional.ofNullable(branchCategory1));
        assertEquals(categoryServiceSpy.findCategoryByName(categoryName), Optional.ofNullable(branchCategory1));
        verify(categoryDAO, times(1)).findByName(categoryName);
    }

    @Test
    public void findAllResourceCategoriesTest() {
        when(categoryDAO.findAll()).thenReturn(allCategories);
        assertEquals(categoryServiceSpy.findAllResourceCategories(), allCategories);
        verify(categoryDAO, times(1)).findAll();
    }

    @Test
    public void saveResourceCategoryTest() {
        when(categoryDAO.makePersistent(any(ResourceCategory.class))).thenReturn(persistedCategory);
        assertEquals(categoryServiceSpy.saveResourceCategory(leafCategory_1_1), persistedCategory);
        verify(categoryDAO, times(1)).makePersistent(leafCategory_1_1);
    }

    @Test
    public void deleteResourceCategoryTest() {
        categoryServiceSpy.deleteResourceCategory(persistedCategory);
        verify(categoryDAO, times(1)).makeTransient(persistedCategory);
    }

    @Test
    public void findRootCategoriesTest() {
        when(categoryDAO.findRootCategories()).thenReturn(rootCategories);
        assertEquals(categoryServiceSpy.findRootCategories(), rootCategories);
        verify(categoryDAO, times(1)).findRootCategories();
    }

    @Test
    public void getDescendantsTest() {
        List<ResourceCategory> expected = new ArrayList<>(Arrays.asList(branchCategory2, leafCategory_2_2,
                leafCategory_2_1, branchCategory1, leafCategory_1_3, leafCategory_1_2, leafCategory_1_1));
        List<ResourceCategory> actual = categoryServiceSpy.getDescendants(rootCategory1);
        assertTrue(actual.containsAll(expected));
        assertTrue(expected.containsAll(actual));
        assertTrue(actual.size() == expected.size());
    }

    @Test(expected = CycleDependencyException.class)
    public void getDescendantsWithCyclesTest() {
        try {
            leafCategory_1_1.setChildrenCategories(new HashSet<>(Arrays.asList(rootCategory1)));
            branchCategory1.setChildrenCategories(new HashSet<>(Arrays.asList(leafCategory_1_1, leafCategory_1_2, leafCategory_1_3)));
            categoryServiceSpy.getDescendants(rootCategory1);
        } finally {
            leafCategory_1_1.getChildrenCategories().clear();
        }
    }

    @Test
    public void getAncestorsTest() {
        List<ResourceCategory> expected = new ArrayList<>(Arrays.asList(rootCategory1, branchCategory1));
        List<ResourceCategory> actual = categoryServiceSpy.getAncestors(leafCategory_1_1);
        assertEquals(expected, actual);
    }

    @Test(expected = CycleDependencyException.class)
    public void getAncestorsWithCyclesTest() {
        try {
            rootCategory1.setParentCategory(leafCategory_1_1);
            categoryServiceSpy.getAncestors(rootCategory1);
        } finally {
            rootCategory1.setParentCategory(null);
        }
    }

    @Test
    public void deployCategoryTest() {
        List<ResourceCategory> expected = new ArrayList<>(allCategories);
        expected.remove(rootCategory2);
        List<ResourceCategory> actual = categoryServiceSpy.deployCategory(rootCategory1);
        assertTrue(actual.containsAll(expected));
        assertTrue(expected.containsAll(actual));
        assertTrue(actual.size() == expected.size());
        verify(categoryServiceSpy, times(1)).getDescendants(any(ResourceCategory.class));
    }

    @Test
    public void deployAllCategoriesFromRootsTest() {
        List<ResourceCategory> expected = allCategories;
        List<ResourceCategory> actual = categoryServiceSpy.deployAllCategoriesFromRoots(rootCategories);
        assertTrue(actual.containsAll(expected));
        assertTrue(expected.containsAll(actual));
        assertTrue(actual.size() == expected.size());
        verify(categoryServiceSpy, times(rootCategories.size())).getDescendants(any(ResourceCategory.class));
    }

    @Test(expected = CycleDependencyException.class)
    public void deployAllCategoriesFromRootsWithCyclesTest() {
        try {
            rootCategory2.setChildrenCategories(branchCategory1.getChildrenCategories());
            categoryServiceSpy.deployAllCategoriesFromRoots(rootCategories);
        } finally {
            rootCategory2.getChildrenCategories().clear();
        }
    }

    @Test
    public void isValidCategoryNameTest() {
        assertTrue(categoryServiceSpy.isValidCategoryName(allCategories, 3, 20));
        rootCategory2.setCategoryName(rootCategory1.getCategoryName());
        assertFalse(categoryServiceSpy.isValidCategoryName(allCategories, 3, 20));
        rootCategory2.setCategoryName("ca");
        assertFalse(categoryServiceSpy.isValidCategoryName(allCategories, 3, 20));
        rootCategory2.setCategoryName("cat");
        assertTrue(categoryServiceSpy.isValidCategoryName(allCategories, 3, 20));
        rootCategory2.setCategoryName("12345678901234567890");
        assertTrue(categoryServiceSpy.isValidCategoryName(allCategories, 3, 20));
        rootCategory2.setCategoryName("123456789012345678901");
        assertFalse(categoryServiceSpy.isValidCategoryName(allCategories, 3, 20));
        rootCategory2.setCategoryName(null);
        assertFalse(categoryServiceSpy.isValidCategoryName(allCategories, 3, 20));
        rootCategory2.setCategoryName("rootCategory2");
    }

    @Test
    public void deleteMissingCategoriesInDBTest() {
        List<ResourceCategory> webCategories = new ArrayList<>(allCategories);
        List<ResourceCategory> dbCategories = new ArrayList<>(allCategories);
        List<ResourceCategory> removedDbCategories = new ArrayList<>();
        when(categoryServiceSpy.findAllResourceCategories()).thenReturn(dbCategories);
        doAnswer(invocation -> removedDbCategories.add(invocation.getArgumentAt(0, ResourceCategory.class)))
                .when(categoryServiceSpy).deleteResourceCategory(any(ResourceCategory.class));
        List<ResourceCategory> removedWebCategories = Arrays.asList(branchCategory2, leafCategory_2_1,
                leafCategory_2_2, rootCategory2);
        webCategories.removeAll(removedWebCategories);
        categoryServiceSpy.deleteMissingCategoriesInDB(webCategories);
        verify(categoryServiceSpy, times(removedWebCategories.size())).deleteResourceCategory(any(ResourceCategory.class));
        verify(categoryDAO, times(1)).flush();
        assertTrue(webCategories.size() == dbCategories.size() - removedDbCategories.size());
        assertTrue(removedWebCategories.containsAll(removedDbCategories));
        assertTrue(removedDbCategories.containsAll(removedWebCategories));
        assertTrue(removedDbCategories.size() == removedWebCategories.size());
    }

    @Test(expected = RemovingCategoriesWithTypesException.class)
    public void deleteMissingCategoriesWithTypesInDBTest() {
        List<ResourceCategory> webCategories = new ArrayList<>(allCategories);
        List<ResourceCategory> dbCategories = new ArrayList<>(allCategories);
        when(categoryServiceSpy.findAllResourceCategories()).thenReturn(dbCategories);
        webCategories.remove(leafCategory_1_3);
        categoryServiceSpy.deleteMissingCategoriesInDB(webCategories);
    }

    @Test
    public void createCategoryDTOTest() {
        ResourceCategoryDTO expected1 = rootCategory1DTO;
        ResourceCategoryDTO actual1 = categoryServiceSpy.createCategoryDTO(rootCategory1);
        assertEquals(expected1, actual1);
        ResourceCategoryDTO expected2 = branchCategory2DTO;
        ResourceCategoryDTO actual2 = categoryServiceSpy.createCategoryDTO(branchCategory2);
        assertEquals(expected2, actual2);
    }

    @Test
    public void mapFromDtoToResourceCategoryTest() {
        doAnswer(invocation -> {
            long id = invocation.getArgumentAt(0, long.class);
            return allCategories.stream().filter(c -> c.getId().equals(id)).findFirst();
        }).when(categoryServiceSpy).findCategoryById(anyLong());
        ResourceCategory expected1 = rootCategory1;
        ResourceCategory actual1 = categoryServiceSpy.mapFromDtoToResourceCategory(rootCategory1DTO);
        assertEquals(expected1, actual1);
        ResourceCategory expected2 = branchCategory2;
        ResourceCategory actual2 = categoryServiceSpy.mapFromDtoToResourceCategory(branchCategory2DTO);
        assertEquals(expected2, actual2);
    }

    @Test
    public void getTypesByCategoryIdTest() {
        doAnswer(invocation -> {
            long id = invocation.getArgumentAt(0, long.class);
            return allCategories.stream().filter(c -> c.getId().equals(id)).findFirst();
        }).when(categoryServiceSpy).findCategoryById(anyLong());
        List<ResourceType> expected1 = new ArrayList<>(Arrays.asList(type1, type2));
        List<ResourceType> actual1 = categoryServiceSpy.getTypesByCategoryId(Optional.ofNullable(leafCategory_1_3.getId()));
        assertTrue(actual1.containsAll(expected1));
        assertTrue(expected1.containsAll(actual1));
        assertTrue(actual1.size() == expected1.size());

        when(categoryServiceSpy.findAllResourceCategories()).thenReturn(allCategories);
        List<ResourceType> expected2 = new ArrayList<>(Arrays.asList(type1, type2, type3));
        List<ResourceType> actual2 = categoryServiceSpy.getTypesByCategoryId(Optional.empty());
        assertTrue(actual2.containsAll(expected2));
        assertTrue(expected2.containsAll(actual2));
        assertTrue(actual2.size() == expected2.size());
    }

    @Test(expected = InvalidResourceCategoryException.class)
    public void getTypesByCategoryIdNullTest() {
        doReturn(Optional.empty()).when(categoryServiceSpy).findCategoryById(anyLong());
        categoryServiceSpy.getTypesByCategoryId(Optional.of(10L));
    }
}
