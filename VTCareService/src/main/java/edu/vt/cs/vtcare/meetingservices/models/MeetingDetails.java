package edu.vt.cs.vtcare.meetingservices.models;

public class MeetingDetails {
    private String agenda;
    private int duration;
    private String password;
    private String providerName;
    private String providerEmail;
    private String patientName;
    private String patientEmail;
    private String scheduleDate;
    private String scheduleTime;

    public MeetingDetails(String agenda, int duration, String password,
                          String providerName, String providerEmail,
                          String patientName, String patientEmail, String scheduleDate,
                          String scheduleTime) {
        this.agenda = agenda;
        this.duration = duration;
        this.password = password;
        this.providerName = providerName;
        this.providerEmail = providerEmail;
        this.patientName = patientName;
        this.patientEmail = patientEmail;
        this.scheduleDate = scheduleDate;
        this.scheduleTime = scheduleTime;
    }

    public String getAgenda() {
        return agenda;
    }

    public void setAgenda(String agenda) {
        this.agenda = agenda;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(String scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public String getScheduleTime() {
        return scheduleTime;
    }

    public void setScheduleTime(String scheduleTime) {
        this.scheduleTime = scheduleTime;
    }
}
