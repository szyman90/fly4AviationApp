package com.example.fly4AviationApp.repositories;

import com.example.fly4AviationApp.model.Flight;
import com.example.fly4AviationApp.model.FlightItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightItemsRepository extends JpaRepository<FlightItems, Integer> {

    FlightItems save(Flight FlightItems);

/*    @Query(value = "SELECT sum(weight) FROM Baggage WHERE Flight.flightNumber =: flightNumber")
    int sumBaggageByFlight(@Param("flightNumber")int flightNumber);

    @Query(value = "SELECT sum(weight) FROM Cargo WHERE Flight.flightNumber =: flightNumber")
    int sumCargoByFlight(int flightNumber);*/
}