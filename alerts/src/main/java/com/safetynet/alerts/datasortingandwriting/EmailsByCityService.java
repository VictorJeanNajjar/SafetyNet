package com.safetynet.alerts.datasortingandwriting;

import com.safetynet.alerts.threemainclasses.AllData;
import com.safetynet.alerts.threemainclasses.Person;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.Set;

@Service
public class EmailsByCityService {

    private final RestTemplate restTemplate;

    private final String jsonUrl = "https://s3-eu-west-1.amazonaws.com/course.oc-static.com/projects/DA+Java+EN/P5+/data.json";

    public EmailsByCityService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Set<String> extractEmailAddresses() {
        Set<String> emailAddresses = new HashSet<>();
        AllData allData = restTemplate.getForObject(jsonUrl, AllData.class);
        if (allData != null) {
            for (Person person : allData.getPersons()) {
                emailAddresses.add(person.getEmail());
            }
        }
        System.out.println(emailAddresses);
        return emailAddresses;
    }
}
//http://localhost:8080/communityEmail?city=<city>