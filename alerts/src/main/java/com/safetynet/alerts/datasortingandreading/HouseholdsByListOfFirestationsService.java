package com.safetynet.alerts.datasortingandreading;

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
import java.util.Optional;

@Service
public class HouseholdsByListOfFirestationsService {
    @Autowired
    public FirestationRepository firestationRepository;
    @Autowired
    public PersonRepository personRepository;
    @Autowired
    public MedicalRecordRepository medicalRecordRepository;
    public List<String> extractHouseholdsByListOfFirestationsService (List<Integer> firestationsNumbers){
        List<String> houseHolds = new ArrayList<>();
        for (Integer integer : firestationsNumbers){
            if (integer <= 4 && integer > 0){
                List<Firestation> selectedFirestations = firestationRepository.findByStation(String.valueOf(integer));
                for (Firestation firestation : selectedFirestations){
                    String address = firestation.getAddress();
                    List<Person> housholdResidents = personRepository.findByAddress(address);
                    houseHolds.add("FireStation " + firestation.getStation() + " in charge of: ");
                    houseHolds.add("Residents of " + address + ": ");
                    for (Person resident : housholdResidents){
                        Long currentPersonId = resident.getId();
                        Optional<MedicalRecord> residentsMedicalRecordOptional = medicalRecordRepository.findById(currentPersonId);
                        if (residentsMedicalRecordOptional.isPresent()){
                            MedicalRecord residentsMedicalRecord = residentsMedicalRecordOptional.get();
                            houseHolds.add("Person Info:   Name: " +
                                    resident.getFirstName() + " " +
                                    resident.getLastName() + " PhoneNumber: " +
                                    resident.getPhone() + " Age: " +
                                    residentsMedicalRecord.getAge() + " Medications: " +
                                    residentsMedicalRecord.getMedications().toString() + " Allergies: " +
                                    residentsMedicalRecord.getAllergies().toString());
                        }
                    }
                }
            }
        }
        return houseHolds;
    }

}
//http://localhost:8080/flood/stations?stations=<a list of station_numbers>