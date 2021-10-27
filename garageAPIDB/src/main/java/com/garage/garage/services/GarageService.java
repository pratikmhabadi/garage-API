package com.garage.garage.services;

import com.garage.garage.entities.Garage;
import com.garage.garage.entities.Vehicle;

import java.util.List;

public interface GarageService {
    //-----------------get Methods---------
    //get garage by Id
    Garage getGarage(int garageId);

    List<Garage> getAllGarages();

    Garage addGarage(String garageName, String garageCity, String garageState);


}
