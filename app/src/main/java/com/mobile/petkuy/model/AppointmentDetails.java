package com.mobile.petkuy.model;

public class AppointmentDetails {
    private int id;
    private String appointment_date;
    private int doctors_id;
    private int method_id;
    private boolean payment_status;
    private int user_id;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAppointment_date() {
        return appointment_date;
    }

    public void setAppointment_date(String appointment_date) {
        this.appointment_date = appointment_date;
    }

    public int getDoctors_id() {
        return doctors_id;
    }

    public void setDoctors_id(int doctors_id) {
        this.doctors_id = doctors_id;
    }

    public int getMethod_id() {
        return method_id;
    }

    public void setMethod_id(int method_id) {
        this.method_id = method_id;
    }

    public boolean isPayment_status() {
        return payment_status;
    }

    public void setPayment_status(boolean payment_status) {
        this.payment_status = payment_status;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "AppointmentDetails{" +
                "appointmentDate='" + appointment_date + '\'' +
                ", doctorsId=" + doctors_id +
                ", methodId=" + method_id +
                ", paymentStatus=" + payment_status +
                ", userId=" + user_id +
                '}';
    }
}