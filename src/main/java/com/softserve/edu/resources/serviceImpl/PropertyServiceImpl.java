/*
 *
 */

package com.softserve.edu.resources.serviceImpl;

import com.softserve.edu.resources.entity.ResourceProperty;
import com.softserve.edu.resources.service.PropertyService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/*
 * @PropertyManager's is a dictionary of any kind of ResourceProperties.
 */
public class PropertyServiceImpl implements PropertyService {

    private Map<String, ResourceProperty> properties = new HashMap<>();

    public PropertyServiceImpl() {
    }

    @Override
    public boolean addProperty(ResourceProperty property) {
        int size = properties.size();
        properties.putIfAbsent(property.toString().toLowerCase(), property);
        return size < properties.size();
    }

    @Override
    public Map<String, ResourceProperty> getProperties() {
        return properties;
    }

    @Override
    public PropertyService setProperties(Map<String, ResourceProperty> properties) {
        this.properties = properties;
        return this;
    }

    @Override
    public Optional<ResourceProperty> getProperty(String propertyName, String unitsName) {
        final String description = String.join(", ", propertyName, unitsName).toLowerCase();
        return Optional.ofNullable(properties.get(description));
    }

    @Override
    public Optional<ResourceProperty> getProperty(String description) {
        return Optional.ofNullable(properties.get(description.toLowerCase()));
    }

    @Override
    public List<ResourceProperty> getProperties(String propertyName) {
        return properties.values().stream()
                       .filter(property -> property.getName().equalsIgnoreCase(propertyName))
                       .collect(Collectors.toList());
    }

    @Override
    public int propertiesCount() {
        return properties.size();
    }

    @Override
    public List<String> propertyDescriptions() {
        return properties.values().stream()
                       .map(ResourceProperty::getDescription)
                       .sorted()
                       .collect(Collectors.toList());
    }
}
