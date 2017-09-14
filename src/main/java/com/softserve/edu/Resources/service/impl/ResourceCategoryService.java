package com.softserve.edu.Resources.service.impl;

import com.softserve.edu.Resources.dao.ResourceCategoryDAO;
import com.softserve.edu.Resources.entity.ResourceCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
public class ResourceCategoryService {

    @Autowired
    private ResourceCategoryDAO resourceCategoryDAO;

    @Transactional
    public ResourceCategory findCategoryById(Long id) {
        return resourceCategoryDAO.findById(id);
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
}
