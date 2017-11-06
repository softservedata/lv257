package com.softserve.edu.Resources.dto;

import com.softserve.edu.Resources.entity.ConstrainedProperty;

public class ConstrainedPropertyBrief {
    private long id;
    private boolean searchable;
    private boolean required;
    private boolean unique;

    public ConstrainedPropertyBrief() {
    }

    public ConstrainedPropertyBrief(ConstrainedProperty constrainedProperty) {
        id = constrainedProperty.getProperty().getId();
        searchable = constrainedProperty.isSearchable();
        required = constrainedProperty.isRequired();
        unique = constrainedProperty.isUnique();
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

    public boolean isUnique() {
        return unique;
    }

    public ConstrainedPropertyBrief setUnique(boolean unique) {
        this.unique = unique;
        return this;
    }
}
