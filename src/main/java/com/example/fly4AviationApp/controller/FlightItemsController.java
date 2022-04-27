package com.example.fly4AviationApp.controller;

import com.example.fly4AviationApp.model.FlightItems;
import com.example.fly4AviationApp.model.dto.FlightItemsDto;
import com.example.fly4AviationApp.repositories.FlightItemsRepository;
import com.example.fly4AviationApp.repositories.FlightRepository;
import com.example.fly4AviationApp.service.FlightItemsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
@RestController
public class FlightItemsController {
    private final FlightItemsService flightItemsService;
    private static final Logger logger = LoggerFactory.getLogger(FlightController.class);

    public FlightItemsController(FlightItemsService flightItemsService) {
        this.flightItemsService = flightItemsService;
    }

    @PostMapping("/items")
    ResponseEntity<?> createFlightItemsForFlight(@Valid @RequestBody FlightItemsDto toCreateDto) {
        FlightItems result = flightItemsService.createNewFlightItems(toCreateDto);
        logger.info("create new flight items");
        return ResponseEntity.created(URI.create("/" + result.getFlightItemsId())).body(result);
    }
}
