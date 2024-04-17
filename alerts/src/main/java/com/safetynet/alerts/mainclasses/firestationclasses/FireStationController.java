package com.safetynet.alerts.mainclasses.firestationclasses;

import com.safetynet.alerts.mainclasses.Firestation;
import com.safetynet.alerts.mainclasses.repositories.FirestationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/firestation")
public class FireStationController {
    @Autowired
    public FirestationService firestationService;
    @Autowired
    public FirestationRepository firestationRepository;

    public FireStationController() {
    }
    public FireStationController(FirestationService firestationService, FirestationRepository firestationRepository) {
        this.firestationService = firestationService;
        this.firestationRepository = firestationRepository;
    }

    @PostMapping
    public String addNewFirestationController(@RequestBody Firestation firestation) {
        return firestationService.addNewFirestation(firestation);
    }
    @DeleteMapping
    public String deleteFirestationController(@RequestParam String address, @RequestParam String station){
        return firestationService.deleteFirestation(address, station);
    }
    @PutMapping
    public String updateFirestationsController(@RequestBody Firestation firestation){
        return firestationService.updateFirestations(firestation);
    }
    @GetMapping("/test")
    public List<Firestation> getAllFireStations() {
        return firestationRepository.findAll();
    }
}
