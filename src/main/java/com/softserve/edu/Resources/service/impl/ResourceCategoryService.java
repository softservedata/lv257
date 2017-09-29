package com.softserve.edu.Resources.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softserve.edu.Resources.dao.ResourceCategoryDAO;
import com.softserve.edu.Resources.dto.ResourceCategoryDTO;
import com.softserve.edu.Resources.entity.ResourceCategory;
import org.jgrapht.DirectedGraph;
import org.jgrapht.alg.CycleDetector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
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
        List<ResourceCategory> result = resourceCategoryDAO.findAll();
//        Comparator<ResourceCategory> categoryComparator = Comparator.comparing(ResourceCategory::getPathToRoot);
//        result.sort(categoryComparator);
        return result;
    }

    @Transactional
    public void saveResourceCategory(ResourceCategory resourceCategory) {
        setRootPath(resourceCategory);
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

    private void setRootPath(ResourceCategory resourceCategory) {
        ResourceCategory parentCategory = resourceCategory
                .getParentCategory();
        if (parentCategory != null) {
            resourceCategory.setPathToRoot(
                    parentCategory.getPathToRoot() + "/"
                            + resourceCategory.getCategoryName());
            resourceCategory.setHierarchyLevel(
                    parentCategory.getHierarchyLevel() + 1);
        } else {
            resourceCategory
                    .setPathToRoot("/" + resourceCategory.getCategoryName());
            resourceCategory.setHierarchyLevel(0);
        }
    }

    @Transactional
    public List<ResourceCategory> getRootsFromDB() {
        List<ResourceCategory> rootsFromDB = new ArrayList<>();
        for (ResourceCategory rc : findAllResourceCategories()) {
            if (rc.getParentCategory() == null) {
                rootsFromDB.add(rc);
            }
        }
        return rootsFromDB;
    }

    @Transactional
    public List<ResourceCategory> getChildrenFromDB(ResourceCategory resourceCategory) {
        List<ResourceCategory> childrenFromDB = new ArrayList<>();
        for (ResourceCategory rc : findAllResourceCategories()) {
            if (resourceCategory.equals(rc.getParentCategory())) {
                childrenFromDB.add(rc);
            }
        }
        return childrenFromDB;
    }

    @Transactional
    public List<ResourceCategory> getDescendantsFromDB(ResourceCategory resourceCategory) {
        List<ResourceCategory> descendantsFromDB = new ArrayList<>();
        for (ResourceCategory rc : findAllResourceCategories()) {
            if (resourceCategory.equals(rc.getParentCategory())) {
                descendantsFromDB.add(rc);
                descendantsFromDB.addAll(getDescendantsFromDB(rc));
            }
        }
        return descendantsFromDB;
    }

    @Transactional
    public Optional<ResourceCategory> getParentFromDB(ResourceCategory resourceCategory) {
        for (ResourceCategory rc : findAllResourceCategories()) {
            if (rc.equals(resourceCategory)) {
                return Optional.ofNullable(rc.getParentCategory());
            }
        }
        return null;
    }

    @Transactional
    public List<ResourceCategory> getAncestorsFromDB(ResourceCategory resourceCategory) {
        List<ResourceCategory> ancestorsFromDB = new ArrayList<>();
        for (ResourceCategory rc : findAllResourceCategories()) {
            if (rc.equals(resourceCategory) && rc.getParentCategory() != null) {
                ancestorsFromDB.add(rc.getParentCategory());
                ancestorsFromDB.addAll(getAncestorsFromDB(rc.getParentCategory()));
            }
        }
        return ancestorsFromDB;
    }

    public void fillParents(List<ResourceCategory> categoryList) {
        for (ResourceCategory rc : categoryList) {
            Collection<ResourceCategory> children = rc.getChildrenCategories();
            for (ResourceCategory ch : children) {
                ch.setParentCategory(rc);
            }
        }
    }

    public List<ResourceCategory> getDescendants(ResourceCategory resourceCategory) {
        List<ResourceCategory> descendants = new ArrayList<>();
        if (resourceCategory.getChildrenCategories() != null && !resourceCategory.getChildrenCategories().isEmpty()) {
            for (ResourceCategory rc : resourceCategory.getChildrenCategories()) {
                descendants.add(rc);
                descendants.addAll(getDescendants(rc));
            }
        }
        return descendants;
    }

    public List<ResourceCategory> getAncestors(ResourceCategory resourceCategory) {
        List<ResourceCategory> ancestors = new ArrayList<>();
        if (resourceCategory != null && resourceCategory.getParentCategory() != null) {
            ancestors.add(resourceCategory.getParentCategory());
            ancestors.addAll(getAncestors(resourceCategory.getParentCategory()));
        }
        return ancestors;
    }

    @Transactional
    public ResourceCategory insertCategoriesTEMPORARY() {
        findAllResourceCategories().stream().forEach(this::deleteResourceCategory);
        resourceCategoryDAO.flush();

        ResourceCategory root = new ResourceCategory("root", null, null);
        ResourceCategory branch1 = new ResourceCategory("branch1", root, null);
        ResourceCategory branch2 = new ResourceCategory("branch2", root, null);
        ResourceCategory leaf1_1 = new ResourceCategory("leaf1_1", branch1, null);
        ResourceCategory leaf1_2 = new ResourceCategory("leaf1_2", branch1, null);
        ResourceCategory leaf2_1 = new ResourceCategory("leaf2_1", branch2, null);
        ResourceCategory leaf2_2 = new ResourceCategory("leaf2_2", branch2, null);
        ResourceCategory leaf1_3 = new ResourceCategory("leaf1_3", branch1, null);

        root.getChildrenCategories().add(branch1);
        root.getChildrenCategories().add(branch2);
        branch1.getChildrenCategories().add(leaf1_1);
        branch1.getChildrenCategories().add(leaf1_2);
        branch1.getChildrenCategories().add(leaf1_3);
        branch2.getChildrenCategories().add(leaf2_1);
        branch2.getChildrenCategories().add(leaf2_2);

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

    @Transactional
    public String serializeCategoriesIntoJson(List<ResourceCategory> categories) {
        ObjectMapper mapper = new ObjectMapper();
        String json = new String();
        try {
//            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootCategories);
            json = mapper.writeValueAsString(categories);
        } catch (JsonProcessingException e) {
            System.out.println("Can not serialize list of root Resource Categories into JSON");
            e.printStackTrace();
        }
        return json;
    }

    @Transactional
    public List<ResourceCategory> deserializeCategoriesFromJson(String json) {
        ObjectMapper mapper = new ObjectMapper();
        List<ResourceCategory> rootCategories = new ArrayList<>();
        try {
            JavaType listType = mapper.getTypeFactory().constructCollectionType(List.class, ResourceCategory.class);
            rootCategories = mapper.readValue(json, listType);
        } catch (IOException e) {
            System.out.println("Can not deserialize JSON into list of root Resource Categories");
            e.printStackTrace();
        }
        return rootCategories;
    }

    public List<ResourceCategory> deployAllCategoriesFromRoots(List<ResourceCategory> rootCategories) {
        List<ResourceCategory> allCategories = new ArrayList<>(rootCategories);
        rootCategories.forEach(c -> allCategories.addAll(getDescendants(c)));
        allCategories.forEach(c -> System.out.println(c + " Parent: " + c.getParentCategory() + " Children: " + Arrays.toString(c.getChildrenCategories().toArray()) + " end;"));
        return allCategories;
    }

    @Transactional
    public void updateChangedCategories(List<ResourceCategory> categoriesFromWeb) {
        List<ResourceCategory> categoriesFromDB = findAllResourceCategories();
        List<ResourceCategory> changedCategories = new ArrayList<>();
        for (ResourceCategory c : categoriesFromWeb) {
            if (!categoriesFromDB.contains(c)
                    || (categoriesFromDB.get(categoriesFromDB.indexOf(c)).getParentCategory() != null && c.getParentCategory() == null)
                    || (categoriesFromDB.get(categoriesFromDB.indexOf(c)).getParentCategory() == null && c.getParentCategory() != null)
                    || !categoriesFromDB.get(categoriesFromDB.indexOf(c)).getParentCategory().getCategoryName().equals(c.getParentCategory().getCategoryName())) {
                changedCategories.add(c);
                System.out.println("Cahnged categories: " + Arrays.toString(changedCategories.toArray()));
            }
            if (!changedCategories.isEmpty()) {
                changedCategories.forEach(this::saveResourceCategory);
            }
        }
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
        for (ResourceCategory resourceCategory : categories) {
            for (ResourceCategory descendant : getDescendants(resourceCategory)) {
                if (getAncestors(resourceCategory).contains(descendant)) return true;
            }
        }
        return false;
    }

    public boolean hasCycleDependencies1(List<ResourceCategory> categories) {
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
            dto.setCategoryName(category.getCategoryName());
            if (!created.contains(category.getParentCategory()) && category.getParentCategory() != null) {
                dto.setParentCategory(createCategoryDTO(category.getParentCategory(), created));
            }
            dto.setChildrenCategories(category.getChildrenCategories().stream()
                    .map(c -> {
                        ResourceCategoryDTO childDTO = createCategoryDTO(c, created);
                        childDTO.setParentCategory(dto);
                        return childDTO;
                    }).collect(Collectors.toSet()));
        }
        return dto;
    }

    public ResourceCategoryDTO createCategoryDTO(ResourceCategory category) {
        return createCategoryDTO(category, new HashSet<>());
    }

    private ResourceCategory transferFromDtoToResourceCategory(ResourceCategoryDTO categoryDTO, Set<ResourceCategoryDTO> transferred, ResourceCategory targetCategory) {
        if (categoryDTO != null) {
            transferred.add(categoryDTO);
            targetCategory.setCategoryName(categoryDTO.getCategoryName());
            if (!transferred.contains(categoryDTO.getParentCategory()) && categoryDTO.getParentCategory() != null) {
                targetCategory.setParentCategory(transferFromDtoToResourceCategory(categoryDTO.getParentCategory(), transferred, targetCategory));
            }
            targetCategory.setChildrenCategories(categoryDTO.getChildrenCategories().stream()
                    .map(c -> {
                        ResourceCategory child = transferFromDtoToResourceCategory(c, transferred, targetCategory);
                        child.setParentCategory(targetCategory);
                        return child;
                    }).collect(Collectors.toSet()));
        }
        return targetCategory;
    }

    @Transactional
    public ResourceCategory transferFromDtoToResourceCategory(ResourceCategoryDTO categoryDTO) {
        if (categoryDTO.getId() != null) {
            ResourceCategory categoryFromDB = findCategoryById(categoryDTO.getId()).orElse(new ResourceCategory());
            return transferFromDtoToResourceCategory(categoryDTO, new HashSet<>(), categoryFromDB);
        } else {
            return transferFromDtoToResourceCategory(categoryDTO, new HashSet<>(), new ResourceCategory());
        }
    }
}
