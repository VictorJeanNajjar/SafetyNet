package com.safetynet.alerts.datasortingandreading;

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
    private final PhoneNumbersByFirestationService phoneNumbersByFirestationService;
    private final PeopleByNameService peopleByNameService;
    private final PeopleAndFireStationNumberByAddressService peopleAndFireStationNumberByAddressService;
    private final HouseholdsByListOfFirestationsService householdsByListOfFirestationsService;

    @Autowired
    public GeneralController(EmailsByCityService emailsByCityService,
                             ChildrenByAddressService childrenByAddressService,
                             PeopleByFireStationService peoplebyfirestation,
                             PhoneNumbersByFirestationService phoneNumbersByFirestationService,
                             PeopleByNameService peopleByNameService,
                             PeopleAndFireStationNumberByAddressService peopleAndFireStationNumberByAddressService,
                             HouseholdsByListOfFirestationsService householdsByListOfFirestationsService) {
        this.emailsByCityService = emailsByCityService;
        this.childrenByAddressService = childrenByAddressService;
        this.peoplebyfirestation = peoplebyfirestation;
        this.phoneNumbersByFirestationService = phoneNumbersByFirestationService;
        this.peopleByNameService = peopleByNameService;
        this.peopleAndFireStationNumberByAddressService = peopleAndFireStationNumberByAddressService;
        this.householdsByListOfFirestationsService = householdsByListOfFirestationsService;
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
//PhoneAlertController
    @GetMapping("/phoneAlert")
    public List<String> getPhoneNumbersByFireStation(@RequestParam String firestation) {
        return phoneNumbersByFirestationService.extractPhoneNumbersByFirestation(firestation);
    }
//peopleByNameServiceController
    @GetMapping("/personInfo")
    public List<String> getPersonByName (@RequestParam String firstName, @RequestParam String lastName){
        return peopleByNameService.extractPeopleByName(firstName, lastName);
    }
    @GetMapping("/fire")
    public List<String> getpeopleAndFireStationNumberByAddressService(@RequestParam String address){
        return peopleAndFireStationNumberByAddressService.extractPeopleAndFireStationNumberByAddressService(address);
    }
    @GetMapping("/flood")
    public List<String> getHouseholdsByListOfFirestationsService(@RequestParam List<Integer> stations){
        return householdsByListOfFirestationsService.extractHouseholdsByListOfFirestationsService(stations);
    }
}
