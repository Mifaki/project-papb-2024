package com.mobile.petkuy.model;


public class HospitalDetails {
    private int id;
    private String address;
    private String name;
    private String picture;

    public HospitalDetails() {
    }

    public HospitalDetails(String address, String name, String picture) {
        this.address = address;
        this.name = name;
        this.picture = picture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
