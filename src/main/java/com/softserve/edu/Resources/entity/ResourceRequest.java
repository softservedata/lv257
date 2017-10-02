package com.softserve.edu.Resources.entity;
import com.softserve.edu.Resources.Constants;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;

import java.util.Date;

@Entity
@Table(name = "RESOURCE_REQUEST")

public class ResourceRequest {

    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)

    @Column(name = "id_request")
    private long id;

    @OneToOne (cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_document")
    private Document document;

    @Column(name = "resourceType", nullable = false)
    @NotBlank(message = "Please, enter new type of resource!")
    private String resourceType;

    @Column(name = "description")
    @NotBlank(message = "Please, enter description to your request!")
    private String description;

    @ManyToOne (cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_requester")
    private User register;

    @ManyToOne
    @JoinColumn(name = "id_assigner")
    private User resourcesAdmin;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "updated")
    private Date update;

    public enum Status {
        NEW, ACCEPTED, DECLINED, TO_REFINEMENT;
    }

    public ResourceRequest(){

    }

    public ResourceRequest(String resourceType, String description, User register, User resourcesAdmin,
                           Status status, Date update, Document document) {
        this.resourceType = resourceType;
        this.description = description;
        this.register = register;
        this.update = update;
        this.status = status;
        this.resourcesAdmin = resourcesAdmin;
        this.document = document;

    }

    public long getId() {
        return id;
    }

    public ResourceRequest setId(long id) {
        this.id = id;
        return this;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public String getResourceType() {
        return resourceType;
    }

    public ResourceRequest setResourceType(String resourceType) {
        this.resourceType = resourceType;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ResourceRequest setDescription(String description) {
        this.description = description;
        return this;
    }

    public User getRegister() {
        return this.register;
    }

    public ResourceRequest setRegister(User register) {
        this.register = register;
        return this;
    }

    public User getResourcesAdmin() {
        return resourcesAdmin;
    }

    public ResourceRequest setResourcesAdmin(User resourcesAdmin) {
        this.resourcesAdmin = resourcesAdmin;
        return this;
    }

    public Status getStatus() {
        return status;
    }

    public ResourceRequest setStatus(Status status) {
        this.status = status;
        return this;
    }

    public Date getUpdate() {
        return update;
    }

    public ResourceRequest setUpdate(Date update) {
        this.update = update;
        return this;
    }

    @Override
    public String toString() {
        return "ResourceRequest{" +
                "id=" + id +
                ", document=" + document +
                ", resourceType='" + resourceType + '\'' +
                ", description='" + description + '\'' +
                ", register=" + register +
                ", resourcesAdmin=" + resourcesAdmin +
                ", status=" + status +
                ", update=" + update +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResourceRequest)) return false;

        ResourceRequest that = (ResourceRequest) o;

        if (id != that.id) return false;
        if (!document.equals(that.document)) return false;
        if (!resourceType.equals(that.resourceType)) return false;
        if (!description.equals(that.description)) return false;
        if (!register.equals(that.register)) return false;
        if (!resourcesAdmin.equals(that.resourcesAdmin)) return false;
        if (status != that.status) return false;
        return update.equals(that.update);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + document.hashCode();
        result = 31 * result + resourceType.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + register.hashCode();
        result = 31 * result + resourcesAdmin.hashCode();
        result = 31 * result + status.hashCode();
        result = 31 * result + update.hashCode();
        return result;
    }
}