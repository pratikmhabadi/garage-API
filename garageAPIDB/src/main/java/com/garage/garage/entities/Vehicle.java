package com.garage.garage.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;


@Entity
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int vehicleId;
    private String registerNo;
    private String vehicleStatus;
    private String vehicleType;
    private double cost;
    private String date;

    @ManyToOne
    @JoinColumn(name="garage_id")
    @JsonBackReference
    private Garage garage;

    public Vehicle(int vehicleId, String registerNo, String vehicleStatus, String vehicleType, double cost) {
        this.vehicleId = vehicleId;
        this.registerNo = registerNo;
        this.vehicleStatus = vehicleStatus;
        this.vehicleType = vehicleType;
        this.cost = cost;
    }


    public Vehicle() {
        super();
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getRegisterNo() {
        return registerNo;
    }

    public void setRegisterNo(String registerNo) {
        this.registerNo = registerNo;
    }

    public String getVehicleStatus() {
        return vehicleStatus;
    }

    public void setVehicleStatus(String vehicleStatus) {
        this.vehicleStatus = vehicleStatus;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Garage getGarage() {
        return garage;
    }

    public void setGarage(Garage garage) {
        this.garage = garage;
    }
}
