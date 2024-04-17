package com.safetynet.alerts;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.safetynet.alerts.mainclasses.Person;
import com.safetynet.alerts.mainclasses.MedicalRecord;
import com.safetynet.alerts.mainclasses.Firestation;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

class MainClassesTest {

    @Test
    void testPerson() {
        // Arrange
        String firstName = "John";
        String lastName = "Doe";
        String address = "123 Main St";
        String city = "Anytown";
        String zip = "12345";
        String phone = "555-1234";
        String email = "john.doe@example.com";

        // Act
        Person person = new Person(firstName, lastName, address, city, zip, phone, email);

        // Assert
        assertEquals(firstName, person.getFirstName());
        assertEquals(lastName, person.getLastName());
        assertEquals(address, person.getAddress());
        assertEquals(city, person.getCity());
        assertEquals(zip, person.getZip());
        assertEquals(phone, person.getPhone());
        assertEquals(email, person.getEmail());
    }

    @Test
    void testFirestation() {
        // Arrange
        String address = "123 Main St";
        String station = "Station 1";

        // Act
        Firestation firestation = new Firestation(address, station);

        // Assert
        assertEquals(address, firestation.getAddress());
        assertEquals(station, firestation.getStation());
    }
}
