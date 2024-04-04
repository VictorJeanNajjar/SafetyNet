package com.safetynet.alerts.readingdata;

import com.safetynet.alerts.threemainclasses.AllData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlertsReadingController {

    private final AlertsReadingService alertsReadingService;

    public AlertsReadingController(AlertsReadingService alertsReadingService) {
        this.alertsReadingService = alertsReadingService;
    }

    @GetMapping
    public AllData getData() {
        return alertsReadingService.fetchData();
    }
}