package com.safetynet.alerts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EntityScan("com.safetynet.alerts.mainclasses")
public class AlertsApplication {
	public static RestTemplate restTemplate;
	public static final String jsonUrl = "https://s3-eu-west-1.amazonaws.com/course.oc-static.com/projects/DA+Java+EN/P5+/data.json";
	public AlertsApplication(RestTemplate restTemplate) {
		AlertsApplication.restTemplate = restTemplate;
	}
	public static void main(String[] args) {
		SpringApplication.run(AlertsApplication.class, args);
	}

}
