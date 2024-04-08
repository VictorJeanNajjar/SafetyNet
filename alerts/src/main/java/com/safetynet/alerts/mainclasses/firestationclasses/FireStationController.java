package com.safetynet.alerts.mainclasses.firestationclasses;

import com.safetynet.alerts.mainclasses.Firestation;
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
    public void addNewFirestationController(@RequestBody Firestation firestation) {
        firestationService.addNewFirestation(firestation);
    }
    @DeleteMapping(path = "{firestationId}")
    public void deleteFirestationController(@PathVariable("firestationId") Long id){
        firestationService.deleteFirestation(id);
    }
    @PutMapping(path = "{firestationId}")
    public void updateFirestationsController(@PathVariable("firestationId") Long id,
                                   @RequestParam (required = false) String address,
                                   @RequestParam (required = false) String stationNumber){
        firestationService.updateFirestations(id, address, stationNumber);
    }
    @GetMapping("/test")
    public List<Firestation> getAllFireStations() {
        return firestationRepository.findAll();
    }
}
