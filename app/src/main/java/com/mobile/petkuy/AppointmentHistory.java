package com.mobile.petkuy;

public class AppointmentHistory {
    private int id;
    private String doctor_name;
    private int doctorPicture;
    private String doctor_category;
    private String doctor_hospital_address;

    public AppointmentHistory(int id, String doctorName, String petCategory, String hospital, int doctorPicture) {
        this.id = id;
        this.doctor_name = doctorName;
        this.doctor_category = petCategory;
        this.doctor_hospital_address = hospital;
        this.doctorPicture = doctorPicture;
    }

    public int getId() {
        return id;
    }

    public String getDoctorName() {
        return doctor_name;
    }

    public int getDoctorPicture() {
        return doctorPicture;
    }

    public String getPetCategory() {
        return doctor_category;
    }

    public String getHospital() {
        return doctor_hospital_address;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDoctorName(String doctorName) {
        this.doctor_name = doctorName;
    }

    public void setDoctorPicture(int doctorPicture) {
        this.doctorPicture = doctorPicture;
    }

    public void setPetCategory(String petCategory) {
        this.doctor_category = petCategory;
    }

    public void setHospital(String hospital) {
        this.doctor_hospital_address = hospital;
    }
}
