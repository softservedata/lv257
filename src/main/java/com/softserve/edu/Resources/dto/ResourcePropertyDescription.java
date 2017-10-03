package com.softserve.edu.Resources.dto;

import com.softserve.edu.Resources.entity.ResourceProperty;

public class ResourcePropertyDescription implements Comparable{
    public final long id;
    public final String description;

    public ResourcePropertyDescription(ResourceProperty property) {
        this.id = property.getId();
        this.description = property.getDescription();
    }

    @Override
    public int compareTo(Object o) {
        ResourcePropertyDescription rpd = (ResourcePropertyDescription) o;
        return description.compareTo(rpd.description);
    }
}
