package com.garage.garage.services;

import com.garage.garage.entities.Garage;
import com.garage.garage.entities.Message;
import com.garage.garage.entities.Vehicle;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GarageService {
    //-----------------get Methods---------
    //get garage by Id
    Garage getGarageById(int garageId);

    //get garages by name
    List<Garage> getGarageByName(String garageName);

    //get List of garages
    List<Garage> getAllGarages();

    //Add new Garage
    ResponseEntity<Message> addGarage(String garageName, String garageCity, String garageState);


}
