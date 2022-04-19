package com.example.fly4AviationApp.controller;

import com.example.fly4AviationApp.model.Flight;
import com.example.fly4AviationApp.repositories.FlightItemsRepository;
import com.example.fly4AviationApp.repositories.FlightRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
class FlightController {
    private final FlightRepository repository;
    private final FlightItemsRepository flightItemsRepository;
    private static final Logger logger = LoggerFactory.getLogger(FlightController.class);
    FlightController(final FlightRepository repository, FlightItemsRepository flightItemsRepository) {
        this.repository = repository;
        this.flightItemsRepository = flightItemsRepository;
    }

/*    @GetMapping( "/flight/weight?{flightNumber}")
    ResponseEntity<String> getFlight(@PathVariable int flightNumber) {
        int baggageSum = flightItemsRepository.sumBaggageByFlight(flightNumber);
        int cargoSum = flightItemsRepository.sumCargoByFlight(flightNumber);
        logger.info("get flight weight");
        return ResponseEntity.ok(baggageSum +", " + cargoSum + ", " + baggageSum+cargoSum);
    }*/

    @PostMapping("/flight")
    ResponseEntity<?> createFlight(@RequestBody Flight toCreate) {
        Flight result = repository.save(toCreate);
        return ResponseEntity.created(URI.create("/" + result.getFlightId())).body(result);
    }

}
