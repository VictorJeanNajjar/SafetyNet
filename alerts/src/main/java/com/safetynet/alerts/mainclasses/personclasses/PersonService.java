package com.safetynet.alerts.mainclasses.personclasses;

import com.safetynet.alerts.mainclasses.Person;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public void addNewPerson(Person person) {
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
            System.out.println("Person address already exists");
        }
    }
    public void deletePerson(Long id){
        if (personRepository.findById(id).isPresent()) {
            personRepository.deleteById(id);
        }
        else{
            System.out.println("Person does not exist");
        }
    }
    @Transactional
    public void updatePerson(Long id,
                             String firstName,
                             String lastName,
                             String address,
                             String city,
                             String zip,
                             String phone,
                             String email) {
        Optional<Person> optionalPerson = personRepository.findById(id);
        if (optionalPerson.isPresent()) {
            Person person = optionalPerson.get();
            person.setFirstName(firstName);
            person.setLastName(lastName);
            person.setAddress(address);
            person.setCity(city);
            person.setZip(zip);
            person.setPhone(phone);
            person.setEmail(email);
            personRepository.save(person);
        } else {
            throw new EntityNotFoundException("Person with id " + id + " not found");
        }
    }
}
