package com.garage.garage.services;

import com.garage.garage.dao.GarageDao;
import com.garage.garage.dao.VehicleDao;
import com.garage.garage.entities.Garage;
import com.garage.garage.entities.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class ServiceImplementation implements VehicleService, GarageService {
    @Autowired
    private VehicleDao vehicleDao;
    @Autowired
    private GarageDao garageDao;

    //----------vehicle operation---------------
    //--------------------get methods -----------------//
    //get vehicle by ID
    @Override
    public Vehicle getVehicle(int vehicleId) {
        return vehicleDao.findById(vehicleId).orElse(null);
    }

    //get All vehicles
    @Override
    public List<Vehicle> getAllVehicles() {
        return vehicleDao.findAll();
    }

    //get vehicles by status repairing/repaired
    @Override
    public List<Vehicle> getVehiclesByStatus(String vehicleStatus) {
        List<Vehicle> list = new ArrayList<>();
        for (Vehicle vehicle : vehicleDao.findAll()) {
            if (vehicle.getVehicleStatus().equals(vehicleStatus)) {
                list.add(vehicle);
            }
        }
        return list;
    }

    //get vehicles by type bike/car
    @Override
    public List<Vehicle> getVehiclesByType(String vehicleType) {
        List<Vehicle> list = new ArrayList<>();
        for (Vehicle vehicle : vehicleDao.findAll()) {
            if (vehicle.getVehicleType().equals(vehicleType)) {
                list.add(vehicle);
            }
        }
        return list;
    }

    //get vehicles by garageId
    @Override
    public List<Vehicle> getVehiclesByGarage(int garageId) {
        List<Vehicle> list = new ArrayList<>();
        for (Vehicle vehicle : vehicleDao.findAll()) {
            if (vehicle.getGarage().getGarageId() == garageId) {
                list.add(vehicle);
            }
        }
        return list;
    }


    //-------------------save and update methods------------
    //Add new vehicles by register number and vehicle type (car/bike)
    @Override
    public Vehicle addVehicle(int garageId, String registerNo, String vehicleType) {
        if (getGarage(garageId) != null) {
            if (getVehiclesByStatus("repairing").size() < 2) {
                String type = vehicleType.toLowerCase(Locale.ROOT);
                Vehicle vehicle = new Vehicle();
                vehicle.setRegisterNo(registerNo.toUpperCase(Locale.ROOT));
                vehicle.setVehicleStatus("repairing");
                vehicle.setGarage(garageDao.getById(garageId));
                switch (type) {
                    case "bike":
                        vehicle.setVehicleType(type);
                        vehicle.setCost(200);
                        return vehicleDao.save(vehicle);

                    case "car":
                        vehicle.setVehicleType(type);
                        vehicle.setCost(500);
                        return vehicleDao.save(vehicle);

                    default:
                        return null;
                }
            }
        }
        return null;
    }

    //update vehicles status from repairing to repaired manually
    @Override
    public Vehicle updateVehicle(int vehicleId, int interval) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        Vehicle vehicle = getVehicle(vehicleId);
        if (vehicle.getVehicleStatus().equals("repairing") ){
            vehicle.setVehicleStatus("repaired");
            vehicle.setDate(formatter.format(date));
        }
        return vehicleDao.save(vehicle);
    }

    //---------------delete methods-------------
    //delete vehicle by ID
    @Override
    public void deleteVehicle(int vehicleId) {
        vehicleDao.deleteById(vehicleId);
    }


    //-----------------Garage Operation------------
    //------------get methods---------------
    //get garage by Id
    @Override
    public Garage getGarage(int garageId) {
        return garageDao.findById(garageId).orElse(null);
    }

    //get all garages
    @Override
    public List<Garage> getAllGarages() {
        return garageDao.findAll();
    }

    //--------- Save and Update method------------
    @Override
    public Garage addGarage(String garageName, String garageCity, String garageState) {
        Garage garage = new Garage(garageName, garageCity, garageState);
        return garageDao.save(garage);
    }


}

