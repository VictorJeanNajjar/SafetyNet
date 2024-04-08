package com.safetynet.alerts.mainclasses;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.ArrayList;

@Entity
public class Firestation {
    @Id
    @GeneratedValue
    private Long id;
    public String address;
    public String station;

    public Firestation(String address,
                       String station) {
        this.address = address;
        this.station = station;
    }

    public Firestation() {

    }
    @Override
    public String toString() {
        return "FireStation{" +
                "address='" + address + '\'' +
                ", station='" + station + '\'' +
                '}';
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }
}
