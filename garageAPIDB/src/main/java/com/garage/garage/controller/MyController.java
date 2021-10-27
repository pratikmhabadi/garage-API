package com.garage.garage.controller;

import com.garage.garage.entities.Garage;
import com.garage.garage.entities.Vehicle;
import com.garage.garage.services.GarageService;
import com.garage.garage.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MyController {

    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private GarageService garageService;

    //------------GetMappings-------------------------
    // get vehicle by ID
    @GetMapping("/vehicle/{vehicleId}")
    public Vehicle getVehicle(@PathVariable String vehicleId) {
        return this.vehicleService.getVehicle(Integer.parseInt(vehicleId));
    }

    //get garage by Id
    @GetMapping("/garage/{garageId}")
    public Garage getGarage(@PathVariable String garageId){
        return this.garageService.getGarage(Integer.parseInt(garageId));
    }

    //get All Vehicles
    @GetMapping("/vehicles")
    public List<Vehicle> getAllVehicles() {
        return this.vehicleService.getAllVehicles();
    }

    //get All Vehicles
    @GetMapping("/garages")
    public List<Garage> getAllGarages() {
        return this.garageService.getAllGarages();
    }

    //get vehicles by repaired status
    @GetMapping("/vehicles/repaired")
    public List<Vehicle> getAllRepairedVehicles() {
        return this.vehicleService.getVehiclesByStatus("repaired");
    }

    //get vehicles by repairing status
    @GetMapping("/vehicles/repairing")
    public List<Vehicle> getAllRepairingVehicles() {
        return this.vehicleService.getVehiclesByStatus("repairing");
    }

    // get all bikes
    @GetMapping("/vehicles/bike")
    public List<Vehicle> getAllBike() {
        return this.vehicleService.getVehiclesByType("bike");
    }

    // get all cars
    @GetMapping("/vehicles/car")
    public List<Vehicle> getAllCars() {
        return this.vehicleService.getVehiclesByType("car");
    }

    //get vehicles by garage ID
    @GetMapping("garage/{garageId}/vehicles")
    public List<Vehicle> getVehiclesByGarage(@PathVariable String garageId){
        return this.vehicleService.getVehiclesByGarage(Integer.parseInt(garageId));
    }

    //--------------------PostMappings----------------------------
    //add new vehicle
    @PostMapping("/garage/{garageId}/vehicles/{registerNo}/{vehicleType}")
    public Vehicle addVehicle(@PathVariable String garageId,@PathVariable String registerNo, @PathVariable String vehicleType) {
        return this.vehicleService.addVehicle(Integer.parseInt(garageId),registerNo, vehicleType);
    }

    @PostMapping("/garage/{garageName}/{garageCity}/{garageState}")
    public  Garage addGarage(@PathVariable String garageName,@PathVariable String garageCity,@PathVariable String garageState){
        return this.garageService.addGarage(garageName,garageCity,garageState);
    }

    //------------------PutMappings--------------------------
    //update vehicle by status by ID
    @PutMapping("/vehicle/{vehicleId}/{interval}")
    public Vehicle updateVehicle(@PathVariable String vehicleId,@PathVariable String interval) {
        return this.vehicleService.updateVehicle(Integer.parseInt(vehicleId),Integer.parseInt(interval));
    }

    //---------------DeleteMappings--------------------
    //delete vehicle by ID
    @DeleteMapping("/vehicle/{vehicleId}")
    public void deleteVehicle(@PathVariable String vehicleId) {
        this.vehicleService.deleteVehicle(Integer.parseInt(vehicleId));
    }
}
