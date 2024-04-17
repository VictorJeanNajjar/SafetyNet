package com.safetynet.alerts.mainclasses.personclasses;

import com.safetynet.alerts.mainclasses.Person;
import com.safetynet.alerts.mainclasses.repositories.PersonRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class PersonService {
    public ArrayList<Person> person;
    @Autowired
    private final PersonRepository personRepository;
    @Autowired
    public PersonService(ArrayList<Person> person,
                              PersonRepository personRepository) {
        this.person = person;
        this.personRepository = personRepository;
    }
    public String addNewPerson(Person person) {
        String firstName = person.getFirstName();
        String lastName = person.getLastName();
        String address = person.getAddress();
        String city = person.getCity();
        String zip = person.getZip();
        String phone = person.getPhone();
        String email = person.getEmail();
        Optional<Person> optionalPerson = personRepository.findByFirstNameAndLastNameAndAddressAndCityAndZipAndPhoneAndEmail(firstName, lastName, address, city, zip, phone, email);
        if (optionalPerson.isEmpty()) {
            personRepository.save(person);
        } else {
            return "Person address already exists";
        }
        return "Person successfully added";
    }
    public String deletePerson(String firstName,
                             String lastName){
        List<Person> persons = personRepository.findByFirstNameAndLastName(firstName, lastName);
        if(!persons.isEmpty()) {
            Person person = persons.getFirst();
            Long id = person.getId();
            personRepository.deleteById(id);
        } else {
            return "Person does not exist";
        }
        return "Person successfully Deleted";
    }
    @Transactional
    public String updatePerson(@RequestBody Person getperson) {
        String firstName = getperson.getFirstName();
        String lastName = getperson.getLastName();
        String address = getperson.getAddress();
        String city = getperson.getCity();
        String zip = getperson.getZip();
        String phone = getperson.getPhone();
        String email = getperson.getEmail();
        List<Person> persons = personRepository.findByFirstNameAndLastName(firstName, lastName);
        if(!persons.isEmpty()) {
            Person person = persons.getFirst();
            person.setFirstName(firstName);
            person.setLastName(lastName);
            person.setAddress(address);
            person.setCity(city);
            person.setZip(zip);
            person.setPhone(phone);
            person.setEmail(email);
            personRepository.save(person);
        } else {
            return "Person with " + firstName + " " + lastName + " not found";
        }
        return "Person successfully updated";
    }
}
