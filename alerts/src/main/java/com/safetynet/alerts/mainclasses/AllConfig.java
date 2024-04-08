package com.safetynet.alerts.mainclasses;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Configuration
public class AllConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
