package com.safetynet.alerts.mainclasses.firestationclasses;

import com.safetynet.alerts.mainclasses.Firestation;
import com.safetynet.alerts.mainclasses.repositories.FirestationRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class FirestationService {
    public ArrayList<Firestation> firestation;
    @Autowired
    private final FirestationRepository firestationRepository;

    @Autowired
    public FirestationService(ArrayList<Firestation> firestation,
                              FirestationRepository firestationRepository) {
        this.firestation = firestation;
        this.firestationRepository = firestationRepository;
    }

    public String addNewFirestation(Firestation firestation) {
        String address = firestation.getAddress();
        String station = firestation.getStation();
        List<Firestation> getFirestation = firestationRepository.findByAddressAndStation(address, station);
        if (getFirestation.isEmpty() && Integer.parseInt(station) <= 4 && Integer.parseInt(station) >= 1) {
            firestationRepository.save(firestation);
        }if (Integer.parseInt(station) > 4 || Integer.parseInt(station) < 1){
            return "fire station number does not exist";
        }if (!getFirestation.isEmpty()) {
            return "fire station address address already exists";
        }
        return "fire station address successfully added";
    }
    @Transactional
    public String deleteFirestation(String address, String station) {
        List<Firestation> firestationList = firestationRepository.findByAddressAndStation(address, station);
        if (firestationList.isEmpty()) {
            return "Firestation address " + address + " not found";
        }
        else {
            for(Firestation firestation : firestationList){
                Long id = firestation.getId();
                firestationRepository.deleteById(id);
            }
        }
        return "fire station address successfully deleted";
    }

    @Transactional
    public String updateFirestations(Firestation getfirestation) {
        String address = getfirestation.getAddress();
        String station = getfirestation.getStation();
        List<Firestation> firestationList = firestationRepository.findByAddress(address);
        if (firestationList.isEmpty()) {
            throw new EntityNotFoundException("Firestation address " + address + " not found please create one");
        }
        else{
            for(Firestation firestation : firestationList){
                firestation.setStation(station);
                firestationRepository.save(firestation);
            }
        }
        return "fire station address successfully updated";
    }
}
