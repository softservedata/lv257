package com.softserve.edu.Resources.entity;import com.softserve.edu.Resources.Constants;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.net.URL;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Represents legal document issued by authorities
 */

@Entity
public class Document {
    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)

    private long id;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "extension", nullable = false)
    private String fileExtension;

    @Transient
    private MultipartFile file;

    public Document() {
        this.code = "PRD" + UUID.randomUUID().toString().substring(26).toUpperCase();

    }

    public long getId() {
        return id;
    }

    public Document setId(long id) {
        this.id = id;
        return this;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
