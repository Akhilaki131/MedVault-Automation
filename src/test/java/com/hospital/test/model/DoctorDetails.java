package com.hospital.test.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DoctorDetails {
    
    private String username;
    
    private String firstName;
    
    private String lastName;
    
    private String Speciality;

    public DoctorDetails(String username, String firstName, String lastName,
                         String speciality) {
        super();
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        Speciality = speciality;
    }

    public DoctorDetails() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSpeciality() {
        return Speciality;
    }

    public void setSpeciality(String speciality) {
        Speciality = speciality;
    }

}
