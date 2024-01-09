package com.siddhantsawant.healthcareapp;

public class Appointment {
    private int id;
    private String doctorName;
    private String dateTime;

    public Appointment() {
        // Default constructor
    }

    public Appointment(String doctorName, String dateTime) {
        this.doctorName = doctorName;
        this.dateTime = dateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
