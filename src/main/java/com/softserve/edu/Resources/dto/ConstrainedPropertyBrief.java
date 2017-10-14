package com.softserve.edu.Resources.dto;

import com.softserve.edu.Resources.entity.ConstrainedProperty;

public class ConstrainedPropertyBrief {
    private long id;
    private boolean searchable;
    private boolean required;

    public ConstrainedPropertyBrief() {
    }

    public ConstrainedPropertyBrief(ConstrainedProperty constrainedProperty) {
        id = constrainedProperty.getProperty().getId();
        searchable = constrainedProperty.isSearchable();
        required = constrainedProperty.isRequired();
    }

    public long getId() {
        return id;
    }

    public ConstrainedPropertyBrief setId(long id) {
        this.id = id;
        return this;
    }

    public boolean isSearchable() {
        return searchable;
    }

    public ConstrainedPropertyBrief setSearchable(boolean searchable) {
        this.searchable = searchable;
        return this;
    }

    public boolean isRequired() {
        return required;
    }

    public ConstrainedPropertyBrief setRequired(boolean required) {
        this.required = required;
        return this;
    }
}
