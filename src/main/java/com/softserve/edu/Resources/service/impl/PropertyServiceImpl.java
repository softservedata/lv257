/*
 *
 */

package com.softserve.edu.Resources.service.impl;

import com.softserve.edu.Resources.dao.ResourcePropertyDAO;
import com.softserve.edu.Resources.entity.ResourceProperty;
import com.softserve.edu.Resources.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/*
 * @PropertyManager's is a dictionary of any kind of ResourceProperties.
 */
@Service
@Transactional
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    ResourcePropertyDAO propertyDAO;

    private List<ResourceProperty> properties = new ArrayList<>();

    {
        properties.add(new ResourceProperty("Width").setUnits("meters (m)"));
        properties.add(new ResourceProperty("Width").setUnits("centimeters (cm)"));
        properties.add(new ResourceProperty("Height").setUnits("meters (m)"));
        properties.add(new ResourceProperty("Height").setUnits("centimeters (cm)"));
        properties.add(new ResourceProperty("Power").setUnits("watts (W)"));
        properties.add(new ResourceProperty("Power").setUnits("kilo watts (k)"));
        properties.add(new ResourceProperty("Power").setUnits("horse power(H)"));
        properties.add(new ResourceProperty("Weight").setUnits("kilograms(kg)"));
        properties.add(new ResourceProperty("Weight").setUnits("pounds(p)"));
        properties.add(new ResourceProperty("Weight").setUnits("tons(t)"));
        properties.add(new ResourceProperty("Color"));
    }

    public PropertyServiceImpl() {
    }

    @Override
    public ResourceProperty add(ResourceProperty property) {
        return propertyDAO.makePersistent(property);
    }

    @Override
    public ResourceProperty update(ResourceProperty property) {
        return propertyDAO.makePersistent(property);
//        return propertyDAO.update(property);
    }

    @Override
    public List<ResourceProperty> getProperties() {
        return properties;
//        return propertyDAO.findAll();
    }

    @Override
    public Optional<ResourceProperty> getProperty(String title, String units) {
        return propertyDAO.findByTitleAndUnits(title, units);
    }

    @Override
    public Optional<ResourceProperty> getProperty(String description) {
        return properties.stream()
                       .filter(p -> String.join(", ", p.getTitle(), p.getUnits()).equalsIgnoreCase(description))
                       .findFirst();
    }

    @Override
    public List<ResourceProperty> getProperties(String propertyName) {
        return properties.stream()
                       .filter(property -> property.getTitle().equalsIgnoreCase(propertyName))
                       .collect(Collectors.toList());
    }

    @Override
    public int propertiesCount() {
        return properties.size();
    }

    @Override
    public List<String> getPropertyDescriptions() {
        return properties.stream()
                       .map(ResourceProperty::getDescription)
                       .sorted()
                       .collect(Collectors.toList());
    }
}
