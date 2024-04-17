package com.safetynet.alerts.datasortingandreading;

import com.safetynet.alerts.mainclasses.Firestation;
import com.safetynet.alerts.mainclasses.Person;
import com.safetynet.alerts.mainclasses.repositories.FirestationRepository;
import com.safetynet.alerts.mainclasses.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class PhoneNumbersByFirestationService {
    @Autowired
    public FirestationRepository firestationRepository;
    @Autowired
    public PersonRepository personRepository;
    public List<String> extractPhoneNumbersByFirestation(String stationNumber){
        List<String> phoneNumbers = new ArrayList<>();
        List<Firestation> firestationList = firestationRepository.findByStation(stationNumber);
        for (Firestation fireStation : firestationList) {
            String address = fireStation.getAddress();
            List<Person> personList = personRepository.findByAddress(address);
            for (Person person : personList) {
                phoneNumbers.add(person.getPhone());
            }
        }
        return phoneNumbers;
    }
}