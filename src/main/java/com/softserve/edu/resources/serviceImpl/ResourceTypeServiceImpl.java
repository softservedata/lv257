
package com.softserve.edu.resources.serviceImpl;

import com.softserve.edu.resources.dao.ResourceTypeDAO;
import com.softserve.edu.resources.dto.ResourceTypeDTO;
import com.softserve.edu.resources.entity.ResourceType;
import com.softserve.edu.resources.service.ResourceTypeService;

import java.util.*;

public class ResourceTypeServiceImpl implements ResourceTypeService {

    private ResourceTypeDAO resourceTypeDAO;
    private Map<String, ResourceType> types = new HashMap<>();
    private Map<ResourceType, String> instances = new HashMap<>();

    @Override
    public Collection<ResourceType> getTypes() {
        return Collections.unmodifiableCollection(types.values());
    }

    @Override
    public int getTypeCount() {
        return types.values().size();
    }@Override
    public void add(ResourceType resourceType) {
        types.putIfAbsent(resourceType.getName(), resourceType);
    }

    @Override
    public void remove(ResourceType resourceType) {
        types.remove(resourceType.getName());
    }

    @Override
    public Optional<ResourceType> get(String typeName) {
        return Optional.ofNullable(types.get(typeName));
    }

    @Override
    public void create(String typeName) {
        ResourceType type = types.get(typeName);
        if (type != null&& !instances.containsKey(typeName)) {
            System.out.printf("Creating ResourceTable '%s':%n", type.getName());
            type.getProperties().forEach(rp -> System.out.printf("adding field '%s'%n", rp.getName()));
            System.out.println("done.");instances.put(type, type.getTableName());
        }

    }

    @Override
    public ResourceTypeService setTypes(Map<String, ResourceType> types) {
        this.types = types;
        return this;
    }

    @Override
    public Map<ResourceType, String> getInstances() {
        return Collections.unmodifiableMap(instances);
    }

    @Override
    public ResourceTypeService setInstances(Map<ResourceType, String> instances) {
        this.instances = instances;
        return this;
    }

    @Override
    public int getInstancesCount() {
        return getInstances().size();
    }
    // Get all hierarchy
    @Override
    public List<ResourceType> getAllResourceTypesAndParents() {
        return resourceTypeDAO.getAllResourceTypesAndParents();
    }

    // Get all leaves of hierarchy
    @Override
    public List<ResourceType> getAllResourceTypes() {
        return resourceTypeDAO.getAllResourceTypes();
    }

    // Get all leaves of branch
    @Override
    public List<ResourceType> getChildrensOfResourceType(
                                                                ResourceType resourceType) {
        return resourceTypeDAO.getChildrensOfResourceType(resourceType);
    }

    // Build tree of types for view
    @Override
    public List<ResourceTypeDTO> buildTypeHierarchy() {
        List<ResourceType> allTypes = this.getAllResourceTypesAndParents();
        List<ResourceType> onlyLeaves = this.getAllResourceTypes();
        List<ResourceTypeDTO> typeDTOList = new ArrayList<>();
        String typeName = "";
        Set<String> propertiesNames = new HashSet<>();
        String pathToRoot = "";
        int hierarchyLevel = 0;
        boolean isLeafType = false;
        for (ResourceType type : allTypes) {
            typeName = type.getName();
            type.getProperties()
                    .forEach(value -> propertiesNames.add(value.getName()));
            pathToRoot = type.getPathToRoot();
            hierarchyLevel = type.getHierarchyLevel();
            isLeafType = onlyLeaves.contains(type) ? true : false;
            typeDTOList.add(new ResourceTypeDTO(typeName, propertiesNames,
                                                pathToRoot, hierarchyLevel, isLeafType));
        }
        return typeDTOList;
    }
}
