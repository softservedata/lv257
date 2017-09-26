package com.softserve.edu.Resources.dto;

public class SelectInfoDTO {

    private long objectId;
    private String message;

    public SelectInfoDTO() {
    }

    public long getObjectId() {
        return objectId;
    }

    public void setObjectId(long objectId) {
        this.objectId = objectId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
