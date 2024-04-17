package com.safetynet.alerts;

import com.safetynet.alerts.datasortingandreading.*;
import com.safetynet.alerts.mainclasses.Firestation;
import com.safetynet.alerts.mainclasses.MedicalRecord;
import com.safetynet.alerts.mainclasses.Person;
import com.safetynet.alerts.mainclasses.repositories.FirestationRepository;
import com.safetynet.alerts.mainclasses.repositories.MedicalRecordRepository;
import com.safetynet.alerts.mainclasses.repositories.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class DataSortingTest {

    @Mock
    private FirestationRepository firestationRepository;

    @Mock
    private PersonRepository personRepository;
    @Mock
    private MedicalRecordRepository medicalRecordRepository;

    @InjectMocks
    private PhoneNumbersByFirestationService phoneNumbersByFirestationService;
    @InjectMocks
    private PeopleByNameService peopleByNameService;
    @InjectMocks
    private PeopleByFireStationService peopleByFireStationService;
    @InjectMocks
    private PeopleAndFireStationNumberByAddressService peopleAndFireStationNumberByAddressService;
    @InjectMocks
    private HouseholdsByListOfFirestationsService householdsByListOfFirestationsService;
    @InjectMocks
    private EmailsByCityService emailsByCityService;
    @InjectMocks
    private  ChildrenByAddressService childrenByAddressService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testExtractPhoneNumbersByFirestation_StationExists() {
        // Arrange
        String stationNumber = "1";
        List<Firestation> firestationList = new ArrayList<>();
        Firestation firestation = new Firestation();
        firestation.setAddress("123 Main St");
        firestationList.add(firestation);
        List<Person> personList = new ArrayList<>();
        Person person1 = new Person();
        person1.setPhone("111-222-3333");
        Person person2 = new Person();
        person2.setPhone("444-555-6666");
        personList.add(person1);
        personList.add(person2);
        when(firestationRepository.findByStation(stationNumber)).thenReturn(firestationList);
        when(personRepository.findByAddress(firestation.getAddress())).thenReturn(personList);

        // Act
        List<String> result = phoneNumbersByFirestationService.extractPhoneNumbersByFirestation(stationNumber);

        // Assert
        assertEquals(2, result.size());
        assertEquals("111-222-3333", result.get(0));
        assertEquals("444-555-6666", result.get(1));
    }

    @Test
    public void testExtractPhoneNumbersByFirestation_StationDoesNotExist() {
        // Arrange
        String stationNumber = "10";
        List<Firestation> firestationList = new ArrayList<>();
        when(firestationRepository.findByStation(stationNumber)).thenReturn(firestationList);

        // Act
        List<String> result = phoneNumbersByFirestationService.extractPhoneNumbersByFirestation(stationNumber);

        // Assert
        assertEquals(0, result.size());
    }
    @Test
    public void testExtractPeopleByName_PersonExistsWithMedicalRecord() {
        // Arrange
        String firstName = "John";
        String lastName = "Doe";
        List<Person> personList = new ArrayList<>();
        Person person = new Person();
        person.setId(1L);
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setAddress("123 Main St");
        person.setEmail("john.doe@example.com");
        personList.add(person);
        when(personRepository.findByFirstNameAndLastName(firstName, lastName)).thenReturn(personList);

        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setBirthdate("01/01/2001");
        medicalRecord.setMedications(List.of("Med1", "Med2"));
        medicalRecord.setAllergies(List.of("Allergy1", "Allergy2"));
        when(medicalRecordRepository.findById(person.getId())).thenReturn(Optional.of(medicalRecord));

        // Act
        List<String> result = peopleByNameService.extractPeopleByName(firstName, lastName);

        // Assert
        assertEquals(2, result.size());
        assertEquals("Person: John Doe address: 123 Main St email: john.doe@example.com", result.get(0));
        assertEquals("age: 23 medication(s): [Med1, Med2] allergies: [Allergy1, Allergy2]", result.get(1));
    }

    @Test
    public void testExtractPeopleByName_PersonExistsWithoutMedicalRecord() {
        // Arrange
        String firstName = "Jane";
        String lastName = "Smith";
        List<Person> personList = new ArrayList<>();
        Person person = new Person();
        person.setId(2L);
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setAddress("456 Oak St");
        person.setEmail("jane.smith@example.com");
        personList.add(person);
        when(personRepository.findByFirstNameAndLastName(firstName, lastName)).thenReturn(personList);

        when(medicalRecordRepository.findById(person.getId())).thenReturn(Optional.empty());

        // Act
        List<String> result = peopleByNameService.extractPeopleByName(firstName, lastName);

        // Assert
        assertEquals(1, result.size());
        assertEquals("Person: Jane Smith address: 456 Oak St email: jane.smith@example.com", result.get(0));
    }

    @Test
    public void testExtractPeopleByName_PersonDoesNotExist() {
        // Arrange
        String firstName = "Nonexistent";
        String lastName = "Person";
        when(personRepository.findByFirstNameAndLastName(firstName, lastName)).thenReturn(new ArrayList<>());

        // Act
        List<String> result = peopleByNameService.extractPeopleByName(firstName, lastName);

        // Assert
        assertEquals(0, result.size());
    }
    @Test
    public void testExtractPeopleByFireStation_FireStationExistsWithPeopleAndMedicalRecords() {
        // Arrange
        String stationNumber = "1";
        List<Firestation> firestationList = new ArrayList<>();
        Firestation firestation = new Firestation();
        firestation.setAddress("123 Main St");
        firestationList.add(firestation);
        when(firestationRepository.findByStation(stationNumber)).thenReturn(firestationList);

        List<Person> personList = new ArrayList<>();
        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Doe");
        person.setAddress("123 Main St");
        person.setPhone("123-456-7890");
        personList.add(person);
        when(personRepository.findByAddress(firestation.getAddress())).thenReturn(personList);

        List<MedicalRecord> medicalRecordList = new ArrayList<>();
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setBirthdate("01/01/2001");
        medicalRecordList.add(medicalRecord);
        when(medicalRecordRepository.findByFirstNameAndLastName(person.getFirstName(), person.getLastName())).thenReturn(medicalRecordList);

        // Act
        List<String> result = peopleByFireStationService.extractPeopleByFireStation(stationNumber);

        // Assert
        assertEquals(3, result.size());
        assertEquals("John Doe address: 123 Main St phone number: 123-456-7890", result.get(0));
        assertEquals("NB of adults is 1", result.get(1));
        assertEquals("NB of children is 0", result.get(2));
    }

    @Test
    public void testExtractPeopleAndFireStationNumberByAddressService_AddressExistsWithAssociatedFireStationsAndPeople() {
        // Arrange
        String address = "123 Main St";

        // Mock firestationRepository
        List<Firestation> firestationList = new ArrayList<>();
        Firestation firestation = new Firestation();
        firestation.setStation("1");
        firestationList.add(firestation);
        when(firestationRepository.findByAddress(address)).thenReturn(firestationList);

        // Mock personRepository
        List<Person> personList = new ArrayList<>();
        Person person = new Person();
        person.setId(1L);
        person.setFirstName("John");
        person.setLastName("Doe");
        person.setAddress(address);
        person.setPhone("123-456-7890");
        personList.add(person);
        when(personRepository.findByAddress(address)).thenReturn(personList);

        // Mock medicalRecordRepository
        MedicalRecord medicalRecord = new MedicalRecord(); // Create a non-null MedicalRecord object
        medicalRecord.setBirthdate("01/01/2001"); // Set some values to ensure it's not null
        Optional<MedicalRecord> medicalRecordOptional = Optional.of(medicalRecord);
        when(medicalRecordRepository.findById(person.getId())).thenReturn(medicalRecordOptional);

        // Act
        List<String> result = peopleAndFireStationNumberByAddressService.extractPeopleAndFireStationNumberByAddressService(address);

        // Assert
        assertEquals(3, result.size());
        assertEquals("Station number: 1 is responsible for 123 Main St", result.get(0));
        assertEquals("People at this address: ", result.get(1));
        assertEquals("Name: John Doe Phone Number: 123-456-7890 Age: 23 Medication: [] Allergies: []", result.get(2));
    }
    @Test
    public void testExtractHouseholdsByListOfFirestationsService() {
        // Arrange
        List<Integer> firestationsNumbers = List.of(1, 2);
        List<Firestation> firestations1 = new ArrayList<>();
        Firestation firestation1 = new Firestation();
        firestation1.setStation("1");
        firestation1.setAddress("123 Main St");
        firestations1.add(firestation1);

        List<Person> residents1 = new ArrayList<>();
        Person person1 = new Person();
        person1.setId(1L);
        person1.setFirstName("John");
        person1.setLastName("Doe");
        person1.setPhone("123-456-7890");
        residents1.add(person1);

        MedicalRecord medicalRecord1 = new MedicalRecord();
        medicalRecord1.setId(1L);
        medicalRecord1.setBirthdate("01/01/2001");
        medicalRecord1.setMedications(List.of("Med1"));
        medicalRecord1.setAllergies(List.of("Allergy1"));

        when(firestationRepository.findByStation("1")).thenReturn(firestations1);
        when(personRepository.findByAddress("123 Main St")).thenReturn(residents1);
        when(medicalRecordRepository.findById(1L)).thenReturn(Optional.of(medicalRecord1));

        // Act
        List<String> result = householdsByListOfFirestationsService.extractHouseholdsByListOfFirestationsService(firestationsNumbers);

        // Assert
        assertEquals(3, result.size());
        assertEquals("FireStation 1 in charge of: ", result.get(0));
        assertEquals("Residents of 123 Main St: ", result.get(1));
        assertEquals("Person Info:   Name: John Doe PhoneNumber: 123-456-7890 Age: 23 Medications: [Med1] Allergies: [Allergy1]", result.get(2));
    }
    @Test
    public void testExtractEmailAddresses() {
        // Mock data
        String city = "TestCity";
        Person person1 = new Person();
        person1.setEmail("person1@example.com");
        Person person2 = new Person();
        person2.setEmail("person2@example.com");
        List<Person> personList = Arrays.asList(person1, person2);

        // Mock the behavior of personRepository
        when(personRepository.findByCity(city)).thenReturn(personList);

        // Invoke the service method
        Set<String> result = emailsByCityService.extractEmailAddresses(city);

        // Verify the result
        Set<String> expectedEmails = new HashSet<>(Arrays.asList("person1@example.com", "person2@example.com"));
        assertEquals(expectedEmails, result);
    }
    @Test
    public void testExtractChildrenByAddress_WithChildren() {
        // Arrange
        String address = "123 Test St";
        List<Person> personList = new ArrayList<>();
        Person child1 = new Person();
        child1.setFirstName("John");
        child1.setLastName("Doe");
        personList.add(child1);
        List<MedicalRecord> medicalRecords = new ArrayList<>();
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setBirthdate("04/16/2014");
        medicalRecords.add(medicalRecord);
        when(personRepository.findByAddress(address)).thenReturn(personList);
        when(medicalRecordRepository.findByFirstNameAndLastName("John", "Doe")).thenReturn(medicalRecords);

        // Act
        List<String> result = childrenByAddressService.extractChildrenByAddress(address);

        // Assert
        assertEquals(3, result.size());
        assertEquals("Children at this address:", result.get(0));
        assertEquals("John Doe, Age: 10", result.get(1));
        assertEquals("No adults at this address:", result.get(2));
    }
}