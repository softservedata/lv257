package com.softserve.edu.Resources.dto;

public class ConstrainedPropertyBrief {
    private long id;
    private boolean searchable;
    private boolean required;

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isSearchable() {
        return searchable;
    }

    public void setSearchable(boolean searchable) {
        this.searchable = searchable;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }
}
