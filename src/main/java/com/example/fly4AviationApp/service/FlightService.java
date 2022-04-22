package com.example.fly4AviationApp.service;

import com.example.fly4AviationApp.model.*;
import com.example.fly4AviationApp.repositories.BaggageRepository;
import com.example.fly4AviationApp.repositories.CargoRepository;
import com.example.fly4AviationApp.repositories.FlightItemsRepository;
import com.example.fly4AviationApp.repositories.FlightRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
@AllArgsConstructor
public class FlightService {
        FlightItemsRepository flightItemsRepository;
        FlightRepository flightRepository;
        BaggageRepository baggageRepository;
        CargoRepository cargoRepository;

    public Weight getWeight(int flightNumber/*, Timestamp departureDate*/) {
        Flight flight = flightRepository.getByFlightNumber(flightNumber/*,departureDate*/);
        int flightItemsIdByFlightId = flightItemsRepository.getFlightItemsIdByFlightId(flight.getFlightId());
        List<Baggage> baggageList= baggageRepository.getAllByFlightItemsId(flightItemsIdByFlightId);
        List<Cargo> cargoList= cargoRepository.getAllByFlightItemsId(flightItemsIdByFlightId);

        return calculateWeight(baggageList, cargoList);
    }

    private Weight calculateWeight(List<Baggage> baggageList, List<Cargo> cargoList) {
        return new Weight(baggageWeight(baggageList), cargoWeight(cargoList));
    }

    private int baggageWeight(List<Baggage> baggageList) {
        int baggageWeightInKg = 0;
        for (Baggage baggage : baggageList) {
            if ((baggage.getWeightUnit()).equals("kg"))
                baggageWeightInKg += baggage.getWeight();
            else
                baggageWeightInKg += baggage.getWeight() * 0.45359237;
        }
        return baggageWeightInKg;
    }
    private int cargoWeight(List<Cargo> cargoList) {
        int cargoWeightInKg = 0;
        for (Cargo cargo : cargoList) {
            if ((cargo.getWeightUnit()).equals("kg"))
                cargoWeightInKg += cargo.getWeight();
            else
                cargoWeightInKg += cargo.getWeight() * 0.45359237;
        }
        return cargoWeightInKg;
    }
}
