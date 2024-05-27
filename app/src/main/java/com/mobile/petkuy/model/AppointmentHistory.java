package com.mobile.petkuy.model;

import com.mobile.petkuy.model.AppointmentDetails;
import com.mobile.petkuy.model.DoctorDetails;
import com.mobile.petkuy.model.HospitalDetails;

public class AppointmentHistory {
    private int id;
    private int appointment_id;
    private AppointmentDetails appointmentDetails;
    private DoctorDetails doctorDetails;
    private HospitalDetails hospitalDetails;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAppointment_id() {
        return appointment_id;
    }

    public void setAppointment_id(int appointment_id) {
        this.appointment_id = appointment_id;
    }

    public AppointmentDetails getAppointmentDetails() {
        return appointmentDetails;
    }

    public void setAppointmentDetails(AppointmentDetails appointmentDetails) {
        this.appointmentDetails = appointmentDetails;
    }

    public DoctorDetails getDoctorDetails() {
        return doctorDetails;
    }

    public void setDoctorDetails(DoctorDetails doctorDetails) {
        this.doctorDetails = doctorDetails;
    }

    public HospitalDetails getHospitalDetails() {
        return hospitalDetails;
    }

    public void setHospitalDetails(HospitalDetails hospitalDetails) {
        this.hospitalDetails = hospitalDetails;
    }

    @Override
    public String toString() {
        return "AppointmentHistory{" +
                "id=" + id +
                ", appointmentDetails=" + appointmentDetails +
                ", doctorDetails=" + doctorDetails +
                ", hospitalDetails=" + hospitalDetails +
                '}';
    }
}