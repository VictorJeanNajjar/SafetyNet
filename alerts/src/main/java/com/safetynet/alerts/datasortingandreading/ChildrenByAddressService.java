package com.safetynet.alerts.datasortingandreading;

import com.safetynet.alerts.mainclasses.MedicalRecord;
import com.safetynet.alerts.mainclasses.Person;
import com.safetynet.alerts.mainclasses.repositories.MedicalRecordRepository;
import com.safetynet.alerts.mainclasses.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChildrenByAddressService {
    @Autowired
    public PersonRepository personRepository;
    @Autowired
    public MedicalRecordRepository medicalRecordRepository;
    public List<String> extractChildrenByAddress(String address) {
        List<String> childrenAndAdults = new ArrayList<>();
        List<String> children = new ArrayList<>();
        List<String> adults = new ArrayList<>();
        List<Person> personList = personRepository.findByAddress(address);

        for (Person person : personList) {
            String firstName = person.firstName;
            String lastName = person.lastName;
            String fullName = firstName + " " + lastName;
            List<MedicalRecord> medicalRecordList = medicalRecordRepository.findByFirstNameAndLastName(firstName, lastName);
            for (MedicalRecord medicalRecord : medicalRecordList) {
                int age = medicalRecord.getAge();
                    if (age < 18) {
                        children.add(fullName + ", Age: " + age);
                    } else {
                        adults.add(fullName + ", Age: " + age);
                    }
            }
        }

        if (!children.isEmpty()) {
            childrenAndAdults.add("Children at this address:");
            childrenAndAdults.addAll(children);
        }
        if (!adults.isEmpty()) {
            childrenAndAdults.add("Adults at this address:");
            childrenAndAdults.addAll(adults);
        }
        if (children.isEmpty()) {
            childrenAndAdults = children;
            childrenAndAdults.add("No children at this address");
        }
        if (adults.isEmpty()) {
            childrenAndAdults.add("No adults at this address:");
        }
        return childrenAndAdults;
    }
}
