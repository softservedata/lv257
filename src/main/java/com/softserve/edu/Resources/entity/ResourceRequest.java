package com.softserve.edu.Resources.entity;import com.softserve.edu.Resources.Constants;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "RESOURCE_REQUEST")

public class ResourceRequest {

    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)

    @Column(name = "id_request")
    private long id;

    @Column(name = "code", nullable = false)
    private String code;

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

    @Transient
    private MultipartFile file;

    public ResourceRequest(){
        this.code = "PRD" + UUID.randomUUID().toString().substring(26).toUpperCase();
    }

    public ResourceRequest(String resourceType, String description, User register, User resourcesAdmin,
                           Status status, Date update) {
        this.resourceType = resourceType;
        this.description = description;
        this.register = register;
        this.update = update;
        this.status = status;
        this.resourcesAdmin = resourcesAdmin;
    }

    public MultipartFile getFile() {
        return file;
    }

    public ResourceRequest setFile(MultipartFile file) {
        this.file = file;
        return this;
    }

    public String getCode() {
        return code;
    }

    public ResourceRequest setCode(String code) {
        this.code = code;
        return this;
    }

    public long getId() {
        return id;
    }

    public ResourceRequest setId(long id) {
        this.id = id;
        return this;
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
                "id_request=" + id +
                ", code='" + code + '\'' +
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
        if (o == null || getClass() != o.getClass()) return false;

        ResourceRequest request = (ResourceRequest) o;

        if (id != request.id) return false;
        if (code != null ? !code.equals(request.code) : request.code != null) return false;
        if (resourceType != null ? !resourceType.equals(request.resourceType) : request.resourceType != null)
            return false;
        if (description != null ? !description.equals(request.description) : request.description != null) return false;
        if (register.getUsername() != null ? !register.getUsername().equals(request.register.getUsername())
                : request.register.getUsername() != null) return false;
        if (resourcesAdmin.getUsername() != null ? !resourcesAdmin.getUsername().equals(request.resourcesAdmin.getUsername())
                : request.resourcesAdmin.getUsername() != null)
            return false;
        if (status != request.status) return false;
        if (update != null ? !update.equals(request.update) : request.update != null) return false;
        return file != null ? file.equals(request.file) : request.file == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (resourceType != null ? resourceType.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (register != null ? register.hashCode() : 0);
        result = 31 * result + (resourcesAdmin != null ? resourcesAdmin.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (update != null ? update.hashCode() : 0);
        result = 31 * result + (file != null ? file.hashCode() : 0);
        return result;
    }
}