package com.softserve.edu.resources.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.softserve.edu.resources.dao.ResourceTypeDAO;
import com.softserve.edu.resources.entity.ResourceType;

public class ResourceTypeManager {

    private ResourceTypeDAO resourceTypeDAO;
    private Map<String, ResourceType> types = new HashMap<>();

    public ResourceTypeManager(ResourceTypeDAO resourceTypeDAO) {
        this.resourceTypeDAO = resourceTypeDAO;
    }

    public Collection<ResourceType> getTypes() {
        return Collections.unmodifiableCollection(types.values());
    }

    public void add(ResourceType resourceType) {
        types.putIfAbsent(resourceType.getName(), resourceType);
    }

    public void remove(ResourceType resourceType) {
        types.remove(resourceType.getName());
    }

    public Optional<ResourceType> get(String typeName) {
        return Optional.ofNullable(types.get(typeName));
    }

    public void create(String typeName) {
        ResourceType type = types.get(typeName);
        if (type != null) {
            System.out.printf("Creating ResourceTable '%s':%n", type.getName());
            type.getProperties().forEach(rp -> System.out
                    .printf("adding field '%s'%n", rp.getName()));
            System.out.println("done.");
        }
    }

    // Get all hierarchy
    public List<ResourceType> getAllResourceTypesAndParents() {
        return resourceTypeDAO.getAllResourceTypesAndParents();
    }

    // Get all leaves of hierarchy
    public List<ResourceType> getAllResourceTypes() {
        return resourceTypeDAO.getAllResourceTypes();
    }

    // Get all leaves of branch
    public List<ResourceType> getChildrensOfResourceType(
            ResourceType resourceType) {
        return resourceTypeDAO.getChildrensOfResourceType(resourceType);
    }

    // Build tree of types for view
    public List<String> buildTypeHierarchy(List<ResourceType> allTypes,
            String indentSymbol) {
        List<ResourceType> branches = new ArrayList<>(allTypes);
        allTypes.removeAll(this.getAllResourceTypes());
        List<String> tree = new ArrayList<>();
        for (ResourceType element : branches) {
            String indent;
            for (int i = 0; i < element.getHierarchyLevel(); i++) {
                indent = indent + indentSymbol;
            }
            tree.add(indent + element.getName());
        }
        return tree;
    }
}
