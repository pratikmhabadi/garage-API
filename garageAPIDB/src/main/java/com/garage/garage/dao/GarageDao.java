package com.garage.garage.dao;

import com.garage.garage.entities.Garage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GarageDao extends JpaRepository<Garage,Integer> {
}
