package com.softserve.edu.Resources.service;

import com.softserve.edu.Resources.dto.ResourceCategoryDTO;
import com.softserve.edu.Resources.entity.ResourceCategory;
import com.softserve.edu.Resources.exception.CycleDependencyException;
import com.softserve.edu.Resources.exception.RemovingCategoriesWithTypesException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ResourceCategoryService {
    @Transactional
    Optional<ResourceCategory> findCategoryById(Long id);

    @Transactional
    Optional<ResourceCategory> findCategoryByName(
            String categoryName);

    @Transactional
    List<ResourceCategory> findAllResourceCategories();

    @Transactional
    void saveResourceCategory(ResourceCategory resourceCategory);

    @Transactional
    void deleteResourceCategory(ResourceCategory resourceCategory);

    @Transactional
    List<ResourceCategory> findRootCategories();

    List<ResourceCategory> getDescendants(ResourceCategory resourceCategory) throws CycleDependencyException;

    List<ResourceCategory> getAncestors(ResourceCategory resourceCategory) throws CycleDependencyException;

    List<ResourceCategory> deployAllCategoriesFromRoots(List<ResourceCategory> rootCategories)
            throws CycleDependencyException;

    boolean isValidCategoryName(List<ResourceCategory> categories, int minNameLength, int maxNameLength);

    @Transactional
    void deleteMissingCategoriesInDB(List<ResourceCategory> allCategoriesFromWeb)
            throws RemovingCategoriesWithTypesException;

    ResourceCategoryDTO createCategoryDTO(ResourceCategory category);

    @Transactional
    ResourceCategory mapFromDtoToResourceCategory(ResourceCategoryDTO categoryDTO);

    @Transactional
    void insertCategoriesTEMPORARY();
}
