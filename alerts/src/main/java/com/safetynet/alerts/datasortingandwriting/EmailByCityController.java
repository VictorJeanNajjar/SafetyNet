package com.safetynet.alerts.datasortingandwriting;

import com.safetynet.alerts.readingdata.AlertsReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class EmailByCityController {

    private final AlertsReadingService alertsReadingService;
    private final EmailsByCityService emailsByCityService;

    @Autowired
    public EmailByCityController(AlertsReadingService alertsReadingService, EmailsByCityService emailsByCityService) {
        this.alertsReadingService = alertsReadingService;
        this.emailsByCityService = emailsByCityService;
    }

    @GetMapping("/communityEmail")
    public Set<String> getCommunityEmails() {
        return emailsByCityService.extractEmailAddresses();
    }
}
