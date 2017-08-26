package com.softserve.edu.resources.dto;


import com.softserve.edu.resources.entity.ResourceRequest;
import com.softserve.edu.resources.entity.User;

import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.Set;

/**
 * Represent request to creating new Category(Resource Type)
 */
public class ResourceRequestDTO {

    private long id;
    private String requestedCategory;
    private User register;
    private User resourcesAdmin;
    private Set<URL> documentLinks;
    private String message;
    private Status status;
    private Date update;
    private boolean notifyExecutor;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<URL> getDocumentLinks() {
        return documentLinks;
    }

    public void setDocumentLinks(Set<URL> documentLinks) {
        this.documentLinks = documentLinks;
    }

    public String getRequestedCategory() {
        return requestedCategory;
    }

    public void setRequestedCategory(String requestedCategory) {
        this.requestedCategory = requestedCategory;
    }

    public User getRegister() {
        return register;
    }

    public void setRegister(User register) {
        this.register = register;
    }

    public User getResourcesAdmin() {
        return resourcesAdmin;
    }

    public void setResourcesAdmin(User resourcesAdmin) {
        this.resourcesAdmin = resourcesAdmin;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getUpdate() {
        return update;
    }

    public void setUpdate(Date update) {
        this.update = update;
    }

    public boolean isNotifyExecutorTrue() {
        return notifyExecutor == true ? true : false;
    }

    public boolean isNotifyExecutorFalse() {
        return notifyExecutor == false ? true : false;
    }

    public void setNotifyExecutor(boolean notifyExecutor) {
        this.notifyExecutor = notifyExecutor;
    }

    public boolean getNotifyExecutor() {
        return notifyExecutor;
    }

    public ResourceRequestDTO(ResourceRequest request) {
        this.id = request.getId();
        this.register = request.getRegister();
        this.resourcesAdmin = request.getResourcesAdmin();
        this.message = request.getMessage();
        this.requestedCategory = request.getTheme();
        this.status = Status.getStatus(request.getStatus());
        this.update = request.getUpdate();
        this.notifyExecutor = request.getNotifyExecutor();
        this.documentLinks =request.getDocument().getCopies();
    }
}
