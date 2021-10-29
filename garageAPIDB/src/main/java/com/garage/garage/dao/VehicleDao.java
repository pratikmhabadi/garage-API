package com.garage.garage.dao;

import com.garage.garage.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleDao extends JpaRepository<Vehicle, Integer> {

    @Query("SELECT v FROM Vehicle v WHERE v.vehicleStatus = :s")
    List<Vehicle> findByStatus(@Param("s") String vehicleStatus);

    @Query("SELECT v FROM Vehicle v WHERE v.vehicleStatus = :s")
    List<Vehicle> findByType( @Param("s") String vehicleStatus);

    @Query("SELECT v FROM Vehicle v left join v.garage g WHERE g.garageId =:i" )
    List<Vehicle> findByGarage( @Param("i") int garageId);

    @Query("SELECT v From Vehicle v WHERE v.vehicleStatus=:s and v.garage.garageId =:i" )
    List<Vehicle> findRepairingByGarage( @Param("i") int garageId, @Param("s") String vehicleStatus);
}
