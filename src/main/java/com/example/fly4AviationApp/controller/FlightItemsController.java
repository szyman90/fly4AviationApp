package com.example.fly4AviationApp.controller;

import com.example.fly4AviationApp.model.FlightItems;
import com.example.fly4AviationApp.repositories.FlightItemsRepository;
import com.example.fly4AviationApp.repositories.FlightRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
@RestController
//@AllArgsConstructor
public class FlightItemsController {
    private final FlightItemsRepository flightItemsRepository;
    private final FlightRepository flightRepository;

    public FlightItemsController(FlightItemsRepository flightItemsRepository, FlightRepository flightRepository) {
        this.flightItemsRepository = flightItemsRepository;
        this.flightRepository = flightRepository;
    }

    @PostMapping("/items/{flightId}")
    ResponseEntity<?> createFlightItemsForFlight(@RequestBody FlightItems toCreate, @PathVariable int flightId) {
        toCreate.setFlight(flightRepository.getByFlightId(flightId));
        FlightItems result = flightItemsRepository.save(toCreate);
        return ResponseEntity.created(URI.create("/" + result.getFlight().getFlightId())).body(result);
    }
/*    @PostMapping("/items")
    ResponseEntity<?> createFlightItemsForFlight(@RequestBody FlightItems toCreate) { //TODO moze dto
        FlightItems result = flightItemsRepository.save(toCreate);
        return ResponseEntity.created(URI.create("/" + result.getFlight().getFlightId())).body(result);
    }*/
}
