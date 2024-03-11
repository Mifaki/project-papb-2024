package com.mobile.petkuy;

public class AppointmentHistory {
    private String doctorName;
    private int doctorPicture;
    private String petCategory;
    private String hospital;

    public AppointmentHistory(String doctorName, int doctorPicture, String petCategory, String hospital) {
        this.doctorName = doctorName;
        this.petCategory = petCategory;
        this.hospital = hospital;
        this.doctorPicture = doctorPicture;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public int getDoctorPicture() {
        return doctorPicture;
    }

    public String getPetCategory() {
        return petCategory;
    }

    public String getHospital() {
        return hospital;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public void setDoctorPicture(int doctorPicture) {
        this.doctorPicture = doctorPicture;
    }

    public void setPetCategory(String petCategory) {
        this.petCategory = petCategory;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }
}
