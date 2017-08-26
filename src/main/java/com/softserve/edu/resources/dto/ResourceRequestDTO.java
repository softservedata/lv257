package com.softserve.edu.resources.dto;


import com.softserve.edu.resources.daoImpl.DocumentDAOimpl;
import com.softserve.edu.resources.entity.ResourceRequest;
import com.softserve.edu.resources.entity.User;

import java.sql.Date;
import java.util.List;

/**
 * Created by User on 16.08.2017.
 */
public class ResourceRequestDTO {

    private long id;
    private String requestedCategory;
    private User register;
    private User resourcesAdmin;
    private List<String> documentLinks;
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

    public void setDocumentLinks(List<String> documentLinks) {
        this.documentLinks = documentLinks;
    }

    public List<String> getDocumentLinks() {
        return documentLinks;
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
        this.documentLinks =new DocumentDAOimpl().getLinks();
    }
}
