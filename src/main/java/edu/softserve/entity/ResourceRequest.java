package edu.softserve.entity;


import edu.softserve.entity.Document;
import edu.softserve.entity.Status;
import edu.softserve.entity.User;

import javax.persistence.*;

import java.sql.Date;

/**
 * ResourceRequest implements request from register to resource admin to create new Resource Type
 */
@Entity
public class ResourceRequest {

    @Id
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
    @JoinColumn(name = "id_document")
    private Document document;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @Column(name = "updated", nullable = false)
    private Date update;

    @Column(name = "details")
    private String details;

    @Column(name = "notify_executor")
    private boolean notifyExecutor;


    public ResourceRequest() {

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

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public boolean isNotifyExecutorTrue() {
        return notifyExecutor == true ? true : false;
    }

    public void setNotifyExecutor(boolean notifyExecutor) {
        this.notifyExecutor = notifyExecutor;
    }

    public boolean getNotifyExecutor() {
        return notifyExecutor;
    }

}
