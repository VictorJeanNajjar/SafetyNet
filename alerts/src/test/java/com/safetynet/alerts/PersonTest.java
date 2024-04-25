package com.safetynet.alerts;

import com.safetynet.alerts.mainclasses.Person;
import com.safetynet.alerts.mainclasses.personclasses.PersonService;
import com.safetynet.alerts.mainclasses.repositories.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PersonTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
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
    public void testAddNewPerson() {
        // Arrange
        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Doe");
        when(personRepository.findByFirstNameAndLastNameAndAddressAndCityAndZipAndPhoneAndEmail(any(), any(), any(), any(), any(), any(), any())).thenReturn(Optional.empty());

        // Act
        personService.addNewPerson(person);

        // Assert
        verify(personRepository, times(1)).save(person);
    }

    @Test
    public void testDeletePerson() {
        // Arrange
        String firstName = "John";
        String lastName = "Doe";
        Person person = new Person();
        person.setId(1L);
        List<Person> persons = new ArrayList<>();
        persons.add(person);
        when(personRepository.findByFirstNameAndLastName(firstName, lastName)).thenReturn(persons);
        doNothing().when(personRepository).deleteById(anyLong());

        // Act
        personService.deletePerson(firstName, lastName);

        // Assert
        verify(personRepository, times(1)).findByFirstNameAndLastName(firstName, lastName);
        verify(personRepository, times(1)).deleteById(person.getId());
    }

    @Test
    public void testUpdatePerson() {
        // Arrange
        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Doe");
        List<Person> persons = new ArrayList<>();
        persons.add(person);
        when(personRepository.findByFirstNameAndLastName(any(), any())).thenReturn(persons);
        when(personRepository.save(any(Person.class))).thenReturn(person);

        // Act
        personService.updatePerson(person);

        // Assert
        verify(personRepository, times(1)).findByFirstNameAndLastName(any(), any());
        verify(personRepository, times(1)).save(any(Person.class));
    }
}

