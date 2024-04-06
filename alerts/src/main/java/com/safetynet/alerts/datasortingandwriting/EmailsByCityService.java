package com.safetynet.alerts.datasortingandwriting;

import com.safetynet.alerts.mainclasses.Person;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.Set;

import static com.safetynet.alerts.AlertsApplication.myData;

@Service
public class EmailsByCityService {
    public Set<String> extractEmailAddresses(@RequestParam String city) {
        Set<String> emailAddresses = new HashSet<>();
        if (myData != null) {
            for (Person person : myData.getPersons()) {
                if (person.getCity().equals(city)) {
                    emailAddresses.add(person.getEmail());
                }
            }
        }
        System.out.println(emailAddresses);
        return emailAddresses;
    }
}