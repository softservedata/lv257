package com.softserve.edu.Resources.dto;

import com.softserve.edu.Resources.entity.ResourceRequest;

import java.util.Date;

public class ResourceRequestDTO {

    private String assignerName;
    private String resourceName;
    private Date update;

    public String getAssignerName() {
        return assignerName;
    }

    public void setAssignerName(String assignerName) {
        this.assignerName = assignerName;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
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
        this.resourceName = resourceRequest.getResourceName();
        this.update = resourceRequest.getUpdate();

    }
}
