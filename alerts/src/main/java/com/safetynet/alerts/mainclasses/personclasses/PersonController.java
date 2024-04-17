package com.safetynet.alerts.mainclasses.personclasses;

import com.safetynet.alerts.mainclasses.Person;
import com.safetynet.alerts.mainclasses.medicalreccordclasses.MedicalRecordService;
import com.safetynet.alerts.mainclasses.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public String addNewPersonController(@RequestBody Person  person){
        return personService.addNewPerson(person);
    }
    @DeleteMapping
    public String deletePersonController(@RequestParam String firstName,
                                       @RequestParam String lastName){
        return personService.deletePerson(firstName, lastName);
    }
    @PutMapping
    public String updatePersonController(@RequestBody Person person){
        return personService.updatePerson(person);
    }
    @GetMapping("/test")
    public List<Person> getAllPerson() {
        return personRepository.findAll();
    }
}
