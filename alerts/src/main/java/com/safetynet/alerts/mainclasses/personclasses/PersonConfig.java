package com.safetynet.alerts.mainclasses.personclasses;

import com.safetynet.alerts.mainclasses.AllData;
import com.safetynet.alerts.mainclasses.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static com.safetynet.alerts.AlertsApplication.jsonUrl;

@Configuration
public class PersonConfig {
    @Autowired
    public com.safetynet.alerts.mainclasses.repositories.PersonRepository PersonRepository;
    @Bean
    public CommandLineRunner loadDataPerson(RestTemplate restTemplate) {
        return args -> {
            AllData allData = restTemplate.getForObject(jsonUrl, AllData.class);
            if (allData != null) {
                List<Person> medicalRecords = allData.getPersons();
                if (medicalRecords != null && !medicalRecords.isEmpty()) {
                    PersonRepository.saveAll(medicalRecords);
                }
            }
        };
    }
}
