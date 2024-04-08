package com.safetynet.alerts.mainclasses;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
@Entity
public class MedicalRecord {
    @Id
    @GeneratedValue
    private Long id;
    public String firstName;
    public String lastName;
    public String birthdate;
    public ArrayList<String> medications;
    public ArrayList<String> allergies;

    public MedicalRecord(String firstName,
                         String lastName,
                         String birthdate,
                         ArrayList<String> medications,
                         ArrayList<String> allergies) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.medications = medications;
        this.allergies = allergies;
    }

    public MedicalRecord() {

    }

    @Override
    public String toString() {
        return "MedicalRecord{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", medications=" + medications +
                ", allergies=" + allergies +
                '}';
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public ArrayList<String> getMedications() {
        return medications;
    }

    public void setMedications(ArrayList<String> medications) {
        this.medications = medications;
    }

    public ArrayList<String> getAllergies() {
        return allergies;
    }

    public void setAllergies(ArrayList<String> allergies) {
        this.allergies = allergies;
    }
    public int getAge() {
        String dateString = getBirthdate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate date = LocalDate.parse(dateString, formatter);
        LocalDate currentDate = LocalDate.now();

        return Period.between(date, currentDate).getYears();
    }
}
