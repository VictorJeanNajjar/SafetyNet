package com.safetynet.alerts.datasortingandreading;

import com.safetynet.alerts.mainclasses.Firestation;
import com.safetynet.alerts.mainclasses.MedicalRecord;
import com.safetynet.alerts.mainclasses.Person;
import com.safetynet.alerts.mainclasses.repositories.FirestationRepository;
import com.safetynet.alerts.mainclasses.repositories.MedicalRecordRepository;
import com.safetynet.alerts.mainclasses.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PeopleAndFireStationNumberByAddressService {
    @Autowired
    public FirestationRepository firestationRepository;
    @Autowired
    public MedicalRecordRepository medicalRecordRepository;
    @Autowired
    public PersonRepository personRepository;
    public List<String> extractPeopleAndFireStationNumberByAddressService(String address){
        List<String> addressInfo = new ArrayList<>();
        List<Firestation> stationNumberFinder = firestationRepository.findByAddress(address);
        List<Person> personList = personRepository.findByAddress(address);
        for (Firestation firestation : stationNumberFinder){
            String stationNumber = firestation.getStation();
            addressInfo.add("Station number: " + stationNumber + " is responsible for " + address);
        }
        addressInfo.add("People at this address: ");
        for (Person person : personList){
            long personId = person.getId();
            Optional<MedicalRecord> currentMedicalRecordOptional = medicalRecordRepository.findById(personId);
            if (currentMedicalRecordOptional.isPresent()){
                MedicalRecord currentMedicalRecord = currentMedicalRecordOptional.get();
                addressInfo.add("Name: " +
                        person.getFirstName() + " " +
                        person.getLastName() + " Phone Number: " +
                        person.getPhone()+ " Age: " +
                        currentMedicalRecord.getAge() + " Medication: " +
                        currentMedicalRecord.getMedications().toString() + " Allergies: " +
                        currentMedicalRecord.getAllergies());
            }
            else{
                addressInfo.add(" no medical records");
            }
        }
        return addressInfo;
    }
}
