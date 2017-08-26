package com.softserve.edu.resources.entity;



import com.softserve.edu.resources.daoImpl.DocumentDAOimpl;
import com.softserve.edu.resources.dto.ResourceRequestDTO;

import javax.persistence.*;

import java.sql.Date;

/**
 * Created by User on 16.08.2017.
 */
@Entity
@Table(name = "RequestResourceType")
public class ResourceRequest {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "theme", nullable = false)
    private String theme;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_from", nullable = false)
    private User register;

    @ManyToOne
    @JoinColumn(name = "id_executor_user")
    private User resourcesAdmin;

    @ManyToOne
    @JoinColumn(name = "id")
    private Document document;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "updated", nullable = false)
    private Date update;

    @Column(name = "message")
    private String message;

    @Column(name = "notify_executor")
    private boolean notifyExecutor;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
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

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getUpdate() {
        return update;
    }

    public void setUpdate(Date update) {
        this.update = update;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public ResourceRequest(ResourceRequestDTO requestDTO) {
        this.id = requestDTO.getId();
        this.register = requestDTO.getRegister();
        this.resourcesAdmin = requestDTO.getResourcesAdmin();
        this.message = requestDTO.getMessage();
        this.theme = requestDTO.getRequestedCategory();
        this.status = requestDTO.getStatus().toString();
        this.update = requestDTO.getUpdate();
        this.notifyExecutor = requestDTO.getNotifyExecutor();
        this.document = new DocumentDAOimpl().getDocumentByLinks(requestDTO.getDocumentLinks());
    }

    public ResourceRequest(long id, String theme, User register, User resourcesAdmin, Document document,
                           String status, Date update, String message, boolean notifyExecutor) {
        this.id = id;
        this.theme = theme;
        this.register = register;
        this.resourcesAdmin = resourcesAdmin;
        this.document = document;
        this.status = status;
        this.update = update;
        this.message = message;
        this.notifyExecutor = notifyExecutor;
    }
}
