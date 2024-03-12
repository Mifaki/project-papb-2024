package com.mobile.petkuy;

public class Doctor {
    private String name;
    private int profilePicture;
    private String petCategory;
    private String hospital;
    private String adresss;

    public Doctor(String name, int profilePicture, String petCategory, String hospital, String address) {
        this.name = name;
        this.profilePicture = profilePicture;
        this.petCategory = petCategory;
        this.hospital = hospital;
        this.adresss = address;
    }

    public String getName() {
        return name;
    }

    public int getProfilePicture() {
        return profilePicture;
    }

    public String getPetCategory() {
        return petCategory;
    }

    public String getHospital() {
        return hospital;
    }

    public String getAdresss() {
        return adresss;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProfilePicture(int profilePicture) {
        this.profilePicture = profilePicture;
    }

    public void setPetCategory(String petCategory) {
        this.petCategory = petCategory;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public void setAdresss(String adresss) {
        this.adresss = adresss;
    }
}
