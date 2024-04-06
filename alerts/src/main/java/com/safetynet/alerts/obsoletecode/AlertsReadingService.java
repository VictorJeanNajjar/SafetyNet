//package com.safetynet.alerts.readingdata;
//
//
//import com.safetynet.alerts.threemainclasses.AllData;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//@Service
//public class AlertsReadingService {
//
//    private final RestTemplate restTemplate;
//
//    public AlertsReadingService(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }
//
//    public AllData fetchData(String jsonUrl) {
//        return restTemplate.getForObject(jsonUrl, AllData.class);
//    }
//}
