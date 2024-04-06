package com.safetynet.alerts.mainclasses;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AlertsReadingConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
