package com.garage.garage.dao;

import com.garage.garage.entities.Garage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GarageDao extends JpaRepository<Garage,Integer> {

    @Query("SELECT g FROM Garage g WHERE g.garageName = :n")
    List<Garage> findByName(@Param("n")String name);
}
