package com.softserve.edu.Resources.service.impl;

import com.softserve.edu.Resources.dao.ResourceCategoryDAO;
import com.softserve.edu.Resources.dto.ResourceCategoryDTO;
import com.softserve.edu.Resources.dto.ResourceTypeDTO;
import com.softserve.edu.Resources.entity.ResourceCategory;
import com.softserve.edu.Resources.entity.ResourceType;
import com.softserve.edu.Resources.exception.CycleDependencyException;
import com.softserve.edu.Resources.exception.RemovingCategoriesWithTypesException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ResourceCategoryServiceImpl implements com.softserve.edu.Resources.service.ResourceCategoryService {

    @Autowired
    private ResourceCategoryDAO resourceCategoryDAO;

    @Override
    @Transactional
    public Optional<ResourceCategory> findCategoryById(Long id) {
        return resourceCategoryDAO.findById(id);
    }

    @Override
    @Transactional
    public Optional<ResourceCategory> findCategoryByName(
            String categoryName) {
        return resourceCategoryDAO.findByName(categoryName);
    }

    @Override
    @Transactional
    public List<ResourceCategory> findAllResourceCategories() {
        return resourceCategoryDAO.findAll();
    }

    @Override
    @Transactional
    public ResourceCategory saveResourceCategory(ResourceCategory resourceCategory) {
        return resourceCategoryDAO.makePersistent(resourceCategory);
    }

    @Override
    @Transactional
    public void deleteResourceCategory(ResourceCategory resourceCategory) {
        resourceCategoryDAO.makeTransient(resourceCategory);
    }

    @Override
    @Transactional
    public List<ResourceCategory> findRootCategories() {
        return resourceCategoryDAO.findRootCategories();
    }

    @Override
    public List<ResourceCategory> getDescendants(ResourceCategory resourceCategory) throws CycleDependencyException {
        return getDescendants(resourceCategory, new HashSet<>());
    }

    private List<ResourceCategory> getDescendants(ResourceCategory resourceCategory, Set<ResourceCategory> visited)
            throws CycleDependencyException {
        List<ResourceCategory> descendants = new ArrayList<>();
        visited.add(resourceCategory);
        if (resourceCategory.getChildrenCategories() != null && !resourceCategory.getChildrenCategories().isEmpty()) {
            for (ResourceCategory rc : resourceCategory.getChildrenCategories()) {
                if (!visited.contains(rc)) {
                    descendants.add(rc);
                } else {
                    throw new CycleDependencyException("Categories hierarchy has cycle dependencies. Some categories are reduplicative");
                }
                descendants.addAll(getDescendants(rc, visited));
            }
        }
        return descendants;
    }

    @Override
    public List<ResourceCategory> getAncestors(ResourceCategory resourceCategory) throws CycleDependencyException {
        return getAncestors(resourceCategory, new HashSet<>());
    }

    private List<ResourceCategory> getAncestors(ResourceCategory resourceCategory, Set<ResourceCategory> visited)
            throws CycleDependencyException {
        List<ResourceCategory> ancestors = new ArrayList<>();
        visited.add(resourceCategory);
        ResourceCategory parent = resourceCategory.getParentCategory();
        if (parent != null) {
            if (!visited.contains(parent)) {
                ancestors.addAll(getAncestors(parent, visited));
            } else {
                throw new CycleDependencyException("Categories hierarchy has cycle dependencies. Some categories are reduplicative");
            }
            ancestors.add(parent);
        }
        return ancestors;
    }

    @Transactional
    public void insertCategoriesTEMPORARY() {
/*        findAllResourceCategories().forEach(this::deleteResourceCategory);
        resourceCategoryDAO.flush();

        ResourceCategory category = new ResourceCategory("category", null);
        ResourceCategory subcategory1 = new ResourceCategory("subcategory1", category);
        ResourceCategory subcategory2 = new ResourceCategory("subcategory2", category);
        ResourceCategory typecategory1_1 = new ResourceCategory("typecategory1_1", subcategory1);
        ResourceCategory typecategory1_2 = new ResourceCategory("typecategory1_2", subcategory1);
        ResourceCategory typecategory2_1 = new ResourceCategory("typecategory2_1", subcategory2);
        ResourceCategory typecategory2_2 = new ResourceCategory("typecategory2_2", subcategory2);
        ResourceCategory typecategory1_3 = new ResourceCategory("typecategory1_3", subcategory1);

        category.getChildrenCategories().add(subcategory1);
        category.getChildrenCategories().add(subcategory2);
        subcategory1.getChildrenCategories().add(typecategory1_1);
        subcategory1.getChildrenCategories().add(typecategory1_2);
        subcategory1.getChildrenCategories().add(typecategory1_3);
        subcategory2.getChildrenCategories().add(typecategory2_1);
        subcategory2.getChildrenCategories().add(typecategory2_2);

        ResourceType type1 = new ResourceType("Land");
        type1.setCategory(typecategory2_2);
        type1.setTableName("Land");
        type1.setInstantiated(true);
        typecategory2_2.getResourceTypes().add(type1);
        ResourceType type3 = new ResourceType("Bank account");
        type3.setCategory(typecategory2_2);
        type3.setTableName("Bank account");
        type3.setInstantiated(true);
        typecategory2_2.getResourceTypes().add(type3);
        ResourceType type2 = new ResourceType("Boat");
        type2.setCategory(typecategory2_2);
        type2.setTableName("Boat");
        type2.setInstantiated(true);
        typecategory2_2.getResourceTypes().add(type2);

        saveResourceCategory(category);
*//*        saveResourceCategory(branch1);
        saveResourceCategory(branch2);
        saveResourceCategory(leaf1_1);
        saveResourceCategory(leaf1_2);
        saveResourceCategory(leaf2_1);
        saveResourceCategory(leaf2_2);
        saveResourceCategory(leaf1_3);*/
    }

    @Override
    public List<ResourceCategory> deployAllCategoriesFromRoots(List<ResourceCategory> rootCategories)
            throws CycleDependencyException {
        List<ResourceCategory> allCategories = new ArrayList<>(rootCategories);
        rootCategories.forEach(c -> allCategories.addAll(getDescendants(c)));
        if (allCategories.size() > new HashSet<>(allCategories).size()) {
            throw new CycleDependencyException("Categories hierarchy has cycle dependencies. Some categories are reduplicative");
        }
        return allCategories;
    }

    @Override
    public boolean isValidCategoryName(List<ResourceCategory> categories, int minNameLength, int maxNameLength) {
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

    @Override
    @Transactional
    public void deleteMissingCategoriesInDB(List<ResourceCategory> allCategoriesFromWeb)
            throws RemovingCategoriesWithTypesException {
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
                        throw new RemovingCategoriesWithTypesException("Can not remove from database resource categories which have resource types");
                    }
                });
        resourceCategoryDAO.flush();
    }

    private ResourceCategoryDTO createCategoryDTO(ResourceCategory category, Set<ResourceCategory> created) {
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

    @Override
    public ResourceCategoryDTO createCategoryDTO(ResourceCategory category) {
        return createCategoryDTO(category, new HashSet<>());
    }

    private ResourceCategory mapFromDtoToResourceCategory(ResourceCategoryDTO categoryDTO, Set<ResourceCategoryDTO> mapped) {
        ResourceCategory targetCategory = new ResourceCategory();
        if (categoryDTO != null) {
            if (categoryDTO.getId() != null) {
                targetCategory = findCategoryById(categoryDTO.getId()).orElse(targetCategory);
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

    @Override
    @Transactional
    public ResourceCategory mapFromDtoToResourceCategory(ResourceCategoryDTO categoryDTO) {
        return mapFromDtoToResourceCategory(categoryDTO, new HashSet<>());
    }
}
