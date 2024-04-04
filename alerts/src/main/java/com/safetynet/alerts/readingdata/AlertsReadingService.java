package com.safetynet.alerts.readingdata;


import com.safetynet.alerts.threemainclasses.AllData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AlertsReadingService {

    private final RestTemplate restTemplate;

    @Value("${https://s3-eu-west-1.amazonaws.com/course.oc-static.com/projects/DA+Java+EN/P5+/data.json}")
    private String jsonUrl;

    public AlertsReadingService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public AllData fetchData() {
        return restTemplate.getForObject(jsonUrl, AllData.class);
    }
}
