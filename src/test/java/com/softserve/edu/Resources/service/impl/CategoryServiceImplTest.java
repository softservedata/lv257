package com.softserve.edu.Resources.service.impl;

import com.softserve.edu.Resources.dao.ResourceCategoryDAO;
import com.softserve.edu.Resources.entity.ResourceCategory;
import com.softserve.edu.Resources.entity.ResourceType;
import com.softserve.edu.Resources.exception.CycleDependencyException;
import com.softserve.edu.Resources.exception.RemovingCategoriesWithTypesException;
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

    @Mock
    private ResourceCategoryDAO categoryDAO;

    @Spy
    @InjectMocks
    private ResourceCategoryServiceImpl categoryServiceSpy;

    @BeforeClass
    public static void setUp() {
        categoryId = 10L;
        categoryName = "branchCategory1";

        rootCategory1 = new ResourceCategory("rootCategory1", null);
        branchCategory1 = new ResourceCategory("branchCategory1", rootCategory1);
        branchCategory2 = new ResourceCategory("branchCategory2", rootCategory1);
        leafCategory_1_1 = new ResourceCategory("leafCategory_1_1", branchCategory1);
        leafCategory_1_2 = new ResourceCategory("leafCategory_1_2", branchCategory1);
        leafCategory_1_3 = new ResourceCategory("leafCategory_1_3", branchCategory1);
        leafCategory_2_1 = new ResourceCategory("leafCategory_2_1", branchCategory2);
        leafCategory_2_2 = new ResourceCategory("leafCategory_2_2", branchCategory2);
        rootCategory2 = new ResourceCategory("rootCategory2", null);

        branchCategory1.setChildrenCategories(new HashSet<>(Arrays.asList(leafCategory_1_1, leafCategory_1_2, leafCategory_1_3)));
        branchCategory2.setChildrenCategories(new HashSet<>(Arrays.asList(leafCategory_2_1, leafCategory_2_2)));
        rootCategory1.setChildrenCategories(new HashSet<>(Arrays.asList(branchCategory1, branchCategory2)));

        rootCategory1.setId(1L);
        rootCategory2.setId(2L);
        branchCategory1.setId(3L);
        branchCategory2.setId(4L);
        leafCategory_1_1.setId(5L);
        leafCategory_1_2.setId(6L);
        leafCategory_1_3.setId(7L);
        leafCategory_2_1.setId(8L);
        leafCategory_2_2.setId(9L);

        type1 = new ResourceType("type1");
        type2 = new ResourceType("type2");

        leafCategory_1_3.setResourceTypes(new HashSet<>(Arrays.asList(type1, type2)));

        allCategories = Arrays.asList(rootCategory1, rootCategory2, branchCategory1, branchCategory2, leafCategory_1_1,
                leafCategory_1_2, leafCategory_1_3, leafCategory_2_1, leafCategory_2_2);
        rootCategories = Arrays.asList(rootCategory1, rootCategory2);
        persistedCategory = new ResourceCategory("persistedCategory", branchCategory1).setId(categoryId);
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
        doNothing().when(categoryDAO).makeTransient(any(ResourceCategory.class));
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
        doAnswer(invocation -> {
            removedDbCategories.addAll(Arrays.stream(invocation.getArguments())
                    .map(o -> (ResourceCategory) o)
                    .collect(Collectors.toList()));
            return null;
        }).when(categoryServiceSpy).deleteResourceCategory(any(ResourceCategory.class));
        List<ResourceCategory> removedWebCategories = Arrays.asList(branchCategory2, leafCategory_2_1,
                leafCategory_2_2, rootCategory2);
        webCategories.removeAll(removedWebCategories);
        categoryServiceSpy.deleteMissingCategoriesInDB(webCategories);
        verify(categoryServiceSpy, times(removedWebCategories.size())).deleteResourceCategory(any(ResourceCategory.class));
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
}
