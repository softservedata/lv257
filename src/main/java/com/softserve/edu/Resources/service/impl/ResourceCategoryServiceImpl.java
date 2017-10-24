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

    @Override
    public List<ResourceCategory> deployCategory(ResourceCategory rootCategory) {
        List<ResourceCategory> allCategories = new ArrayList<>(Arrays.asList(rootCategory));
        allCategories.addAll(getDescendants(rootCategory));
        return allCategories;
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

    @Override
    @Transactional
    public ResourceCategory mapFromDtoToResourceCategory(ResourceCategoryDTO categoryDTO) {
        return mapFromDtoToResourceCategory(categoryDTO, new HashSet<>());
    }

    @Override
    @Transactional
    public List<ResourceType> getTypesByCategoryId(Optional<Long> id) {
        List<ResourceCategory> categories;
        if (id.isPresent()) {
            Optional<ResourceCategory> rootCategory = findCategoryById(id.get());
            if (!rootCategory.isPresent()) {
                throw new InvalidResourceCategoryException("Requested Resource Category not found.");
            }
            categories = deployCategory(rootCategory.get());
        } else {
            categories = findAllResourceCategories();
        }
        return categories.stream()
                .map(ResourceCategory::getResourceTypes)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
}
