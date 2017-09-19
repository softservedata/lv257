package com.softserve.edu.Resources.service.impl;

import com.softserve.edu.Resources.entity.ResourceType;
import com.softserve.edu.Resources.service.ResourceTypeService;

import java.util.*;

public class ResourceTypeServiceImpl implements ResourceTypeService {

    private Map<String, ResourceType> types = new HashMap<>();
    private Map<ResourceType, String> instances = new HashMap<>();

    @Override
    public Collection<ResourceType> getTypes() {
        return Collections.unmodifiableCollection(types.values());
    }

    @Override
    public int getTypeCount() {
        return types.values().size();
    }
    @Override
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
            type.getProperties().forEach(rp -> System.out.printf("adding field '%s'%n", rp.getTitle()));
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

}
