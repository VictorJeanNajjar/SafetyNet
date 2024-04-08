package com.safetynet.alerts.datasortingandwriting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
public class GeneralController {

    private final EmailsByCityService emailsByCityService;
    private final ChildrenByAddressService childrenByAddressService;
    private final PeopleByFireStationService peoplebyfirestation;

    @Autowired
    public GeneralController(EmailsByCityService emailsByCityService, ChildrenByAddressService childrenByAddressService, PeopleByFireStationService peoplebyfirestation) {
        this.emailsByCityService = emailsByCityService;
        this.childrenByAddressService = childrenByAddressService;
        this.peoplebyfirestation = peoplebyfirestation;
    }
//Email by city controller
    @GetMapping("communityEmail")
    public Set<String> getCommunityEmails(@RequestParam String city) {
        return emailsByCityService.extractEmailAddresses(city);
    }
//ChildrenByAddressController
    @GetMapping("childAlert")
    public List<String> getChildrenByAddress(@RequestParam String address) {
        return childrenByAddressService.extractChildrenByAddress(address);
    }
//PeopleByFireStationController
    @GetMapping("/firestation")
    public List<String> getPeopleByFireStation(@RequestParam String stationNumber) {
        return peoplebyfirestation.extractPeopleByFireStation(stationNumber);
    }
}
