package com.garage.garage.services;

import com.garage.garage.entities.Garage;
import com.garage.garage.entities.Message;
import com.garage.garage.entities.Vehicle;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface VehicleService {
    //----------------get methods-----------
    //get vehicle by id
    Vehicle getVehicle(int vehicleId);

    //get list of all vehicle
    List<Vehicle> getAllVehicles();

    // get list of vehicles by status
    List<Vehicle> getVehiclesByStatus(String vehicleStatus);

    //get list of vehicles by type
    List<Vehicle> getVehiclesByType(String vehicleType);

    //get List of vehicles by garage
    List<Vehicle> getVehiclesByGarage(int garageId);

    //---------------save and update methods--------------

    //add new vehicle by registerNo and type
    ResponseEntity<Message> addVehicle(String garageName, String registerNo, String vehicleType);

    //update vehicle by id
    ResponseEntity<Message> updateVehicle(int vehicleId);

    //---------------delete methods ------------

    // delete vehicle by id
    ResponseEntity<Message> deleteVehicle(int vehicleId);


}
