package com.safetynet.alerts.mainclasses.medicalreccordclasses;

import com.safetynet.alerts.mainclasses.MedicalRecord;
import com.safetynet.alerts.mainclasses.repositories.MedicalRecordRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class MedicalRecordService {
    @Autowired
    private final MedicalRecordRepository medicalRecordRepository;
    public MedicalRecordService(MedicalRecordRepository medicalRecordRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
    }
    public String addNewMedicalRecord(MedicalRecord medicalRecord) {
        String firstName = medicalRecord.getFirstName();
        String lastName = medicalRecord.getLastName();
        String birthdate = medicalRecord.getBirthdate();
        Optional<MedicalRecord> optionalMedicalRecord = medicalRecordRepository.findByFirstNameAndLastNameAndBirthdate(firstName, lastName, birthdate);
        if (optionalMedicalRecord.isEmpty()) {
            medicalRecordRepository.save(medicalRecord);
        } else {
            return "Medical Record already exists";
        }
        return "Medical record successfully added";
    }
    @Transactional
    public String deleteMedicalRecord(String firstName, String lastName){
        List<MedicalRecord> medicalRecords = medicalRecordRepository.findByFirstNameAndLastName(firstName, lastName);
        if(!medicalRecords.isEmpty()) {
            MedicalRecord medicalRecord = medicalRecords.getFirst();
            Long id = medicalRecord.getId();
            medicalRecordRepository.deleteById(id);
        } else{
            return "Medical record does not exist";
        }
        return "Medical record successfully Deleted";
    }
    @Transactional
    public String updateMedicalRecord(MedicalRecord getmedicalRecord) {
        String firstName = getmedicalRecord.getFirstName();
        String lastName = getmedicalRecord.getLastName();
        String birthdate = getmedicalRecord.getBirthdate();
        List<String> medications = getmedicalRecord.getMedications();
        List<String> allergies = getmedicalRecord.getAllergies();
        List<MedicalRecord> medicalRecords = medicalRecordRepository.findByFirstNameAndLastName(firstName, lastName);
        if(!medicalRecords.isEmpty()) {
            MedicalRecord medicalRecord = medicalRecords.getFirst();
            medicalRecord.setBirthdate(birthdate);
            medicalRecord.setMedications(medications);
            medicalRecord.setAllergies(allergies);
            medicalRecordRepository.save(medicalRecord);
        } else{
            throw new EntityNotFoundException("Medical Record with " + firstName + " " + lastName + " not found");
        }
        return "Medical record successfully updated";
    }
}
