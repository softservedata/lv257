package com.softserve.edu.Resources.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.softserve.edu.Resources.entity.ResourceRequest;
import org.omg.CORBA.Request;

import java.util.Date;

public class RequestDTO {
    private long id;
    private String requesterName;
    private String assignerName;
    private String resourceName;
    private String description;
    private String resourceType;
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+3")
    private Date update;
    private ResourceRequest.Status status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRequesterName() {
        return requesterName;
    }

    public void setRequesterName(String requesterName) {
        this.requesterName = requesterName;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getUpdate() {
        return update;
    }

    public void setUpdate(Date update) {
        this.update = update;
    }

    public ResourceRequest.Status getStatus() {
        return status;
    }

    public void setStatus(ResourceRequest.Status status) {
        this.status = status;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public RequestDTO() {

    }

    public RequestDTO(ResourceRequest request) {
        this.id = request.getId();
        if (request.getRegister() != null) {
            this.requesterName = request.getRegister().getUsername();
        } else {
            this.requesterName = null;
        }
        if (request.getResourcesAdmin() != null) {
            this.assignerName = request.getResourcesAdmin().getUsername();
        } else {
            this.assignerName = null;
        }
        if (request.getResourceType() != null) {
            this.resourceType = request.getResourceType().getTypeName();
        } else {
            this.resourceType = null;
        }
        this.resourceName=request.getResourceName();
        this.description = request.getDescription();
        this.update = request.getUpdate();
        this.status = request.getStatus();
    }
}
