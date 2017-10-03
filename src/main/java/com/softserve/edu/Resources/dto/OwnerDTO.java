package com.softserve.edu.Resources.dto;

public class OwnerDTO {

    private long ownerId;
    private String ownerType;
    private String personalInfo;
    private String phone;
    private String addressInfo;

    public OwnerDTO() {
    }

    public long getOwnerId() {
        return ownerId;
    }

    public OwnerDTO setOwnerId(long ownerId) {
        this.ownerId = ownerId;
        return this;
    }

    public String getOwnerType() {
        return ownerType;
    }

    public OwnerDTO setOwnerType(String ownerType) {
        this.ownerType = ownerType;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public OwnerDTO setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getAddressInfo() {
        return addressInfo;
    }

    public OwnerDTO setAddressInfo(String addressInfo) {
        this.addressInfo = addressInfo;
        return this;
    }

    public String getPersonalInfo() {
        return personalInfo;
    }

    public OwnerDTO setPersonalInfo(String personalInfo) {
        this.personalInfo = personalInfo;
        return this;
    }
}
