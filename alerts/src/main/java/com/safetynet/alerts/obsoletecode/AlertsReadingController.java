//package com.safetynet.alerts.readingdata;
//
//import com.safetynet.alerts.threemainclasses.AllData;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class AlertsReadingController {
//
//    private final AlertsReadingService alertsReadingService;
//    private static final String jsonUrl = "https://s3-eu-west-1.amazonaws.com/course.oc-static.com/projects/DA+Java+EN/P5+/data.json";
//
//    public AlertsReadingController(AlertsReadingService alertsReadingService) {
//        this.alertsReadingService = alertsReadingService;
//    }
//
//    @GetMapping("/myData")
//    public AllData getData() {
//        return alertsReadingService.fetchData(jsonUrl);
//    }
//}