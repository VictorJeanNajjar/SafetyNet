package com.safetynet.alerts.mainclasses.personclasses;

import com.safetynet.alerts.mainclasses.MedicalRecord;
import com.safetynet.alerts.mainclasses.Person;
import com.safetynet.alerts.mainclasses.medicalreccordclasses.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RestController
    @RequestMapping("/person")
public class PersonController {
    @Autowired
    public PersonService personService;
    @Autowired
    public PersonRepository personRepository;
    @Autowired
    public MedicalRecordService medicalRecordService;

    public PersonController() {
    }
    public PersonController(PersonService personService, PersonRepository personRepository, MedicalRecordService medicalRecordService) {
        this.personService = personService;
        this.personRepository = personRepository;
        this.medicalRecordService = medicalRecordService;
    }

    @PostMapping
    public void addNewPersonController(@RequestBody Person person, @RequestParam MedicalRecord medicalRecord) {
        personService.addNewPerson(person);
        medicalRecordService.addNewMedicalRecord(medicalRecord);
    }
    @DeleteMapping(path = "{id}")
    public void deletePersonController(@PathVariable("id") Long id){
        personService.deletePerson(id);
        medicalRecordService.deleteMedicalRecord(id);
    }
    @PutMapping(path = "{personId}")
    public void updatePersonController(@PathVariable("personId") Long id,
                                       @RequestParam (required = false) String firstName,
                                       @RequestParam (required = false) String lastName,
                                       @RequestParam (required = false) String address,
                                       @RequestParam (required = false) String city,
                                       @RequestParam (required = false) String zip,
                                       @RequestParam (required = false) String phone,
                                       @RequestParam (required = false) String email){
        personService.updatePerson(id, firstName, lastName, address, city, zip, phone, email);
    }
    @GetMapping("/test")
    public List<Person> getAllPerson() {
        return personRepository.findAll();
    }
}
