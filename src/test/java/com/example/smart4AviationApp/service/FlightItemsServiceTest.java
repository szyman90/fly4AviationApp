package com.example.smart4AviationApp.service;

import com.example.smart4AviationApp.model.Baggage;
import com.example.smart4AviationApp.model.Cargo;
import com.example.smart4AviationApp.model.Flight;
import com.example.smart4AviationApp.model.FlightItems;
import com.example.smart4AviationApp.model.dto.FlightItemsDto;
import com.example.smart4AviationApp.repositories.FlightRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class FlightItemsServiceTest {
    LocalDateTime FLIGHT_DATE = LocalDateTime.now();
    @Mock
    FlightRepository flightRepository;
    @InjectMocks
    private FlightItemsService flightItemsService;
    Flight flight = new Flight();
    FlightItems flightItems = new FlightItems();
    @BeforeEach
    void beforeEach() {
        flight.setFlightId(0);
        flight.setFlightNumber(3890);
        flight.setArrivalAirportIATACode("TPT");
        flight.setDepartureAirportIATACode("PXT");
        flight.setDepartureDate(Timestamp.valueOf(FLIGHT_DATE));

        flightItems.setFlight(flight);
        flightItems.setFlightItemsId(0);

        Baggage baggage0 = new Baggage();
        baggage0.setItemId(0);
        baggage0.setPieces(400);
        baggage0.setWeight(100);
        baggage0.setWeightUnit("kg");

        Baggage baggage1 = new Baggage();
        baggage1.setItemId(1);
        baggage1.setPieces(100);
        baggage1.setWeight(50);
        baggage1.setWeightUnit("kg");

        Baggage baggage2 = new Baggage();
        baggage2.setItemId(2);
        baggage2.setPieces(200);
        baggage2.setWeight(150);
        baggage2.setWeightUnit("kg");

        Baggage baggage3 = new Baggage();
        baggage3.setItemId(3);
        baggage3.setPieces(1000);
        baggage3.setWeight(100);
        baggage3.setWeightUnit("kg");

        List<Baggage> baggageList = List.of(baggage0, baggage1, baggage2, baggage3);
        flightItems.setBaggageList(baggageList);

        Cargo cargo0 = new Cargo();
        cargo0.setItemId(4);
        cargo0.setPieces(100);
        cargo0.setWeight(200);
        cargo0.setWeightUnit("kg");

        Cargo cargo1 = new Cargo();
        cargo1.setItemId(5);
        cargo1.setPieces(250);
        cargo1.setWeight(500);
        cargo1.setWeightUnit("kg");

        Cargo cargo2 = new Cargo();
        cargo2.setItemId(6);
        cargo2.setPieces(200);
        cargo2.setWeight(300);
        cargo2.setWeightUnit("kg");

        List<Cargo> cargoList = List.of(cargo0, cargo1, cargo2);
        flightItems.setCargoList(cargoList);
    }

    @Test
    void createNewFlightItemsTest() {
        when(flightRepository.getByFlightId(flight.getFlightId())).thenReturn(flight);
        FlightItemsDto flightItemsDto = new FlightItemsDto(flightItems.getFlightItemsId(),
                flightItems.getFlight().getFlightId(), flightItems.getCargoList(), flightItems.getBaggageList());
        ResponseEntity<?> responseEntity = flightItemsService.createNewFlightItems(flightItemsDto);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    void createNewFlightItemsWithoutFlightTest() {
        when(flightRepository.getByFlightId(anyInt())).thenReturn(null);
        FlightItemsDto flightItemsDto = new FlightItemsDto(flightItems.getFlightItemsId(),
                flightItems.getFlight().getFlightId(), flightItems.getCargoList(), flightItems.getBaggageList());
        ResponseEntity<?> responseEntity = flightItemsService.createNewFlightItems(flightItemsDto);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }
}