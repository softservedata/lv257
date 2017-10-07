package com.softserve.edu.Resources.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.softserve.edu.Resources.Constants;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

public class UserProfileDTO {

    @Id
    @GeneratedValue(generator = Constants.ID_GENERATOR)

    private Long id;
    private String firstName;
    private String secondName;
    private String middleName;
    private String passportSeries;
    private String passportNumber;
    private String issuedBy;
    private Date dateOfIssue;
    private String idAddress;
    private String phone;
    private String bankId;
    private Long user;

    public UserProfileDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void  setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPassportSeries() {
        return passportSeries;
    }

    public void setPassportSeries(String passportSeries) {
        this.passportSeries = passportSeries;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getIssuedBy() {
        return issuedBy;
    }

    public void setIssuedBy(String issuedBy) {
        this.issuedBy = issuedBy;
    }

    public Date getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(Date dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public String getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(String idAddress) {
        this.idAddress = idAddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserProfileDTO that = (UserProfileDTO) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (secondName != null ? !secondName.equals(that.secondName) : that.secondName != null) return false;
        if (middleName != null ? !middleName.equals(that.middleName) : that.middleName != null) return false;
        if (passportSeries != null ? !passportSeries.equals(that.passportSeries) : that.passportSeries != null)
            return false;
        if (passportNumber != null ? !passportNumber.equals(that.passportNumber) : that.passportNumber != null)
            return false;
        if (issuedBy != null ? !issuedBy.equals(that.issuedBy) : that.issuedBy != null) return false;
        if (dateOfIssue != null ? !dateOfIssue.equals(that.dateOfIssue) : that.dateOfIssue != null) return false;
        if (idAddress != null ? !idAddress.equals(that.idAddress) : that.idAddress != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (bankId != null ? !bankId.equals(that.bankId) : that.bankId != null) return false;
        return user != null ? user.equals(that.user) : that.user == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (secondName != null ? secondName.hashCode() : 0);
        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
        result = 31 * result + (passportSeries != null ? passportSeries.hashCode() : 0);
        result = 31 * result + (passportNumber != null ? passportNumber.hashCode() : 0);
        result = 31 * result + (issuedBy != null ? issuedBy.hashCode() : 0);
        result = 31 * result + (dateOfIssue != null ? dateOfIssue.hashCode() : 0);
        result = 31 * result + (idAddress != null ? idAddress.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (bankId != null ? bankId.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserProfileDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", passportSeries='" + passportSeries + '\'' +
                ", passportNumber='" + passportNumber + '\'' +
                ", issuedBy='" + issuedBy + '\'' +
                ", dateOfIssue=" + dateOfIssue +
                ", idAddress='" + idAddress + '\'' +
                ", phone='" + phone + '\'' +
                ", bankId='" + bankId + '\'' +
                ", user=" + user +
                '}';
    }
}
