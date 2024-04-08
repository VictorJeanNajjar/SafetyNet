package com.safetynet.alerts.mainclasses.firestationclasses;

import com.safetynet.alerts.mainclasses.Firestation;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public void addNewFirestation(Firestation firestation) {
        String address = firestation.getAddress();
        String station = firestation.getStation();
        Optional<Firestation> optionalFirestation = firestationRepository.findByAddressAndStation(address, station);
        if (optionalFirestation.isEmpty()) {
            firestationRepository.save(firestation);
        } else {
            System.out.println("fire station address already exists");
        }
    }
    public void deleteFirestation(Long id){
        if (firestationRepository.findById(id).isPresent()) {
            firestationRepository.deleteById(id);
        }
        else{
            System.out.println("fire station address does not exist");
        }
    }
    @Transactional
    public void updateFirestations(Long id, String address, String stationNumber) {
        Optional<Firestation> optionalFirestation = firestationRepository.findById(id);
        if (optionalFirestation.isPresent()) {
            Firestation firestation = optionalFirestation.get();
            firestation.setAddress(address);
            firestation.setStation(stationNumber);
            firestationRepository.save(firestation);
        } else {
            throw new EntityNotFoundException("Firestation with id " + id + " not found");
        }
    }
}
