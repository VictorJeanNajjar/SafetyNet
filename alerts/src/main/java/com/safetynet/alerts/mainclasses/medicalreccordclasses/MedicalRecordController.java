package com.safetynet.alerts.mainclasses.medicalreccordclasses;

import com.safetynet.alerts.mainclasses.MedicalRecord;
import com.safetynet.alerts.mainclasses.Person;
import com.safetynet.alerts.mainclasses.personclasses.PersonRepository;
import com.safetynet.alerts.mainclasses.personclasses.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/medicalRecord")
public class MedicalRecordController {
    @Autowired
    public MedicalRecordService medicalRecordService;
    @Autowired
    public MedicalRecordRepository medicalRecordRepository;
    public PersonService personService;

    public MedicalRecordController() {
    }
    public MedicalRecordController(MedicalRecordService medicalRecordService, MedicalRecordRepository medicalRecordRepository) {
        this.medicalRecordService = medicalRecordService;
        this.medicalRecordRepository = medicalRecordRepository;
    }

    @PostMapping
    public void addNewMedicalRecordController(@RequestBody MedicalRecord medicalRecord, @RequestParam Person person) {
        medicalRecordService.addNewMedicalRecord(medicalRecord);
        personService.addNewPerson(person);
    }
    @DeleteMapping(path = "{id}")
    public void deleteMedicalRecordController(@PathVariable("id") Long id){
        medicalRecordService.deleteMedicalRecord(id);
        personService.deletePerson(id);

    }
    @PutMapping(path = "{medicalRecordId}")
    public void updateMedicalRecordController(@PathVariable("medicalRecordId") Long id,
                                              @RequestParam (required = false) String firstName,
                                              @RequestParam (required = false) String lastName,
                                              @RequestParam (required = false) String birthdate,
                                              @RequestParam (required = false) ArrayList<String> medications,
                                              @RequestParam (required = false) ArrayList<String> allergies){
        medicalRecordService.updateMedicalRecord(id, firstName, lastName, birthdate, medications, allergies);
    }
    @GetMapping("/test")
    public List<MedicalRecord> getAllMedicalRecords() {
        return medicalRecordRepository.findAll();
    }
}

