package edu.vt.cs.vtcare.vtcareservice.models;

import java.sql.Date;

public class User {
    private String name;
    private String email;
    private String password;
    private String gender;
    private String dateOfBirth;
    private String address;
    private String contact;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String date) {
        this.dateOfBirth = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    /*public boolean register() {

    }

    public boolean login() {

    }

    public boolean updateProfile() {

    }

    public boolean deleteProfile() {

    }

    public boolean logout() {

    }*/
}
