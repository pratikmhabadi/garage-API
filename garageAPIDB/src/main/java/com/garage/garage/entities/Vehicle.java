package com.garage.garage.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.time.LocalDateTime;
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
    private LocalDateTime inTime;
    private LocalDateTime outTime;


    @ManyToOne
    @JoinColumn(name="garage_id")
    @JsonBackReference
    private Garage garage;

    public Vehicle(String registerNo, String vehicleStatus, String vehicleType, double cost, LocalDateTime inTime, Garage garage) {
        this.registerNo = registerNo;
        this.vehicleStatus = vehicleStatus;
        this.vehicleType = vehicleType;
        this.cost = cost;
        this.inTime = inTime;
        this.garage = garage;
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

    public LocalDateTime getInTime() {
        return inTime;
    }

    public void setInTime(LocalDateTime inTime) {
        this.inTime = inTime;
    }

    public LocalDateTime getOutTime() {
        return outTime;
    }

    public void setOutTime(LocalDateTime outTime) {
        this.outTime = outTime;
    }

    public Garage getGarage() {
        return garage;
    }

    public void setGarage(Garage garage) {
        this.garage = garage;
    }
}
