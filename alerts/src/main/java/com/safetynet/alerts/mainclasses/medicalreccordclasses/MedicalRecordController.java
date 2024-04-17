package com.safetynet.alerts.mainclasses.medicalreccordclasses;

import com.safetynet.alerts.mainclasses.MedicalRecord;
import com.safetynet.alerts.mainclasses.repositories.MedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicalRecord")
public class MedicalRecordController {
    @Autowired
    public MedicalRecordService medicalRecordService;
    @Autowired
    public MedicalRecordRepository medicalRecordRepository;

    public MedicalRecordController() {
    }
    public MedicalRecordController(MedicalRecordService medicalRecordService, MedicalRecordRepository medicalRecordRepository) {
        this.medicalRecordService = medicalRecordService;
        this.medicalRecordRepository = medicalRecordRepository;
    }

    @PostMapping
    public String addNewMedicalRecordController(@RequestBody MedicalRecord medicalRecord){
        return medicalRecordService.addNewMedicalRecord(medicalRecord);
    }
    @DeleteMapping
    public String deleteMedicalRecordController(@RequestParam String firstName,
                                              @RequestParam String lastName){
        return medicalRecordService.deleteMedicalRecord(firstName, lastName);

    }
    @PutMapping
    public String updateMedicalRecordController(@RequestBody MedicalRecord medicalRecord){
        return medicalRecordService.updateMedicalRecord(medicalRecord);
    }
    @GetMapping("/test")
    public List<MedicalRecord> getAllMedicalRecords() {
        return medicalRecordRepository.findAll();
    }
}

