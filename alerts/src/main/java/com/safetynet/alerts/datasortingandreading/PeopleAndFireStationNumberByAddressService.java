package com.safetynet.alerts.datasortingandreading;

import com.safetynet.alerts.mainclasses.MedicalRecord;
import com.safetynet.alerts.mainclasses.Person;
import com.safetynet.alerts.mainclasses.firestationclasses.FirestationRepository;
import com.safetynet.alerts.mainclasses.medicalreccordclasses.MedicalRecordRepository;
import com.safetynet.alerts.mainclasses.personclasses.PersonRepository;
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
        String stationNumber = firestationRepository.findByAddress(address).getStation();
        List<Person> personList = personRepository.findByAddress(address);
        addressInfo.add("Station number: " + stationNumber);
        for (Person person : personList){
            long personId = person.getId();
            Optional<MedicalRecord> currentMedicalRecordOptional = medicalRecordRepository.findById(personId);
            if (currentMedicalRecordOptional.isPresent()){
                MedicalRecord currentMedicalRecord = currentMedicalRecordOptional.get();
                addressInfo.add("Person Name: " +
                        person.getFirstName() + " " +
                        person.getLastName() + " Phone Number: " +
                        person.getPhone()+ " Age: " +
                        currentMedicalRecord.getAge() + " Medication: " +
                        currentMedicalRecord.getMedications().toString() + " Allergies: " +
                        currentMedicalRecord.getAllergies());
            }
        }
        return addressInfo;
    }
}
