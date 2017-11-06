package com.softserve.edu.Resources.service.impl;

import com.softserve.edu.Resources.dao.ResourceCategoryDAO;
import com.softserve.edu.Resources.dto.ResourceCategoryDTO;
import com.softserve.edu.Resources.dto.ResourceTypeDTO;
import com.softserve.edu.Resources.dto.ViewTypesDTO;
import com.softserve.edu.Resources.entity.ResourceCategory;
import com.softserve.edu.Resources.entity.ResourceType;
import com.softserve.edu.Resources.exception.CycleDependencyException;
import com.softserve.edu.Resources.exception.InvalidResourceCategoryException;
import com.softserve.edu.Resources.exception.RemovingCategoriesWithTypesException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.invoke.MethodHandles;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Service layer for working with resource categories
 */
@Service
public class ResourceCategoryServiceImpl implements com.softserve.edu.Resources.service.ResourceCategoryService {

    /**
     * Logger for class
     */
    static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getName());

    /**
     * DAO for interaction with database
     */
    @Autowired
    private ResourceCategoryDAO resourceCategoryDAO;

    /**
     * Find resource category in database by ID
     *
     * @param id ID of resource category which should be retrieved
     * @return Optional with retrieved resource category
     */
    @Override
    @Transactional
    public Optional<ResourceCategory> findCategoryById(Long id) {
        logger.info("Finding category by ID " + id);
        return resourceCategoryDAO.findById(id);
    }

    /**
     * Find resource category in database by name
     *
     * @param categoryName name of resource category which should be retrieved
     * @return Optional with retrieved resource category
     */
    @Override
    @Transactional
    public Optional<ResourceCategory> findCategoryByName(
            String categoryName) {
        logger.info("Finding category by name " + categoryName);
        return resourceCategoryDAO.findByName(categoryName);
    }

    /**
     * Find all resource categories in database
     *
     * @return List with retrieved resource categories
     */
    @Override
    @Transactional
    public List<ResourceCategory> findAllResourceCategories() {
        logger.info("Finding all categories");
        return resourceCategoryDAO.findAll();
    }

    /**
     * Save resource category into database
     *
     * @param resourceCategory resource category which should be saved
     * @return persisted resource category
     */
    @Override
    @Transactional
    public ResourceCategory saveResourceCategory(ResourceCategory resourceCategory) {
        logger.info("Saving category " + resourceCategory);
        return resourceCategoryDAO.makePersistent(resourceCategory);
    }

    /**
     * Remove resource category from database
     *
     * @param resourceCategory resource category which should be removed
     */
    @Override
    @Transactional
    public void deleteResourceCategory(ResourceCategory resourceCategory) {
        logger.info("Removing category " + resourceCategory);
        resourceCategoryDAO.makeTransient(resourceCategory);
    }

    /**
     * Find all resource categories in database which haven't parent category
     *
     * @return List with retrieved resource categories
     */
    @Override
    @Transactional
    public List<ResourceCategory> findRootCategories() {
        logger.info("Finding root categories");
        return resourceCategoryDAO.findRootCategories();
    }

    /**
     * Find all nested resource categories for certain resource category
     *
     * @param resourceCategory resource category for which nested categories are looking for
     * @return List with nested resource categories
     * @throws CycleDependencyException
     */
    @Override
    public List<ResourceCategory> getDescendants(ResourceCategory resourceCategory) throws CycleDependencyException {
        return getDescendants(resourceCategory, new HashSet<>());
    }

    /**
     * Find all nested resource categories for certain resource category
     *
     * @param resourceCategory resource category for which nested categories are looking for
     * @param visited          HashSet with resource categories which are visited through recursive search
     * @return List with nested resource categories
     * @throws CycleDependencyException
     */
    private List<ResourceCategory> getDescendants(ResourceCategory resourceCategory, Set<ResourceCategory> visited)
            throws CycleDependencyException {
        logger.info("Getting nested categories for category " + resourceCategory);
        List<ResourceCategory> descendants = new ArrayList<>();
        visited.add(resourceCategory);
        if (resourceCategory.getChildrenCategories() != null && !resourceCategory.getChildrenCategories().isEmpty()) {
            for (ResourceCategory rc : resourceCategory.getChildrenCategories()) {
                if (!visited.contains(rc)) {
                    descendants.add(rc);
                } else {
                    logger.warn("Categories hierarchy has cycle dependencies. Some categories are reduplicative");
                    throw new CycleDependencyException("Categories hierarchy has cycle dependencies. Some categories are reduplicative");
                }
                descendants.addAll(getDescendants(rc, visited));
            }
        }
        return descendants;
    }

    /**
     * Find all ancestor resource categories for certain resource category
     *
     * @param resourceCategory resource category for which ancestor categories are looking for
     * @return List with parent resource categories
     * @throws CycleDependencyException
     */
    @Override
    public List<ResourceCategory> getAncestors(ResourceCategory resourceCategory) throws CycleDependencyException {
        return getAncestors(resourceCategory, new HashSet<>());
    }

    /**
     * Find all ancestor resource categories for certain resource category
     *
     * @param resourceCategory resource category for which ancestor categories are looking for
     * @param visited          HashSet with resource categories which are visited through recursive search
     * @return List with parent resource categories
     * @throws CycleDependencyException
     */
    private List<ResourceCategory> getAncestors(ResourceCategory resourceCategory, Set<ResourceCategory> visited)
            throws CycleDependencyException {
        logger.info("Getting ancestor categories for category " + resourceCategory);
        List<ResourceCategory> ancestors = new ArrayList<>();
        visited.add(resourceCategory);
        ResourceCategory parent = resourceCategory.getParentCategory();
        if (parent != null) {
            if (!visited.contains(parent)) {
                ancestors.addAll(getAncestors(parent, visited));
            } else {
                logger.warn("Categories hierarchy has cycle dependencies. Some categories are reduplicative");
                throw new CycleDependencyException("Categories hierarchy has cycle dependencies. Some categories are reduplicative");
            }
            ancestors.add(parent);
        }
        return ancestors;
    }

    /**
     * Deploy category and its nested categories into flat list of categories
     *
     * @param rootCategory resource category for which nested categories should be deployed into flat list
     * @return List with resource category which should be deployed and its nested categories
     */
    @Override
    public List<ResourceCategory> deployCategory(ResourceCategory rootCategory) {
        logger.info("Deploying category " + rootCategory + " and its nested categories into flat list of categories");
        List<ResourceCategory> allCategories = new ArrayList<>(Arrays.asList(rootCategory));
        allCategories.addAll(getDescendants(rootCategory));
        return allCategories;
    }

    /**
     * Deploy categories and its nested categories into flat list of categories
     *
     * @param rootCategories resource categories for which nested categories should be deployed into flat list
     * @return List with resource categories which should be deployed and its nested categories
     * @throws CycleDependencyException
     */
    @Override
    public List<ResourceCategory> deployAllCategoriesFromRoots(List<ResourceCategory> rootCategories)
            throws CycleDependencyException {
        logger.info("Deploying list of categories and its nested categories into flat list of categories");
        List<ResourceCategory> allCategories = new ArrayList<>(rootCategories);
        rootCategories.forEach(c -> allCategories.addAll(getDescendants(c)));
        if (allCategories.size() > new HashSet<>(allCategories).size()) {
            logger.warn("Categories hierarchy has cycle dependencies. Some categories are reduplicative");
            throw new CycleDependencyException("Categories hierarchy has cycle dependencies. Some categories are reduplicative");
        }
        return allCategories;
    }

    /**
     * Check whether names of resource categories are unique and correct
     *
     * @param categories    List with resource categories which names should be checked
     * @param minNameLength minimal valid length of name of the resource category
     * @param maxNameLength maximal valid length of name of the resource category
     * @return <tt>true</tt> if names of resource categories require to all given conditions
     */
    @Override
    public boolean isValidCategoryName(List<ResourceCategory> categories, int minNameLength, int maxNameLength) {
        logger.info("Checking categories for valid names");
        List<String> usedNames = new ArrayList<>();
        for (ResourceCategory category : categories) {
            String name = category.getCategoryName();
            if (name == null
                    || usedNames.contains(name.toLowerCase())
                    || name.length() < minNameLength
                    || name.length() > maxNameLength) {
                return false;
            }
            usedNames.add(name.toLowerCase());
        }
        return true;
    }

    /**
     * Remove resource categories which are absent in database after managing
     *
     * @param allCategoriesFromWeb List with resource categories which were returned after managing
     * @throws RemovingCategoriesWithTypesException
     */
    @Override
    @Transactional
    public void deleteMissingCategoriesInDB(List<ResourceCategory> allCategoriesFromWeb)
            throws RemovingCategoriesWithTypesException {
        logger.info("Removing categories which are absent in Database after managing");
        List<ResourceCategory> allCategoriesFromDB = findAllResourceCategories();
        allCategoriesFromDB.stream()
                .filter(c -> !allCategoriesFromWeb.stream()
                        .map(ResourceCategory::getId)
                        .collect(Collectors.toList()).contains(c.getId()))
                .forEach(dc -> {
                    if (dc.getResourceTypes().size() == 0 && getDescendants(dc).stream()
                            .allMatch(dcd -> dcd.getResourceTypes().size() == 0)) {
                        deleteResourceCategory(dc);
                    } else {
                        logger.warn("Can not remove from database resource categories which have resource types");
                        throw new RemovingCategoriesWithTypesException("Can not remove from database resource categories which have resource types");
                    }
                });
        resourceCategoryDAO.flush();
    }

    /**
     * Create DTO for resource category
     *
     * @param category resource category for which DTO should be created
     * @param created  HashSet with resource categories which are visited through recursive DTO creating
     * @return DTO of the given resource category
     */
    private ResourceCategoryDTO createCategoryDTO(ResourceCategory category, Set<ResourceCategory> created) {
        logger.info("Creating DTO for category " + category);
        ResourceCategoryDTO dto = new ResourceCategoryDTO();
        if (category != null) {
            created.add(category);
            if (category.getId() != null) {
                dto.setId(category.getId());
            }
            dto.setCategoryName(category.getCategoryName());
            ResourceCategory parent = category.getParentCategory();
            if (!created.contains(parent) && parent != null) {
                dto.setParentCategory(createCategoryDTO(parent, created));
            }
            dto.setChildrenCategories(category.getChildrenCategories().stream()
                    .map(c -> {
                        ResourceCategoryDTO childDTO = createCategoryDTO(c, created);
                        childDTO.setParentCategory(dto);
                        return childDTO;
                    }).collect(Collectors.toSet()));
            dto.setInstantiatedResourceTypes(category.getResourceTypes().stream()
                    .filter(ResourceType::isInstantiated)
                    .map(ResourceTypeDTO::new).collect(Collectors.toSet()));
            dto.setWithResourceTypes(category.getResourceTypes().size() > 0);
        }
        return dto;
    }

    /**
     * Create DTO for resource category
     *
     * @param category resource category for which DTO should be created
     * @return DTO of the given resource category
     */
    @Override
    public ResourceCategoryDTO createCategoryDTO(ResourceCategory category) {
        return createCategoryDTO(category, new HashSet<>());
    }

    /**
     * Map from DTO to resource category
     *
     * @param categoryDTO resource category DTO for which resource category should be mapped
     * @param mapped      HashSet with resource categories DTO which are visited through recursive mapping
     * @return resource category which responds to the given resource category DTO
     */
    private ResourceCategory mapFromDtoToResourceCategory(ResourceCategoryDTO categoryDTO, Set<ResourceCategoryDTO> mapped) {
        logger.info("Mapping from DTO " + categoryDTO + " to category");
        ResourceCategory targetCategory = new ResourceCategory();
        if (categoryDTO != null) {
            if (categoryDTO.getId() != null) {
                targetCategory = findCategoryById(categoryDTO.getId())
                        .orElse(targetCategory);
            }
            mapped.add(categoryDTO);
            targetCategory.setCategoryName(categoryDTO.getCategoryName());
            ResourceCategoryDTO parentDTO = categoryDTO.getParentCategory();
            if (!mapped.contains(parentDTO)) {
                if (parentDTO != null) {
                    targetCategory.setParentCategory(mapFromDtoToResourceCategory(parentDTO, mapped));
                } else targetCategory.setParentCategory(null);
            }
            ResourceCategory finalTargetCategory = targetCategory;
            targetCategory.setChildrenCategories(categoryDTO.getChildrenCategories().stream()
                    .map(c -> {
                        ResourceCategory child = mapFromDtoToResourceCategory(c, mapped);
                        child.setParentCategory(finalTargetCategory);
                        return child;
                    }).collect(Collectors.toSet()));
        }
        return targetCategory;
    }

    /**
     * Map from DTO to resource category
     *
     * @param categoryDTO resource category DTO for which resource category should be mapped
     * @return resource category which responds to the given resource category DTO
     */
    @Override
    @Transactional
    public ResourceCategory mapFromDtoToResourceCategory(ResourceCategoryDTO categoryDTO) {
        return mapFromDtoToResourceCategory(categoryDTO, new HashSet<>());
    }
}
