package com.example.smart4AviationApp.controller;

import com.example.smart4AviationApp.model.Flight;
import com.example.smart4AviationApp.model.dto.FlightDto;
import com.example.smart4AviationApp.model.dto.FlightsAndBaggagesDto;
import com.example.smart4AviationApp.model.dto.WeightDto;
import com.example.smart4AviationApp.service.FlightService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.Date;


@RestController
class FlightController {
    private final FlightService flightService;
    private static final Logger logger = LoggerFactory.getLogger(FlightController.class);

    FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/flight/weight")
    ResponseEntity<WeightDto> getWeightInKg(@RequestParam("flightNumber") int flightNumber,
                                            @RequestParam("departureDate")
                                     @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date departureDate) {
        logger.info("get flight weight");
        return ResponseEntity.ok(flightService.getWeightInKg(flightNumber, departureDate));
    }

    @GetMapping("/flight/flightsAndNumberOfBaggages")
    ResponseEntity<FlightsAndBaggagesDto> getFlightsAndBaggages(@RequestParam("IATANumber") String iataCode,
                                                                @RequestParam("departureDate")
                                                             @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate departureDate) {
        logger.info("get flights and number of baggage for that airport");
        return ResponseEntity.ok(flightService.getFlightsAndBaggages(iataCode, departureDate));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/flight")
    ResponseEntity<?> createFlight(@Valid @RequestBody FlightDto toCreateDto) {
        Flight result = flightService.createNewFlight(toCreateDto);
        logger.info("create new flight");
        return ResponseEntity.created(URI.create("/" + result.getFlightId())).body(result);
    }

}
