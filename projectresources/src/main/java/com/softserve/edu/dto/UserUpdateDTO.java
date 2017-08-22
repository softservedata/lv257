package com.softserve.edu.dto;

public class UserUpdateDTO {

    private int id_community;
    private int id_address;
    private String first_name;
    private String second_name;
    private String middle_name;
    private String passport_series;
    private String passport_number;
    private String issued_by;
    private String date_of_issue;
    private String phone;
    private int bank_id;

    public UserUpdateDTO(int id_community, int id_address, String first_name, String second_name, String middle_name, String passport_series, String passport_number, String issued_by, String date_of_issue, String phone, int bank_id) {
        this.id_community = id_community;
        this.id_address = id_address;
        this.first_name = first_name;
        this.second_name = second_name;
        this.middle_name = middle_name;
        this.passport_series = passport_series;
        this.passport_number = passport_number;
        this.issued_by = issued_by;
        this.date_of_issue = date_of_issue;
        this.phone = phone;
        this.bank_id = bank_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getSecond_name() {
        return second_name;
    }

    public void setSecond_name(String second_name) {
        this.second_name = second_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public String getPassport_series() {
        return passport_series;
    }

    public void setPassport_series(String passport_series) {
        this.passport_series = passport_series;
    }

    public String getPassport_number() {
        return passport_number;
    }

    public void setPassport_number(String passport_number) {
        this.passport_number = passport_number;
    }

    public String getIssued_by() {
        return issued_by;
    }

    public void setIssued_by(String issued_by) {
        this.issued_by = issued_by;
    }

    public String getDate_of_issue() {
        return date_of_issue;
    }

    public void setDate_of_issue(String date_of_issue) {
        this.date_of_issue = date_of_issue;
    }

    public int getId_community() {
        return id_community;
    }

    public void setId_community(int id_community) {
        this.id_community = id_community;
    }

    public int getId_address() {
        return id_address;
    }

    public void setId_address(int id_address) {
        this.id_address = id_address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getBank_id() {
        return bank_id;
    }

    public void setBank_id(int bank_id) {
        this.bank_id = bank_id;
    }
}
