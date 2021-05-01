package com.example.patient;

import java.time.LocalDate;

public class PatientItem {
    private String name; 
    private boolean active;
    private String address;
    private String phone;
    private String dateOfBirth;
    private String closestRelative;
    private boolean gender;
    private String generalPractitioner;
    private String preflang;

    public PatientItem() {
    }

    public PatientItem(String name, boolean active) {
        this.name = name;
        this.active = active;
    }

    public PatientItem(String name, boolean active, String address, String phone, String dateOfBirth, String closestRelative, boolean gender, String generalPractitioner, String preflang) {
        this.name = name;
        this.active = active;
        this.address = address;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
        this.closestRelative = closestRelative;
        this.gender = gender;
        this.generalPractitioner = generalPractitioner;
        this.preflang = preflang;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }


    public String getClosestRelative() {
        return closestRelative;
    }

    public void setClosestRelative(String closestRelative) {
        this.closestRelative = closestRelative;
    }

    public boolean getGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getGeneralPractitioner() {
        return generalPractitioner;
    }

    public void setGeneralPractitioner(String generalPractitioner) {
        this.generalPractitioner = generalPractitioner;
    }

    public String getPreflang() {
        return preflang;
    }

    public void setPreflang(String preflang) {
        this.preflang = preflang;
    }
}


