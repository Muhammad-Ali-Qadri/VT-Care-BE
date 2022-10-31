package edu.vt.cs.vtcare.vtcareservice.models;

import java.sql.Time;
import java.util.Date;

public class Appointment {
    private int id;
    private int providerId;
    private int patientId;

    private String date;
    private String time;
    private int duration;
    private boolean isVideoAppointment;
    private String url;
    private String status;

    public Appointment(int id, int providerId, int patientId,
                       String date,
                       String time, int duration, boolean isVideo, String url,
                       String status) {
        this.id = id;
        this.providerId = providerId;
        this.patientId = patientId;
        this.date = date;
        this.time = time;
        this.duration = duration;
        this.isVideoAppointment = isVideo;
        this.url = url;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProviderId() {
        return providerId;
    }

    public void setProviderId(int providerId) {
        this.providerId = providerId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean getIsVideoAppointment() {
        return isVideoAppointment;
    }

    public void setIsVideoAppointment(boolean video) {
        isVideoAppointment = video;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}