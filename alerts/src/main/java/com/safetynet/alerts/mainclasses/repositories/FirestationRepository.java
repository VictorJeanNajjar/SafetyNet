package com.safetynet.alerts.mainclasses.repositories;

import com.safetynet.alerts.mainclasses.Firestation;
import com.safetynet.alerts.mainclasses.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FirestationRepository extends JpaRepository<Firestation, Long> {
    public List<Firestation> findByAddressAndStation(String address, String station);
    public List<Firestation> findByStation(String station);
    public List<Firestation> findByAddress(String address);
}
