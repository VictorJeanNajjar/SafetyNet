package com.safetynet.alerts.threemainclasses;

import java.util.ArrayList;

public class AllData {
    public ArrayList<Person> persons;
    public ArrayList<FireStation> firestations;
    public ArrayList<MedicalRecord> medicalrecords;

    public AllData(ArrayList<Person> persons,
                   ArrayList<FireStation> firestations,
                   ArrayList<MedicalRecord> medicalrecords) {
        this.persons = persons;
        this.firestations = firestations;
        this.medicalrecords = medicalrecords;
    }

    @Override
    public String toString() {
        return "AllData{" +
                "persons=" + persons +
                ", firestations=" + firestations +
                ", medicalrecords=" + medicalrecords +
                '}';
    }

    public ArrayList<Person> getPersons() {
        return persons;
    }

    public void setPersons(ArrayList<Person> persons) {
        this.persons = persons;
    }

    public ArrayList<FireStation> getFirestations() {
        return firestations;
    }

    public void setFirestations(ArrayList<FireStation> firestations) {
        this.firestations = firestations;
    }

    public ArrayList<MedicalRecord> getMedicalrecords() {
        return medicalrecords;
    }

    public void setMedicalrecords(ArrayList<MedicalRecord> medicalrecords) {
        this.medicalrecords = medicalrecords;
    }
}
