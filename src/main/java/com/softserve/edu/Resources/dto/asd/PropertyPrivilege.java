package com.softserve.edu.Resources.dto.asd;

public class PropertyPrivilege {

    private String name;
    private boolean read;
    private boolean update;

    public PropertyPrivilege() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }
}
