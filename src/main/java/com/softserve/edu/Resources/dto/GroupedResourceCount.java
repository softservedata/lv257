package com.softserve.edu.Resources.dto;

public class GroupedResourceCount {

    private String resourceTypeName;
    private long resourceRecordsCount;

    public GroupedResourceCount(String resourceTypeName, long resourceRecordsCount) {
        this.resourceTypeName = resourceTypeName;
        this.resourceRecordsCount = resourceRecordsCount;
    }

    public String getResourceTypeName() {
        return resourceTypeName;
    }

    public void setResourceTypeName(String resourceTypeName) {
        this.resourceTypeName = resourceTypeName;
    }

    public long getResourceRecordsCount() {
        return resourceRecordsCount;
    }

    public void setResourceRecordsCount(long resourceRecordsCount) {
        this.resourceRecordsCount = resourceRecordsCount;
    }
    
    
    
}
