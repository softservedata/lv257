package com.softserve.edu.Resources.dto;

import com.softserve.edu.Resources.entity.ResourceRequest;

import java.util.Date;

public class ResourceRequestDTO {

    private String assignerName;
    private String resourceType;
    private Date update;

    public String getAssignerName() {
        return assignerName;
    }

    public void setAssignerName(String assignerName) {
        this.assignerName = assignerName;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public Date getUpdate() {
        return update;
    }

    public void setUpdate(Date update) {
        this.update = update;
    }

    public ResourceRequestDTO(){

    }

    public ResourceRequestDTO(ResourceRequest resourceRequest){

        if (resourceRequest.getResourcesAdmin() != null) {
            this.assignerName = resourceRequest.getResourcesAdmin().getUsername();
        } else {
            this.assignerName = null;
        }
        this.resourceType = resourceRequest.getResourceType();
        this.update = resourceRequest.getUpdate();

    }
}
