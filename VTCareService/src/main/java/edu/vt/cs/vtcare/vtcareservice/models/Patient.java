package edu.vt.cs.vtcare.vtcareservice.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Encapsulates the patient entity. Adds more relevant properties and entities.
 */
public class Patient extends User {
    private long id;
    private String employer;
    private String designation;
    private Insurance insurance;
    private List<Appointment> upcomingAppointments;
    private List<PatientVisitHistory> history;

    @JsonCreator
    public Patient(@JsonProperty("name") String name,
                    @JsonProperty("email") String email,
                    @JsonProperty("password") String password,
                    @JsonProperty("gender") String gender,
                    @JsonProperty("dob") String dob,
                    @JsonProperty("address") String address,
                    @JsonProperty("contact") String contact,
                    @JsonProperty("employer") String employer,
                    @JsonProperty("designation") String designation,
                    @JsonProperty("insurancePolicyNo") long policyNo,
                    @JsonProperty("insuranceNetwId") long networkId) {
        this.setName(name);
        this.setEmail(email);
        this.setPassword(password);
        this.setGender(gender);
        this.setDate(dob);
        this.setAddress(address);
        this.setContact(contact);
        this.employer = employer;
        this.designation = designation;
        this.insurance = new Insurance(policyNo, networkId);
    }

    /**
     *
     * @param id
     * @param name
     * @param email
     * @param password
     * @param gender
     * @param dob
     * @param address
     * @param contact
     * @param employer
     * @param designation
     * @param insurance
     */
    public Patient(long id,
                   String name,
                   String email,
                   String password,
                   String gender,
                   String dob,
                   String address,
                   String contact,String employer, String designation, Insurance insurance) {
        this.setName(name);
        this.setEmail(email);
        this.setPassword(password);
        this.setGender(gender);
        this.setDate(dob);
        this.setAddress(address);
        this.setContact(contact);
        this.id = id;
        this.employer = employer;
        this.designation = designation;
        this.insurance = insurance;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Insurance getInsurance() {
        return insurance;
    }

    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
    }

    public List<Appointment> getUpcomingAppointments() {
        return upcomingAppointments;
    }

    public void setUpcomingAppointments(List<Appointment> upcomingAppointments) {
        this.upcomingAppointments = upcomingAppointments;
    }

    public List<PatientVisitHistory> getHistory() {
        return history;
    }

    public void setHistory(List<PatientVisitHistory> history) {
        this.history = history;
    }
}
