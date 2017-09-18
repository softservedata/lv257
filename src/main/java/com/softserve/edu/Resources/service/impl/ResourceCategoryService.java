package com.softserve.edu.Resources.service.impl;

import com.softserve.edu.Resources.dao.ResourceCategoryDAO;
import com.softserve.edu.Resources.entity.ResourceCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class ResourceCategoryService {

    @Autowired
    private ResourceCategoryDAO resourceCategoryDAO;

    @Transactional
    public ResourceCategory findCategoryById(Long id) {
        return resourceCategoryDAO.findById(id).get();
    }

    @Transactional
    public ResourceCategory findCategoryByName(
            String categoryName) {
        return resourceCategoryDAO.findByName(categoryName);
    }

    @Transactional
    public List<ResourceCategory> findAllResourceCategories() {
        List<ResourceCategory> result = resourceCategoryDAO.findAll();
        Comparator<ResourceCategory> categoryComparator = Comparator.comparing(ResourceCategory::getPathToRoot);
        result.sort(categoryComparator);
        return result;
    }

    @Transactional
    public void addResourceCategory(ResourceCategory resourceCategory) {
        setRootPath(resourceCategory);
//        List<ResourceCategory> existingCategories = this.findAllResourceCategories();
//        if (existingCategories.stream().noneMatch(c -> c.getCategoryName().equalsIgnoreCase(resourceCategory.getCategoryName())))
        {
            resourceCategoryDAO.makePersistent(resourceCategory);
        }
    }

    @Transactional
    public ResourceCategory updateResourceCategory(ResourceCategory resourceCategory) {
        setRootPath(resourceCategory);
        return resourceCategoryDAO.merge(resourceCategory);
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
    public Optional<ResourceCategory> getRoot() {
        for (ResourceCategory rc : findAllResourceCategories()) {
            if (rc.getParentCategory() == null) {
                return Optional.ofNullable(rc);
            }
        }
        return null;
    }

    @Transactional
    public List<ResourceCategory> getChildren(ResourceCategory resourceCategory) {
        List<ResourceCategory> list = new ArrayList<>();
        for (ResourceCategory rc : findAllResourceCategories()) {
            if (resourceCategory.equals(rc.getParentCategory())) {
                list.add(rc);
            }
        }
        return list;
    }

    @Transactional
    public List<ResourceCategory> getDescendants(ResourceCategory resourceCategory) {
        List<ResourceCategory> list = new ArrayList<>();
        for (ResourceCategory rc : findAllResourceCategories()) {
            if (resourceCategory.equals(rc.getParentCategory())) {
                list.add(rc);
                list.addAll(getDescendants(rc));
            }
        }
        return list;
    }

    @Transactional
    public Optional<ResourceCategory> getParent(ResourceCategory resourceCategory) {
        for (ResourceCategory rc : findAllResourceCategories()) {
            if (rc.equals(resourceCategory)) {
                return Optional.ofNullable(rc.getParentCategory());
            }
        }
        return null;
    }

    @Transactional
    public List<ResourceCategory> getAncestors(ResourceCategory resourceCategory) {
        List<ResourceCategory> list = new ArrayList<>();
        for (ResourceCategory rc : findAllResourceCategories()) {
            if (rc.equals(resourceCategory) && rc.getParentCategory() != null) {
                list.add(rc.getParentCategory());
                list.addAll(getAncestors(rc.getParentCategory()));
            }
        }
        return list;
    }
}
