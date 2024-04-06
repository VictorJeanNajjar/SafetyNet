package com.safetynet.alerts.datasortingandwriting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class GeneralController {

    private final EmailsByCityService emailsByCityService;

    @Autowired
    public GeneralController(EmailsByCityService emailsByCityService) {
        this.emailsByCityService = emailsByCityService;
    }
//Email by city controller
    @GetMapping("communityEmail")
    public Set<String> getCommunityEmails(@RequestParam String city) {
        return emailsByCityService.extractEmailAddresses(city);
    }
}
