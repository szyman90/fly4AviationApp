package com.example.fly4AviationApp.service;

import com.example.fly4AviationApp.model.FlightItems;
import com.example.fly4AviationApp.model.dto.FlightItemsDto;
import com.example.fly4AviationApp.repositories.BaggageRepository;
import com.example.fly4AviationApp.repositories.CargoRepository;
import com.example.fly4AviationApp.repositories.FlightItemsRepository;
import com.example.fly4AviationApp.repositories.FlightRepository;
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
