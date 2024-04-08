package com.safetynet.alerts.mainclasses;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

public class AllData {
    public ArrayList<Person> persons;
    public List<Firestation> firestations;
    public ArrayList<MedicalRecord> medicalrecords;
    public AllData(ArrayList<Person> persons,
                   List<Firestation> firestations,
                   ArrayList<MedicalRecord> medicalrecords) {
        this.persons = persons;
        this.firestations = firestations;
        this.medicalrecords = medicalrecords;
    }

    @Override
    public String toString() {
        return "AllData{" +
                "persons=" + persons +
                ", fire stations=" + firestations +
                ", medical records=" + medicalrecords +
                '}';
    }

    public ArrayList<Person> getPersons() {
        return persons;
    }

    public void setPersons(ArrayList<Person> persons) {
        this.persons = persons;
    }

    public List<Firestation> getFirestations() {
        return firestations;
    }

    public void setFirestations(ArrayList<Firestation> firestations) {
        this.firestations = firestations;
    }

    public ArrayList<MedicalRecord> getMedicalRecords() {return medicalrecords;}

    public void setMedicalrecords(ArrayList<MedicalRecord> medicalrecords) {
        this.medicalrecords = medicalrecords;
    }
}
