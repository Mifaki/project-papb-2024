package com.mobile.petkuy.model;

public class Doctor {
    private int hospital_id;
    private int id;
    private String name;
    private String picture;
    private int price;
    private String specialities;
    private int imageResourceId;

    public Doctor() {
    }

    public Doctor(int hospital_id, int id, String name, String picture, int price, String specialities, int imageResourceId) {
        this.hospital_id = hospital_id;
        this.id = id;
        this.name = name;
        this.picture = picture;
        this.price = price;
        this.specialities = specialities;
        this.imageResourceId = imageResourceId;
    }

    public int getHospital_id() {
        return hospital_id;
    }

    public void setHospital_id(int hospital_id) {
        this.hospital_id = hospital_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getSpecialities() {
        return specialities;
    }

    public void setSpecialities(String specialities) {
        this.specialities = specialities;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }

    @Override
    public String toString() {
        return "Doctor ID: " + id + "\nName: " + name + "\nSpecialities: " + specialities;
    }
}
