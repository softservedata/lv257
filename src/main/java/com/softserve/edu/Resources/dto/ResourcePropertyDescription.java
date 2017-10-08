package com.softserve.edu.Resources.dto;

import com.softserve.edu.Resources.entity.ResourceProperty;

public class ResourcePropertyDescription implements Comparable {

    public final long id;
    public final String title;
    public final String units;
    public final String unitsShort;

    public ResourcePropertyDescription(ResourceProperty property) {
        id = property.getId();
        title = property.getTitle();
        units = property.getUnits();
        unitsShort = property.getUnitsShort();
    }

    @Override
    public int compareTo(Object o) {
        ResourcePropertyDescription rpd = (ResourcePropertyDescription) o;
        if (title.compareTo(rpd.title) == 0)
            return units.compareTo(rpd.units);
        return title.compareTo(rpd.title);
    }
}
