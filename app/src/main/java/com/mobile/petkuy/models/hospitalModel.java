package com.mobile.petkuy.models;

public class hospitalModel {
    private int id;
    private String name;
    private String address;

    // No-argument constructor
    public hospitalModel() {
    }

    // Parameterized constructor (optional)
    public hospitalModel(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    // Getters and setters
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

