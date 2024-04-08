package com.safetynet.alerts.datasortingandwriting;

import com.safetynet.alerts.mainclasses.Firestation;
import com.safetynet.alerts.mainclasses.MedicalRecord;
import com.safetynet.alerts.mainclasses.Person;
import com.safetynet.alerts.mainclasses.firestationclasses.FirestationRepository;
import com.safetynet.alerts.mainclasses.medicalreccordclasses.MedicalRecordRepository;
import com.safetynet.alerts.mainclasses.personclasses.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PeopleByFireStationService {
    @Autowired
    public FirestationRepository firestationRepository;
    @Autowired
    public MedicalRecordRepository medicalRecordRepository;
    @Autowired
    public PersonRepository personRepository;
    public List<String> extractPeopleByFireStation(String stationNumber){
        List<String> people = new ArrayList<>();
        List<Firestation> firestationList = firestationRepository.findByStation(stationNumber);
        int childrenCounter = 0;
        int adultCounter = 0;
        for (Firestation fireStation : firestationList) {
            String address = fireStation.getAddress();
            List<Person> personList = personRepository.findByAddress(address);
            for (Person person : personList) {
                String firstName = person.getFirstName();
                String lastName = person.getLastName();
                List<MedicalRecord> medicalRecordList = medicalRecordRepository.findByFirstNameAndLastName(firstName, lastName);
                for (MedicalRecord medicalRecord : medicalRecordList) {
                    people.add(firstName + " " + lastName + " address: " + person.getAddress() + " phone number: " + person.getPhone());
                    int age = medicalRecord.getAge();
                    if (age < 18) {
                        childrenCounter++;
                    } else {
                        adultCounter++;
                    }
                }
            }
        }
        people.add("NB of adults is " + adultCounter);
        people.add("NB of children is " + childrenCounter);
        return people;
    }
}

