package com.softserve.edu.dto;

public class UserUpdateDTO {

    private int id_community;
    private int id_address;
    private String phone;
    private int bank_id;

    public UserUpdateDTO(int id_community, int id_address, String phone, int bank_id) {
        this.id_community = id_community;
        this.id_address = id_address;
        this.phone = phone;
        this.bank_id = bank_id;
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
