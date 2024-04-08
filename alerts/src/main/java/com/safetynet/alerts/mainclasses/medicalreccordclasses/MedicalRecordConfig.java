package com.safetynet.alerts.mainclasses.medicalreccordclasses;

import com.safetynet.alerts.mainclasses.AllData;
import com.safetynet.alerts.mainclasses.MedicalRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static com.safetynet.alerts.AlertsApplication.jsonUrl;

@Configuration
public class MedicalRecordConfig {
    @Autowired
    public MedicalRecordRepository medicalRecordRepository;
    @Bean
    public CommandLineRunner loadDataMedicalRecord(RestTemplate restTemplate) {
        return args -> {
            AllData allData = restTemplate.getForObject(jsonUrl, AllData.class);
            if (allData != null) {
                List<MedicalRecord> medicalRecords = allData.getMedicalRecords();
                if (medicalRecords != null && !medicalRecords.isEmpty()) {
                    medicalRecordRepository.saveAll(medicalRecords);
                }
            }
        };
    }
}
