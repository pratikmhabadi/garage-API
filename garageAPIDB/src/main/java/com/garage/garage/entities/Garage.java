package com.garage.garage.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Garage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int garageId;
    private String garageName;
    private String garageCity;
    private String garageState;

    @OneToMany(mappedBy = "garage")
    private List<Vehicle> vehicles;

    public Garage(String garageName, String garageCity, String garageState) {
        this.garageName = garageName;
        this.garageCity = garageCity;
        this.garageState = garageState;
    }

    public Garage() {
        super();
    }

    public int getGarageId() {
        return garageId;
    }

    public void setGarageId(int garageId) {
        this.garageId = garageId;
    }

    public String getGarageName() {
        return garageName;
    }

    public void setGarageName(String garageName) {
        this.garageName = garageName;
    }

    public String getGarageCity() {
        return garageCity;
    }

    public void setGarageCity(String garageCity) {
        this.garageCity = garageCity;
    }

    public String getGarageState() {
        return garageState;
    }

    public void setGarageState(String garageState) {
        this.garageState = garageState;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }
}
