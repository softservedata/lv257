package com.softserve.edu.Resources.dto;

public class SelectInfoDTO {

    private long objectId;
    private String message;

    public SelectInfoDTO() {
    }

    public SelectInfoDTO(long objectId, String message) {
        this.objectId = objectId;
        this.message = message;
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

    @Override
    public String toString() {
        return "SelectInfoDTO{" +
                "objectId=" + objectId +
                ", message='" + message + '\'' +
                '}';
    }
}
