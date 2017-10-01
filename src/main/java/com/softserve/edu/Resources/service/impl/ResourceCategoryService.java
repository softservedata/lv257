package com.softserve.edu.Resources.service.impl;

import com.softserve.edu.Resources.dao.ResourceCategoryDAO;
import com.softserve.edu.Resources.dto.ResourceCategoryDTO;
import com.softserve.edu.Resources.dto.ResourceTypeDTO;
import com.softserve.edu.Resources.entity.ResourceCategory;
import com.softserve.edu.Resources.entity.ResourceType;
import com.softserve.edu.Resources.exception.CycleDependencyException;
import org.jgrapht.DirectedGraph;
import org.jgrapht.alg.CycleDetector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ResourceCategoryService {

    @Autowired
    private ResourceCategoryDAO resourceCategoryDAO;

    @Transactional
    public Optional<ResourceCategory> findCategoryById(Long id) {
        return resourceCategoryDAO.findById(id);
    }

    @Transactional
    public Optional<ResourceCategory> findCategoryByName(
            String categoryName) {
        return resourceCategoryDAO.findByName(categoryName);
    }

    @Transactional
    public List<ResourceCategory> findAllResourceCategories() {
        return resourceCategoryDAO.findAll();
    }

    @Transactional
    public void saveResourceCategory(ResourceCategory resourceCategory) {
//        List<ResourceCategory> existingCategories = this.findAllResourceCategories();
//        if (existingCategories.stream().noneMatch(c -> c.getCategoryName().equalsIgnoreCase(resourceCategory.getCategoryName())))
        {
            resourceCategoryDAO.makePersistent(resourceCategory);
        }
    }

    @Transactional
    public void deleteResourceCategory(ResourceCategory resourceCategory) {
        resourceCategoryDAO.makeTransient(resourceCategory);
    }

    @Transactional
    public List<ResourceCategory> findRootCategories() {
        return findAllResourceCategories().stream()
                .filter(c -> c.getParentCategory() == null)
                .collect(Collectors.toList());
    }

    public List<ResourceCategory> getDescendants(ResourceCategory resourceCategory) {
        return getDescendants(resourceCategory, new HashSet<>());
    }

    private List<ResourceCategory> getDescendants(ResourceCategory resourceCategory, Set<ResourceCategory> visited) {
        List<ResourceCategory> descendants = new ArrayList<>();
        visited.add(resourceCategory);
        if (resourceCategory.getChildrenCategories() != null && !resourceCategory.getChildrenCategories().isEmpty()) {
            for (ResourceCategory rc : resourceCategory.getChildrenCategories()) {
                if (!visited.contains(rc)) {
                    descendants.add(rc);
                } else {
                    throw new CycleDependencyException("Elements " + resourceCategory + " and " + rc + " are involved in cycle dependencies");
                }
                descendants.addAll(getDescendants(rc, visited));
            }
        }
        return descendants;
    }

    public List<ResourceCategory> getAncestors(ResourceCategory resourceCategory) {
        return getAncestors(resourceCategory, new HashSet<>());
    }

    private List<ResourceCategory> getAncestors(ResourceCategory resourceCategory, Set<ResourceCategory> visited) {
        List<ResourceCategory> ancestors = new ArrayList<>();
        visited.add(resourceCategory);
        ResourceCategory parent = resourceCategory.getParentCategory();
        if (parent != null) {
            if (!visited.contains(parent)) {
                ancestors.addAll(getAncestors(parent, visited));
            } else {
                throw new CycleDependencyException("Elements " + resourceCategory + " and " + parent + " are involved in cycle dependencies");
            }
            ancestors.add(parent);
        }
        return ancestors;
    }

    @Transactional
    public ResourceCategory insertCategoriesTEMPORARY() {
        findAllResourceCategories().forEach(this::deleteResourceCategory);
        resourceCategoryDAO.flush();

        ResourceCategory root = new ResourceCategory("root", null);
        ResourceCategory branch1 = new ResourceCategory("branch1", root);
        ResourceCategory branch2 = new ResourceCategory("branch2", root);
        ResourceCategory leaf1_1 = new ResourceCategory("leaf1_1", branch1);
        ResourceCategory leaf1_2 = new ResourceCategory("leaf1_2", branch1);
        ResourceCategory leaf2_1 = new ResourceCategory("leaf2_1", branch2);
        ResourceCategory leaf2_2 = new ResourceCategory("leaf2_2", branch2);
        ResourceCategory leaf1_3 = new ResourceCategory("leaf1_3", branch1);

        root.getChildrenCategories().add(branch1);
        root.getChildrenCategories().add(branch2);
        branch1.getChildrenCategories().add(leaf1_1);
        branch1.getChildrenCategories().add(leaf1_2);
        branch1.getChildrenCategories().add(leaf1_3);
        branch2.getChildrenCategories().add(leaf2_1);
        branch2.getChildrenCategories().add(leaf2_2);

        ResourceType type1 = new ResourceType("type1");
        type1.setTableName("AAA");
        type1.setCategory(leaf2_2);
        leaf2_2.getResourceTypes().add(type1);

        saveResourceCategory(root);
/*        saveResourceCategory(branch1);
        saveResourceCategory(branch2);
        saveResourceCategory(leaf1_1);
        saveResourceCategory(leaf1_2);
        saveResourceCategory(leaf2_1);
        saveResourceCategory(leaf2_2);
        saveResourceCategory(leaf1_3);*/

        return root;
    }

    public List<ResourceCategory> deployAllCategoriesFromRoots(List<ResourceCategory> rootCategories) {
        List<ResourceCategory> allCategories = new ArrayList<>(rootCategories);
        rootCategories.forEach(c -> allCategories.addAll(getDescendants(c)));
        return allCategories;
    }

    @Transactional
    public void deleteMissingCategoriesInDB(List<ResourceCategory> rootCategoriesFromWeb) {
        List<ResourceCategory> allCategoriesFromWeb = deployAllCategoriesFromRoots(rootCategoriesFromWeb);
        List<ResourceCategory> allCategoriesFromDB = findAllResourceCategories();
        allCategoriesFromDB.stream()
                .filter(c -> !allCategoriesFromWeb.stream()
                        .map(ResourceCategory::getId)
                        .collect(Collectors.toList()).contains(c.getId()))
                .forEach(this::deleteResourceCategory);
        resourceCategoryDAO.flush();
    }

    public boolean hasCycleDependencies(List<ResourceCategory> categories) {
        DirectedGraph<ResourceCategory, DefaultEdge> categoriesGraph = new SimpleDirectedGraph<>(DefaultEdge.class);
        for (ResourceCategory category : categories) {
            categoriesGraph.addVertex(category);
            if (category.getParentCategory() != null) {
                categoriesGraph.addEdge(category, category.getParentCategory());
            }
        }
        CycleDetector<ResourceCategory, DefaultEdge> cycleDetector = new CycleDetector<>(categoriesGraph);
        return cycleDetector.detectCycles();
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
                    }).collect(Collectors.toCollection(TreeSet::new)));
            dto.setResourceTypes(category.getResourceTypes().stream()
                    .map(ResourceTypeDTO::new).collect(Collectors.toSet()));
            List<ResourceCategory> ancestors = getAncestors(category);
            dto.setDepth(ancestors.size());
            ancestors.add(category);
            dto.setTreePath(ancestors.stream()
                    .map(ResourceCategory::getCategoryName).collect(Collectors.joining("/", "/", "")));
        }
        return dto;
    }

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

    @Transactional
    public ResourceCategory mapFromDtoToResourceCategory(ResourceCategoryDTO categoryDTO) {
        return mapFromDtoToResourceCategory(categoryDTO, new HashSet<>());
    }
}
