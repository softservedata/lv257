package edu.softserve.entity;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;

import java.util.Date;

@Entity
@Table(name = "RequestResourceType")

public class ResourceRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_request")
    private long id_request;

    @NotBlank(message = "Please, enter new type of resource!")
    @Column(name = "theme", nullable = false)
    private String theme;

    @NotBlank(message = "Please, enter description to your request!")
    @Column(name = "details")
    private String details;

    @ManyToOne (cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_from")
    private User register;

    @ManyToOne
    @JoinColumn(name = "id_executor_user")
    private User resourcesAdmin;

    @ManyToOne
    @JoinColumn(name = "id_document")
    private Document document;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "updated")
    private Date update;

    @Column(name = "notify_executor")
    private boolean notifyExecutor;


    public ResourceRequest(){
       super();
    }

    public ResourceRequest(String theme, String details, User register, User resourcesAdmin, Document document,
                           Status status, Date update, boolean notifyExecutor) {
        this.theme = theme;
        this.details = details;
        this.register = register;
        this.document = document;
        this.update = update;
        this.status = status;
        this.notifyExecutor = notifyExecutor;
        this.resourcesAdmin = resourcesAdmin;
    }

    public long getId() {
        return id_request;
    }

    public void setId(long id_request) {
        this.id_request = id_request;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public User getRegister() {
        return this.register;
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

    public boolean isNotifyExecutorTrue() {
        return notifyExecutor == true ? true : false;
    }

    public void setNotifyExecutor(boolean notifyExecutor) {
        this.notifyExecutor = notifyExecutor;
    }

    public boolean getNotifyExecutor() {
        return notifyExecutor;
    }

    @Override
    public String toString() {
        return "ResourceRequest{" +
                "id=" + id_request +
                ", theme='" + theme + '\'' +
                ", details='" + details + '\'' +
                ", register=" + register +
                ", resourcesAdmin=" + resourcesAdmin +
                ", document=" + document +
                ", status=" + status +
                ", update=" + update +
                ", notifyExecutor=" + notifyExecutor +
                '}';
    }
}