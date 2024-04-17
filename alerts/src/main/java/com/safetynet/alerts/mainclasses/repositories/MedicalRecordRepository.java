package com.safetynet.alerts.mainclasses.repositories;


import com.safetynet.alerts.mainclasses.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
    public Optional<MedicalRecord> findByFirstNameAndLastNameAndBirthdate(String firstName,
                                                                          String lastName,
                                                                          String birthdate);
    public List <MedicalRecord> findByFirstNameAndLastName(String firstName,
                                                           String lastName);
}
