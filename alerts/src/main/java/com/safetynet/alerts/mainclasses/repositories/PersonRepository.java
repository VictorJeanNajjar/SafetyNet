package com.safetynet.alerts.mainclasses.repositories;

import com.safetynet.alerts.mainclasses.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {
    public Optional<Person> findByFirstNameAndLastNameAndAddressAndCityAndZipAndPhoneAndEmail(String firstName,
                                                                                              String lastName,
                                                                                              String address,
                                                                                              String city,
                                                                                              String zip,
                                                                                              String phone,
                                                                                              String email);
    public List<Person> findByCity(String city);
    public List<Person> findByAddress (String address);
    public List <Person> findByFirstNameAndLastName (String firstName,
                                              String lastName);
}
