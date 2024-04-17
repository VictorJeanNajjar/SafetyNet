package com.safetynet.alerts.datasortingandreading;

import com.safetynet.alerts.mainclasses.Person;
import com.safetynet.alerts.mainclasses.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class EmailsByCityService {
    @Autowired
    public PersonRepository personRepository;
    public Set<String> extractEmailAddresses(String city) {
        Set<String> emailAddresses = new HashSet<>();
        List<Person> personList = personRepository.findByCity(city);
        for (Person person : personList) {
                emailAddresses.add(person.getEmail());
            }
        System.out.println(emailAddresses);
        return emailAddresses;
    }
}