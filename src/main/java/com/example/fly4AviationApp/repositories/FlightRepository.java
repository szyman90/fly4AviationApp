package com.example.fly4AviationApp.repositories;

import com.example.fly4AviationApp.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {
   Flight getByFlightId(int flightId);

   Flight getByFlightNumberAndDepartureDate(int flightNumber, Timestamp departureDate);
}
