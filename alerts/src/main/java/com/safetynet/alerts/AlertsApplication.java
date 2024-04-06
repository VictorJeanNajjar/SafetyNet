package com.safetynet.alerts;

import com.safetynet.alerts.mainclasses.AllData;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class AlertsApplication {
	public static AllData myData;
	private static RestTemplate restTemplate;
	private static final String jsonUrl = "https://s3-eu-west-1.amazonaws.com/course.oc-static.com/projects/DA+Java+EN/P5+/data.json";
	public AlertsApplication(RestTemplate restTemplate) {
		AlertsApplication.restTemplate = restTemplate;
	}
	public static void main(String[] args) {
		SpringApplication.run(AlertsApplication.class, args);
		myData = restTemplate.getForObject(jsonUrl, AllData.class);
	}

}
