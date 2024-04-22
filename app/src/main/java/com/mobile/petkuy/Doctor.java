package com.mobile.petkuy;

public class Doctor {
    private int id;
    private String name;
    private int profilePicture;
    private String category;
    private String hospital_name;
    private String hospital_address;

    public Doctor(int id, String name, int profilePicture, String petCategory, String hospital, String address) {
        this.id = id;
        this.name = name;
        this.profilePicture = profilePicture;
        this.category = petCategory;
        this.hospital_name = hospital;
        this.hospital_address = address;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getProfilePicture() {
        return profilePicture;
    }

    public String getPetCategory() {
        return category;
    }

    public String getHospital() {
        return hospital_name;
    }

    public String getAdresss() {
        return hospital_address;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProfilePicture(int profilePicture) {
        this.profilePicture = profilePicture;
    }

    public void setPetCategory(String petCategory) {
        this.category = petCategory;
    }

    public void setHospital(String hospital) {
        this.hospital_name = hospital;
    }

    public void setAdresss(String adresss) {
        this.hospital_address = adresss;
    }
}
