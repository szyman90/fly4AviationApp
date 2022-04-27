package com.example.smart4AviationApp.repositories;

import com.example.smart4AviationApp.model.FlightItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightItemsRepository extends JpaRepository<FlightItems, Integer> {

    @Query(value = "select flight_items_id from  flight_items where flight =:flightId", nativeQuery = true)
    int getFlightItemsIdByFlightId(@Param("flightId")int flightId);
}