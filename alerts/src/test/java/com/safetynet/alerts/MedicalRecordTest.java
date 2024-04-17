package com.safetynet.alerts;

import com.safetynet.alerts.mainclasses.MedicalRecord;
import com.safetynet.alerts.mainclasses.medicalreccordclasses.MedicalRecordService;
import com.safetynet.alerts.mainclasses.repositories.MedicalRecordRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;


import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MedicalRecordTest {
    @Autowired
    private MockMvc mockMvc;
    @Mock
    private MedicalRecordRepository medicalRecordRepository;
    @InjectMocks
    private MedicalRecordService medicalRecordService;
    @Test
    void testMedicalRecord() {
        // Arrange
        String firstName = "John";
        String lastName = "Doe";
        String birthdate = "01/01/1990"; // January 1, 1990
        ArrayList<String> medications = new ArrayList<>();
        medications.add("Medication A");
        medications.add("Medication B");
        ArrayList<String> allergies = new ArrayList<>();
        allergies.add("Allergy A");
        allergies.add("Allergy B");

        // Act
        MedicalRecord medicalRecord = new MedicalRecord(firstName, lastName, birthdate, medications, allergies);

        // Assert
        assertEquals(firstName, medicalRecord.getFirstName());
        assertEquals(lastName, medicalRecord.getLastName());
        assertEquals(birthdate, medicalRecord.getBirthdate());
        assertEquals(medications, medicalRecord.getMedications());
        assertEquals(allergies, medicalRecord.getAllergies());
        assertEquals(Period.between(LocalDate.parse("01/01/1990", DateTimeFormatter.ofPattern("MM/dd/yyyy")), LocalDate.now()).getYears(), medicalRecord.getAge());
    }
    @Test
    public void testAddNewMedicalRecord() {
        // Arrange
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setFirstName("John");
        medicalRecord.setLastName("Doe");
        medicalRecord.setBirthdate("01/01/1980");
        when(medicalRecordRepository.findByFirstNameAndLastNameAndBirthdate("John", "Doe", "01/01/1980")).thenReturn(Optional.empty());

        // Act
        medicalRecordService.addNewMedicalRecord(medicalRecord);

        // Assert
        verify(medicalRecordRepository, times(1)).save(medicalRecord);
    }

    @Test
    public void testAddNewMedicalRecord_AlreadyExists() {
        // Arrange
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setFirstName("John");
        medicalRecord.setLastName("Doe");
        medicalRecord.setBirthdate("01/01/1980");
        when(medicalRecordRepository.findByFirstNameAndLastNameAndBirthdate("John", "Doe", "01/01/1980")).thenReturn(Optional.of(medicalRecord));

        // Act
        medicalRecordService.addNewMedicalRecord(medicalRecord);

        // Assert
        // Verify that save method is not called
        verify(medicalRecordRepository, never()).save(any());
    }

    @Test
    public void testDeleteMedicalRecord() {
        // Arrange
        String firstName = "John";
        String lastName = "Doe";
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setId(1L);
        List<MedicalRecord> medicalRecords = new ArrayList<>();
        medicalRecords.add(medicalRecord);
        when(medicalRecordRepository.findByFirstNameAndLastName(firstName, lastName)).thenReturn(medicalRecords);
        doNothing().when(medicalRecordRepository).deleteById(anyLong());

        // Act
        medicalRecordService.deleteMedicalRecord(firstName, lastName);

        // Assert
        verify(medicalRecordRepository, times(1)).findByFirstNameAndLastName(firstName, lastName);
        verify(medicalRecordRepository, times(1)).deleteById(medicalRecord.getId());
    }

    @Test
    public void testDeleteMedicalRecord_NotExists() {
        // Arrange
        String firstName = "Jane";
        String lastName = "Doe";
        List<MedicalRecord> medicalRecords = new ArrayList<>();
        when(medicalRecordRepository.findByFirstNameAndLastName(firstName, lastName)).thenReturn(medicalRecords);

        // Act
        medicalRecordService.deleteMedicalRecord(firstName, lastName);

        // Assert
        verify(medicalRecordRepository, times(1)).findByFirstNameAndLastName(firstName, lastName);
        verify(medicalRecordRepository, never()).deleteById(anyLong());
    }

    @Test
    public void testUpdateMedicalRecord() {
        // Arrange
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setFirstName("John");
        medicalRecord.setLastName("Doe");
        medicalRecord.setBirthdate("01/01/1980");
        List<String> medications = new ArrayList<>();
        medications.add("Med1");
        List<String> allergies = new ArrayList<>();
        allergies.add("Allergy1");
        medicalRecord.setMedications(medications);
        medicalRecord.setAllergies(allergies);
        List<MedicalRecord> medicalRecords = new ArrayList<>();
        medicalRecords.add(medicalRecord);
        when(medicalRecordRepository.findByFirstNameAndLastName("John", "Doe")).thenReturn(medicalRecords);

        // Mock the behavior of save method
        when(medicalRecordRepository.save(any(MedicalRecord.class))).thenReturn(medicalRecord);

        // Act
        medicalRecordService.updateMedicalRecord(medicalRecord);

        // Assert
        verify(medicalRecordRepository, times(1)).findByFirstNameAndLastName("John", "Doe");
        verify(medicalRecordRepository, times(1)).save(any(MedicalRecord.class));
    }

    @Test
    public void testUpdateMedicalRecord_NotFound() {
        // Arrange
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setFirstName("Jane");
        medicalRecord.setLastName("Doe");
        medicalRecord.setBirthdate("01/01/1980");
        List<String> medications = new ArrayList<>();
        medications.add("Med1");
        List<String> allergies = new ArrayList<>();
        allergies.add("Allergy1");
        medicalRecord.setMedications(medications);
        medicalRecord.setAllergies(allergies);
        when(medicalRecordRepository.findByFirstNameAndLastName("Jane", "Doe")).thenReturn(new ArrayList<>());

        // Act & Assert
        assertThrows(EntityNotFoundException.class, () -> medicalRecordService.updateMedicalRecord(medicalRecord));
    }
}

