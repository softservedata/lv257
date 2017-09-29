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

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
}