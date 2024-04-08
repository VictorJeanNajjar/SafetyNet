package com.safetynet.alerts.mainclasses.medicalreccordclasses;

import com.safetynet.alerts.mainclasses.MedicalRecord;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;


@Service
public class MedicalRecordService {
    public ArrayList<MedicalRecord> medicalRecord;
    @Autowired
    private final MedicalRecordRepository medicalRecordRepository;
    @Autowired
    public MedicalRecordService(ArrayList<MedicalRecord> medicalRecord,
                         MedicalRecordRepository medicalRecordRepository) {
        this.medicalRecord = medicalRecord;
        this.medicalRecordRepository = medicalRecordRepository;
    }
    public void addNewMedicalRecord(MedicalRecord medicalRecord) {
        String firstName = medicalRecord.getFirstName();
        String lastName = medicalRecord.getLastName();
        String birthdate = medicalRecord.getBirthdate();
        ArrayList<String> medications = medicalRecord.getMedications();
        ArrayList<String> allergies = medicalRecord.getAllergies();
        Optional<MedicalRecord> optionalMedicalRecord = medicalRecordRepository.findByFirstNameAndLastNameAndBirthdateAndMedicationsAndAllergies(firstName, lastName, birthdate, medications, allergies);
        if (optionalMedicalRecord.isEmpty()) {
            medicalRecordRepository.save(medicalRecord);
        } else {
            System.out.println("Medical Record already exists");
        }
    }
    public void deleteMedicalRecord(Long id){
        if (medicalRecordRepository.findById(id).isPresent()) {
            medicalRecordRepository.deleteById(id);
        }
        else{
            System.out.println("Medical record does not exist");
        }
    }
    @Transactional
    public void updateMedicalRecord(Long id,
                                    String firstName,
                                    String lastName,
                                    String birthdate,
                                    ArrayList<String> medications,
                                    ArrayList<String> allergies) {
        Optional<MedicalRecord> optionalMedicalRecord = medicalRecordRepository.findById(id);
        if (optionalMedicalRecord.isPresent()) {
            MedicalRecord medicalRecord = optionalMedicalRecord.get();
            medicalRecord.setFirstName(firstName);
            medicalRecord.setLastName(lastName);
            medicalRecord.setBirthdate(birthdate);
            medicalRecord.setMedications(medications);
            medicalRecord.setAllergies(allergies);
            medicalRecordRepository.save(medicalRecord);
        } else {
            throw new EntityNotFoundException("Medical Record with id " + id + " not found");
        }
    }
}
