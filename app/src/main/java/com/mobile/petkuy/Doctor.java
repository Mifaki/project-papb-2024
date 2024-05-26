package com.mobile.petkuy;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "doctor")
public class Doctor {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "profile_picture")
    private int profile_picture;
    @ColumnInfo(name = "category")
    private String category;
    @ColumnInfo(name = "hospital_name")
    private String hospital_name;
    @ColumnInfo(name = "hospital_address")
    private String hospital_address;

    public Doctor(int id, String name, int profile_picture, String category, String hospital_name, String hospital_address) {
        this.id = id;
        this.name = name;
        this.profile_picture = profile_picture;
        this.category = category;
        this.hospital_name = hospital_name;
        this.hospital_address = hospital_address;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getProfile_picture() {
        return profile_picture;
    }

    public String getCategory() {
        return category;
    }

    public String getHospital_name() {
        return hospital_name;
    }

    public String getHospital_address() {
        return hospital_address;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProfile_picture(int profile_picture) {
        this.profile_picture = profile_picture;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setHospital_name(String hospital_name) {
        this.hospital_name = hospital_name;
    }

    public void setHospital_address(String hospital_address) {
        this.hospital_address = hospital_address;
    }
}
