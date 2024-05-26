package com.mobile.petkuy;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "appoinment_history")
public class AppointmentHistory {
    @ColumnInfo(name="id")
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "doctor_name")
    private String doctor_name;
    @ColumnInfo(name = "doctor_picture")
    private int doctor_picture;
    @ColumnInfo(name = "doctor_category")
    private String doctor_category;
    @ColumnInfo(name = "doctor_hospital_address")
    private String doctor_hospital_address;

    public AppointmentHistory(int id, String doctor_name, String doctor_category, String doctor_hospital_address, int doctor_picture) {
        this.id = id;
        this.doctor_name = doctor_name;
        this.doctor_category = doctor_category;
        this.doctor_hospital_address = doctor_hospital_address;
        this.doctor_picture = doctor_picture;
    }

    public int getId() {
        return id;
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public int getDoctor_picture() {
        return doctor_picture;
    }

    public String getDoctor_category() {
        return doctor_category;
    }

    public String getDoctor_hospital_address() {
        return doctor_hospital_address;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public void setDoctor_picture(int doctor_picture) {
        this.doctor_picture = doctor_picture;
    }

    public void setDoctor_category(String doctor_category) {
        this.doctor_category = doctor_category;
    }

    public void setDoctor_hospital_address(String doctor_hospital_address) {
        this.doctor_hospital_address = doctor_hospital_address;
    }
}
