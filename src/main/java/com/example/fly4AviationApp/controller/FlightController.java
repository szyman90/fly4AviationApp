package com.example.fly4AviationApp.controller;

import com.example.fly4AviationApp.model.Flight;
import com.example.fly4AviationApp.model.Weight;
import com.example.fly4AviationApp.repositories.FlightRepository;
import com.example.fly4AviationApp.service.FlightService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Date;
import java.sql.Timestamp;


@RestController
class FlightController {
    private final FlightRepository repository;
    private final FlightService flightService;
    private static final Logger logger = LoggerFactory.getLogger(FlightController.class);
    FlightController(final FlightRepository repository, FlightService flightService) {
        this.repository = repository;
        this.flightService = flightService;
    }

    @GetMapping( "/flight/weight")
    ResponseEntity<Weight> getFlight(@RequestParam("flightNumber") int flightNumber,
                                     @RequestParam("departureDate")
                                     @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date departureDate) {
        logger.info("get flight weight");
        return ResponseEntity.ok(flightService.getWeight(flightNumber, departureDate));
    }

    @PostMapping("/flight")
    ResponseEntity<?> createFlight(@RequestBody Flight toCreate) {
        Flight result = repository.save(toCreate);
        return ResponseEntity.created(URI.create("/" + result.getFlightId())).body(result);
    }

}
