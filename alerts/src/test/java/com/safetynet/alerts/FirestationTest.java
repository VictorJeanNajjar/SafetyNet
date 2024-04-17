package com.safetynet.alerts;

import com.safetynet.alerts.mainclasses.Firestation;
import com.safetynet.alerts.mainclasses.firestationclasses.FirestationService;
import com.safetynet.alerts.mainclasses.repositories.FirestationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class FirestationTest {

    @Mock
    private FirestationRepository firestationRepository;

    @InjectMocks
    private FirestationService firestationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddNewFirestation() {
        // Arrange
        Firestation firestation = new Firestation();
        firestation.setAddress("123 Main St");
        firestation.setStation("1");
        when(firestationRepository.findByAddressAndStation(any(), any())).thenReturn(new ArrayList<>());

        // Act
        firestationService.addNewFirestation(firestation);

        // Assert
        verify(firestationRepository, times(1)).save(firestation);
    }

    @Test
    public void testDeleteFirestation() {
        // Arrange
        String address = "123 Main St";
        String station = "1";
        Firestation firestation = new Firestation();
        firestation.setId(1L);
        List<Firestation> firestations = new ArrayList<>();
        firestations.add(firestation);
        when(firestationRepository.findByAddressAndStation(address, station)).thenReturn(firestations);
        doNothing().when(firestationRepository).deleteById(anyLong());

        // Act
        firestationService.deleteFirestation(address, station);

        // Assert
        verify(firestationRepository, times(1)).findByAddressAndStation(address, station);
        verify(firestationRepository, times(1)).deleteById(firestation.getId());
    }

    @Test
    public void testUpdateFirestations() {
        // Arrange
        Firestation firestation = new Firestation();
        firestation.setAddress("123 Main St");
        firestation.setStation("2");
        List<Firestation> firestationList = new ArrayList<>();
        firestationList.add(firestation);
        when(firestationRepository.findByAddress(any())).thenReturn(firestationList);
        when(firestationRepository.save(any(Firestation.class))).thenReturn(firestation);

        // Act
        firestationService.updateFirestations(firestation);

        // Assert
        verify(firestationRepository, times(1)).findByAddress(any());
        verify(firestationRepository, times(1)).save(any(Firestation.class));
    }
}
