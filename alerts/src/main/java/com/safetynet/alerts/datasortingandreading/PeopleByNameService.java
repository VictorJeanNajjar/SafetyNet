package com.safetynet.alerts.datasortingandreading;

import com.safetynet.alerts.mainclasses.MedicalRecord;
import com.safetynet.alerts.mainclasses.Person;
import com.safetynet.alerts.mainclasses.repositories.MedicalRecordRepository;
import com.safetynet.alerts.mainclasses.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PeopleByNameService {
    @Autowired
    public PersonRepository personRepository;
    @Autowired
    public MedicalRecordRepository medicalRecordRepository;
    public List<String> extractPeopleByName(String firstName, String lastName){
        List<String> peopleInfo = new ArrayList<>();
        List <Person> personList = personRepository.findByFirstNameAndLastName(firstName, lastName);
        for (Person person : personList){
            Long personId = person.getId();
            peopleInfo.add("Person: " +
                    person.getFirstName()+ " " +
                    person.getLastName()+" address: " +
                    person.getAddress() + " email: " +
                    person.getEmail());
            Optional<MedicalRecord> medicalRecordListOptional = medicalRecordRepository.findById(personId);
            if (medicalRecordListOptional.isPresent()){
            MedicalRecord currentmedicalRecord = medicalRecordListOptional.get();
            peopleInfo.add("age: " +
                    currentmedicalRecord.getAge()+ " medication(s): " +
                    currentmedicalRecord.getMedications().toString()+ " allergies: " +
                    currentmedicalRecord.getAllergies().toString());
            }
        }return peopleInfo;
    }
}