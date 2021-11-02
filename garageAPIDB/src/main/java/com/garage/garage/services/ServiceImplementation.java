package com.garage.garage.services;

import com.garage.garage.dao.GarageDao;
import com.garage.garage.dao.VehicleDao;
import com.garage.garage.entities.Garage;
import com.garage.garage.entities.Message;
import com.garage.garage.entities.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.Duration;
import java.time.LocalDateTime;
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
    public ResponseEntity<Message> addVehicle(String garageName, String registerNo, String vehicleType) {
        try {
            if (!garageDao.findByName(garageName.trim().toLowerCase(Locale.ROOT)).isEmpty()) {
                Garage g = garageDao.findByName(garageName.trim().toLowerCase(Locale.ROOT)).get(0);
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
                            return new ResponseEntity<Message>(new Message("400", "Validation Error", "Invalid Vehicle Type"), HttpStatus.BAD_REQUEST);

                    }
                    return new ResponseEntity<Message>(new Message("200", "New vehicle Added Successfully"), HttpStatus.OK);
                }
                return new ResponseEntity<Message>(new Message("200", "Currently Garage is full , try after some time"), HttpStatus.OK);
            }
            return new ResponseEntity<Message>(new Message("404", "This Garage is not present", "Not Found"), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            //return new ResponseEntity<Message> (new Message("500","Something Went Wrong"),HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<Message>(new Message("500", "Something Went Wrong"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //update vehicles status from repairing to repaired manually
    @Override
    public ResponseEntity<Message> updateVehicle(int vehicleId) {
        try {
            LocalDateTime date = LocalDateTime.now();
            Vehicle vehicle = vehicleDao.getById(vehicleId);
            if (vehicle != null) {
                if (vehicle.getVehicleStatus().equals("repairing")) {
                    vehicle.setVehicleStatus("repaired");
                    vehicle.setOutTime(date);
                    vehicleDao.save(vehicle);
                    return new ResponseEntity<Message>(new Message("200", "Vehicle Id " + vehicleId + " is Repaired"), HttpStatus.OK);
                } else {
                    return new ResponseEntity<Message>(new Message("200", "Vehicle Id " + vehicleId + " is already repaired"), HttpStatus.OK);
                }
            } else {
                return new ResponseEntity<Message>(new Message("404", "Vehicle Id " + vehicleId + " is Not Found", "Not Found"), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            //return new ResponseEntity<Message>(new Message("500", "Something Went Wrong"), HttpStatus.INTERNAL_SERVER_ERROR);
            throw new EntityNotFoundException();
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
                    long diff = Duration.between(inTime, now).getSeconds();
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
            System.out.println("Something Went Wrong");
        }
    }


    //---------------delete methods-------------
    //delete vehicle by ID
    @Override
    public ResponseEntity<Message> deleteVehicle(int vehicleId) {
        try {
            vehicleDao.deleteById(vehicleId);
            return new ResponseEntity<Message>(new Message("200", "Vehicle ID" + vehicleId + " Deleted Successfully"), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            // return new ResponseEntity<Message>(new Message("404", "Match Not Found"), HttpStatus.NOT_FOUND);
            throw new EntityNotFoundException();
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
    public ResponseEntity<Message> addGarage(String garageName, String garageCity, String garageState) {
        try {
            Garage garage = new Garage(garageName.trim().toLowerCase(Locale.ROOT), garageCity.trim().toLowerCase(Locale.ROOT), garageState.trim().toLowerCase(Locale.ROOT));
            garageDao.save(garage);
            return new ResponseEntity<Message>(new Message("200", "New Garage Added Successfully"), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Message>(new Message("500", "Something Went Wrong"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}

