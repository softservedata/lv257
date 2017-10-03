/*
 *
 */

package com.softserve.edu.Resources.service.impl;

import com.softserve.edu.Resources.dao.ResourcePropertyDAO;
import com.softserve.edu.Resources.dto.ResourcePropertyDescription;
import com.softserve.edu.Resources.dto.ValueTypeDTO;
import com.softserve.edu.Resources.entity.ResourceProperty;
import com.softserve.edu.Resources.entity.ValueType;
import com.softserve.edu.Resources.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

/*
 * @PropertyManager's is a dictionary of any kind of ResourceProperties.
 */
@Service
@Transactional
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    ResourcePropertyDAO propertyDAO;

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
        return propertyDAO.findAll();
    }

    @Override
    public Optional<ResourceProperty> getProperty(String title, String units) {
        return propertyDAO.findByTitleAndUnits(title, units);
    }

    @Override
    public Optional<ResourceProperty> getProperty(String description) {
        return propertyDAO.findByDescription(description);
    }

    @Override
    public List<ResourceProperty> getProperties(String propertyTitle) {
        return propertyDAO.findByTitle(propertyTitle);
    }

    @Override
    public Long propertiesCount() {
        return propertyDAO.getCount();
    }

    @Override
    public List<ResourcePropertyDescription> getPropertyDescriptions() {
        List<ResourcePropertyDescription> collect = propertyDAO.findAll().stream()
                                                            .map(ResourcePropertyDescription::new)
                                                            .sorted()
                                                            .collect(toList());
        return collect;
    }

    @Override
    public List<ValueTypeDTO> getValueTypes() {
        return Arrays.stream(ValueType.values()).map(valueType -> new ValueTypeDTO(valueType)).collect(toList());
    }
}
