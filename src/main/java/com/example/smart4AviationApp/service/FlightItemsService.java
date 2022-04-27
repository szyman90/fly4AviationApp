package com.example.smart4AviationApp.service;

import com.example.smart4AviationApp.model.FlightItems;
import com.example.smart4AviationApp.model.dto.FlightItemsDto;
import com.example.smart4AviationApp.repositories.BaggageRepository;
import com.example.smart4AviationApp.repositories.CargoRepository;
import com.example.smart4AviationApp.repositories.FlightItemsRepository;
import com.example.smart4AviationApp.repositories.FlightRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FlightItemsService {
    FlightItemsRepository flightItemsRepository;
    FlightRepository flightRepository;
    BaggageRepository baggageRepository;
    CargoRepository cargoRepository;

    public FlightItems createNewFlightItems(FlightItemsDto toCreateDto) {
        FlightItems flightItemsToCreate = new FlightItems();
        flightItemsToCreate.setFlight(flightRepository.getByFlightId(toCreateDto.getFlightId()));
        flightItemsToCreate.setBaggageList(toCreateDto.getBaggageList());
        flightItemsToCreate.setCargoList(toCreateDto.getCargoList());
        flightItemsRepository.save(flightItemsToCreate);
        return flightItemsToCreate;
    }
}
