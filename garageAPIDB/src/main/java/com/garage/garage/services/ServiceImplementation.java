package com.garage.garage.services;

import com.garage.garage.dao.GarageDao;
import com.garage.garage.dao.VehicleDao;
import com.garage.garage.entities.Garage;
import com.garage.garage.entities.Message;
import com.garage.garage.entities.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.Duration;
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
        return vehicleDao.findByStatus(vehicleStatus);
    }

    //get vehicles by type bike/car
    @Override
    public List<Vehicle> getVehiclesByType(String vehicleType) {
        return vehicleDao.findByType(vehicleType);
    }

    //get vehicles by garageId
    @Override
    public List<Vehicle> getVehiclesByGarage(int garageId) {
        return vehicleDao.findByGarage(garageId);
    }


    //-------------------save and update methods------------
    //Add new vehicles by register number and vehicle type (car/bike)
    @Override
    public Message addVehicle(String garageName, String registerNo, String vehicleType) {
        try {
            Garage g = garageDao.findByName(garageName.trim().toLowerCase(Locale.ROOT)).get(0);
            if ((getGarageById(g.getGarageId())) != null) {
                if (vehicleDao.findRepairingByGarage(g.getGarageId(), "repairing").size() < 2) {
                    String type = vehicleType.toLowerCase(Locale.ROOT);
                    LocalDateTime now = LocalDateTime.now();
                    switch (type) {
                        case "bike":
                            Vehicle vehicle1 = new Vehicle(registerNo.toUpperCase(Locale.ROOT), "repairing", type, 200, now, g);
                            vehicleDao.save(vehicle1);
                            break;

                        case "car":
                            Vehicle vehicle2 = new Vehicle(registerNo.toUpperCase(Locale.ROOT), "repairing", type, 500, now, g);
                            vehicleDao.save(vehicle2);
                            break;

                        default:
                            return new Message("Change vehicle type", true);

                    }
                    return new Message("New Vehicle Added Successfully", true);
                }
                return new Message("Currently Garage is full , try after some time", true);
            }
            return new Message("This Garage is not present", true);
        } catch (Exception e) {
            e.printStackTrace();
            return new Message("Exception occurred due to invalid input", false);
        }
    }


    //update vehicles status from repairing to repaired manually
    @Override
    public Message updateVehicle(int vehicleId) {
        try {
            LocalDateTime date = LocalDateTime.now();
            Vehicle vehicle = getVehicle(vehicleId);
            if (vehicle.getVehicleStatus().equals("repairing")) {
                vehicle.setVehicleStatus("repaired");
                vehicle.setOutTime(date);
                vehicleDao.save(vehicle);
            }
            return new Message("Vehicle is repaired", true);
        } catch (Exception e) {
            e.printStackTrace();
            return new Message("Some Error Occurred", false);
        }
    }

    // update vehicles status from repairing to repaired automatically
    @Scheduled(fixedDelay = 10000)
    void autoUpdateToRepaired() throws InterruptedException {
        try {
            List<Vehicle> list = vehicleDao.findByStatus("repairing");
            if (!list.isEmpty()) {
                for (Vehicle vehicle : list) {
                    LocalDateTime now = LocalDateTime.now();
                    LocalDateTime inTime = vehicle.getInTime();
                    long diff = Duration.between(inTime,now).getSeconds();
                    System.out.println(diff);
                    if (diff >= 30) {
                        vehicle.setOutTime(now);
                        vehicle.setVehicleStatus("repaired");
                        vehicleDao.save(vehicle);
                    }
                }
            } else {
                System.out.println("List is empty");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Some Error Occurred");
        }
    }


    //---------------delete methods-------------
    //delete vehicle by ID
    @Override
    public Message deleteVehicle(int vehicleId) {
        try {
            vehicleDao.deleteById(vehicleId);
            return new Message("Vehicle is deleted", true);
        } catch (Exception e) {
            e.printStackTrace();
            return new Message("Some Error Occurred", true);
        }
    }


    //-----------------Garage Operation------------
    //------------get methods---------------
    //get garage by Id
    @Override
    public Garage getGarageById(int garageId) {
        return garageDao.findById(garageId).orElse(null);
    }

    //get garage by Name
    @Override
    public List<Garage> getGarageByName(String garageName) {
        return garageDao.findByName(garageName);
    }

    //get all garages
    @Override
    public List<Garage> getAllGarages() {
        return garageDao.findAll();
    }


    //--------- Save and Update method------------
    @Override
    public Message addGarage(String garageName, String garageCity, String garageState) {
        try {
            Garage garage = new Garage(garageName.trim().toLowerCase(Locale.ROOT), garageCity.trim().toLowerCase(Locale.ROOT), garageState.trim().toLowerCase(Locale.ROOT));
            garageDao.save(garage);
            return new Message("New Garage Added Successfully.", true);
        } catch (Exception e) {
            e.printStackTrace();
            return new Message("Some Error Occurred", true);
        }
    }


}

