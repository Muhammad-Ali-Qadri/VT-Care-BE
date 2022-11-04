package edu.vt.cs.vtcare.vtcareservice.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.Locale;

public class Appointment {
    private long id;
    private int providerId;
    private int patientId;
    private int duration;

    private boolean isVideoAppointment;

    private String providerName;
    private String providerEmail;
    private String patientName;
    private String patientEmail;
    private Date date;
    private String time;
    private String url;

    private AppointmentStatus status;

    @JsonCreator
    public Appointment(@JsonProperty("providerId") int providerId,
                       @JsonProperty("patientId") int patientId,
                       @JsonProperty("duration") int duration,
                       @JsonProperty("isVideoAppointment") boolean isVideoAppointment,
                       @JsonProperty("providerName") String providerName,
                       @JsonProperty("providerEmail") String providerEmail,
                       @JsonProperty("patientName") String patientName,
                       @JsonProperty("patientEmail") String patientEmail,
                       @JsonProperty("date") String date,
                       @JsonProperty("time") String time,
                       @JsonProperty("url") String url,
                       @JsonProperty("status") AppointmentStatus status) {
        this.providerId = providerId;
        this.patientId = patientId;
        this.duration = duration;
        this.isVideoAppointment = isVideoAppointment;
        this.providerName = providerName;
        this.providerEmail = providerEmail;
        this.patientName = patientName;
        this.patientEmail = patientEmail;
        this.time = time;
        this.url = url;
        this.status = status;

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        try{
            this.date = formatter.parse(date);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public Appointment(long id, int providerId, int patientId, int duration,
                       boolean isVideoAppointment, String providerName,
                       String providerEmail, String patientName,
                       String patientEmail, Date date, String time,
                       String url, AppointmentStatus status) {
        this.id = id;
        this.providerId = providerId;
        this.patientId = patientId;
        this.duration = duration;
        this.isVideoAppointment = isVideoAppointment;
        this.providerName = providerName;
        this.providerEmail = providerEmail;
        this.patientName = patientName;
        this.patientEmail = patientEmail;
        this.date = date;
        this.time = time;
        this.url = url;
        this.status = status;
    }

    public String getDateTimeString(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date) + "T" + time + "Z";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean isVideoAppointment() {
        return isVideoAppointment;
    }

    public void setVideoAppointment(boolean videoAppointment) {
        isVideoAppointment = videoAppointment;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getProviderEmail() {
        return providerEmail;
    }

    public void setProviderEmail(String providerEmail) {
        this.providerEmail = providerEmail;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientEmail() {
        return patientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }
}
