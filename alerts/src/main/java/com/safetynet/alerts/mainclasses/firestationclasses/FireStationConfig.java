package com.safetynet.alerts.mainclasses.firestationclasses;

import com.safetynet.alerts.mainclasses.AllData;
import com.safetynet.alerts.mainclasses.Firestation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static com.safetynet.alerts.AlertsApplication.jsonUrl;

@Configuration
public class FireStationConfig {
    @Autowired
    public FirestationRepository firestationRepository;
    @Bean
    public CommandLineRunner loadDataFirestation(RestTemplate restTemplate) {
        return args -> {
            AllData allData = restTemplate.getForObject(jsonUrl, AllData.class);
            if (allData != null) {
                List<Firestation> firestations = allData.getFirestations();
                if (firestations != null && !firestations.isEmpty()) {
                    firestationRepository.saveAll(firestations);
                }
            }
        };
    }
}
