package edu.softserve.service;

import edu.softserve.dao.ResourceCategoryDAO;
import edu.softserve.entity.ResourceCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ResourceCategoryService {

    @Autowired
    private ResourceCategoryDAO resourceCategoryDAO;

    @Transactional
    public ResourceCategory findCategoryById(Long id) {
        return resourceCategoryDAO
                .findById(id);
    }

    @Transactional
    public ResourceCategory findCategoryByName(
            String categoryName) {
        return resourceCategoryDAO
                .findByName(categoryName);
    }

    @Transactional
    public List<ResourceCategory> findAllResourceCategories() {
        return resourceCategoryDAO
                .findAll();
    }

    @Transactional
    public void addResourceCategory(ResourceCategory resourceCategory) {
        ResourceCategory parentCategory = resourceCategory
                .getParentCategory();
        if (parentCategory != null) {
            resourceCategory.setPathToRoot(
                    parentCategory.getPathToRoot() + "/"
                            + resourceCategory.getId());
            resourceCategory.setHierarchyLevel(
                    parentCategory.getHierarchyLevel() + 1);
        } else {
            resourceCategory
                    .setPathToRoot("/" + resourceCategory.getId());
            resourceCategory.setHierarchyLevel(0);
        }
        resourceCategoryDAO.makePersistent(resourceCategory);
    }
}
