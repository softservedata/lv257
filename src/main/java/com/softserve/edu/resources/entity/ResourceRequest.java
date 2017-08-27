package com.softserve.edu.resources.entity;



import javax.persistence.*;

import java.sql.Date;

/**
 * ResourceRequest implements request from register to resource admin to create new Resource Type
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

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @Column(name = "updated", nullable = false)
    private Date update;

    @Column(name = "message")
    private String message;

    @Column(name = "notify_executor")
    private boolean notifyExecutor;


    public ResourceRequest(){

    }

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

    public ResourceRequest(String theme, User register, User resourcesAdmin, Document document,
                           Status status, Date update, String message, boolean notifyExecutor) {
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
