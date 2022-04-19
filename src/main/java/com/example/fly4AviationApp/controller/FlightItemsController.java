package com.example.fly4AviationApp.controller;

import com.example.fly4AviationApp.model.FlightItems;
import com.example.fly4AviationApp.repositories.FlightItemsRepository;
import com.example.fly4AviationApp.repositories.FlightRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
@RestController
public class FlightItemsController {
    private final FlightItemsRepository repository;
    private final FlightRepository flightRepository;
    private static final Logger logger = LoggerFactory.getLogger(FlightController.class);
    FlightItemsController(final FlightItemsRepository repository, FlightRepository flightRepository) {
        this.repository = repository;
        this.flightRepository = flightRepository;
    }

    @GetMapping( "/items")
    ResponseEntity<String> getFlightItems() {
        logger.info("getFlight");
        return ResponseEntity.ok("Get Flight");
    }

    @PostMapping("/items/{flightId}")
    ResponseEntity<?> createFlightItemsForFlight(@RequestBody FlightItems toCreate, @PathVariable int flightId) {
        toCreate.setFlight(flightRepository.getByFlightId(flightId));
        FlightItems result = repository.save(toCreate);
        return ResponseEntity.created(URI.create("/" + result.getFlight().getFlightId())).body(result);
    }
    @PostMapping("/items")
    ResponseEntity<?> createFlightItemsForFlight(@RequestBody FlightItems toCreate) {
        FlightItems result = repository.save(toCreate);
        return ResponseEntity.created(URI.create("/" + result.getFlight().getFlightId())).body(result);
    }
}
