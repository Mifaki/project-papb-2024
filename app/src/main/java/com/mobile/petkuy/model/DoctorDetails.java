package com.mobile.petkuy.model;

import androidx.room.Ignore;

public class DoctorDetails {
    private int id;
    private double price;
    private String name;
    private int hospital_id;
    private String picture;
    private String specialities;
    private HospitalDetails hospitalDetails;

    public DoctorDetails() {
    }

    @Ignore
    public DoctorDetails(double price, String name, int hospitalId, String picture, String specialities) {
        this.price = price;
        this.name = name;
        this.hospital_id = hospitalId;
        this.picture = picture;
        this.specialities = specialities;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHospital_id() {
        return hospital_id;
    }

    public void setHospitalId(int hospital_id) {
        this.hospital_id = hospital_id;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getSpecialities() {
        return specialities;
    }

    public void setSpecialities(String specialities) {
        this.specialities = specialities;
    }

    public HospitalDetails getHospitalDetails() {
        return hospitalDetails;
    }

    public void setHospitalDetails(HospitalDetails hospitalDetails) {
        this.hospitalDetails = hospitalDetails;
    }

}