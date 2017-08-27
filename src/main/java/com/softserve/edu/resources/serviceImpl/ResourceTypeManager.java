
package com.softserve.edu.resources.serviceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.softserve.edu.resources.dao.ResourceTypeDAO;
import com.softserve.edu.resources.dto.ResourceTypeDTO;
import com.softserve.edu.resources.entity.ResourceType;

public class ResourceTypeManager {

    private ResourceTypeDAO resourceTypeDAO;
    private Map<String, ResourceType> types = new HashMap<>();
  private Map<ResourceType, String> instances = new HashMap<>();

    public Collection<ResourceType> getTypes() {
        return Collections.unmodifiableCollection(types.values());
    }

  public int getTypeCount() {
    return types.values().size();
  }public void add(ResourceType resourceType) {
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
    if (type != null&& !instances.containsKey(typeName)) {
      System.out.printf("Creating ResourceTable '%s':%n", type.getName());
      type.getProperties().forEach(rp -> System.out.printf("adding field '%s'%n", rp.getName()));
      System.out.println("done.");instances.put(type, type.getTableName());
    }

  }

  public ResourceTypeManager setTypes(Map<String, ResourceType> types) {
    this.types = types;
    return this;
  }

  public Map<ResourceType, String> getInstances() {
    return Collections.unmodifiableMap(instances);
  }

  public ResourceTypeManager setInstances(Map<ResourceType, String> instances) {
    this.instances = instances;
    return this;
  }

  public int getInstancesCount() {
    return getInstances().size();
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
