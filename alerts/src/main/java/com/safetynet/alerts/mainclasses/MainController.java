package com.safetynet.alerts.mainclasses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

import static com.safetynet.alerts.AlertsApplication.myData;

@RestController
public class MainController {
    public ArrayList<MedicalRecord> medicalRecords;
    public ArrayList<Person> person;
    public ArrayList<FireStation> fireStation;
    @Autowired
    public MainController(ArrayList<MedicalRecord> medicalRecords,
                          ArrayList<Person> person,
                          ArrayList<FireStation> fireStation) {
        this.medicalRecords = medicalRecords;
        this.person = person;
        this.fireStation = fireStation;
    }
    @GetMapping("/medicalRecord")
    public ArrayList<MedicalRecord> getMedicalRecordsController() {
        medicalRecords = myData.getMedicalRecords();
        return medicalRecords;
    }
    @GetMapping("/person")
    public ArrayList<Person> getPersonsController() {
        person = myData.getPersons();
        return person;
        }
    @GetMapping("/firestation")
    public ArrayList<FireStation> getFireStationController() {
        fireStation = myData.getFirestations();
        return fireStation;
    }
    @GetMapping("/alldatafortesting")
    public AllData GetAllInformation(){
        return myData;
    }
}
