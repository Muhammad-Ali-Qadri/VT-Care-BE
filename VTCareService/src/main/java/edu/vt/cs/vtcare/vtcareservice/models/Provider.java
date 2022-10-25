package edu.vt.cs.vtcare.vtcareservice.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

public class Provider extends User {

    private long providerId;
    private int yearsOfExperience;
    private String specialization;

    private List<Appointment> upcomingAppointments;
    private List<AppointmentSlot> availabilitySchedule;

    @JsonCreator
    public Provider(@JsonProperty("name") String name,
                    @JsonProperty("email") String email,
                    @JsonProperty("password") String password,
                    @JsonProperty("gender") String gender,
                    @JsonProperty("dob") String dob,
                    @JsonProperty("address") String address,
                    @JsonProperty("contact") String contact,
                    @JsonProperty("experience") int experience,
                    @JsonProperty("specialization") String specialization) {
        this.setName(name);
        this.setEmail(email);
        this.setPassword(password);
        this.setGender(gender);
        this.setDate(dob);
        this.setAddress(address);
        this.setContact(contact);
        this.yearsOfExperience = experience;
        this.specialization = specialization;
    }

    public long getProviderId() {
        return providerId;
    }

    public void setProviderId(long providerId) {
        this.providerId = providerId;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public List<Appointment> getUpcomingAppointments() {
        return upcomingAppointments;
    }

    public void setUpcomingAppointments(List<Appointment> upcomingAppointments) {
        this.upcomingAppointments = upcomingAppointments;
    }

    public List<AppointmentSlot> getAvailabilitySchedule() {
        return availabilitySchedule;
    }

    public void setAvailabilitySchedule(List<AppointmentSlot> availabilitySchedule) {
        this.availabilitySchedule = availabilitySchedule;
    }

    public void addAvailabilitySchedule(AppointmentSlot appointmentSlot) {
        this.availabilitySchedule.add(appointmentSlot);
    }

    public void addAppointment(Appointment appointment) {
        this.upcomingAppointments.add(appointment);
    }
}
