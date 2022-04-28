package com.example.smart4AviationApp.service;

import com.example.smart4AviationApp.model.*;
import com.example.smart4AviationApp.model.dto.FlightDto;
import com.example.smart4AviationApp.model.dto.FlightsAndBaggagesDto;
import com.example.smart4AviationApp.model.dto.WeightDto;
import com.example.smart4AviationApp.repositories.BaggageRepository;
import com.example.smart4AviationApp.repositories.CargoRepository;
import com.example.smart4AviationApp.repositories.FlightItemsRepository;
import com.example.smart4AviationApp.repositories.FlightRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class FlightService {
    final static double LB_TO_KG_CONSTANT = 0.45359237;
    FlightItemsRepository flightItemsRepository;
    FlightRepository flightRepository;
    BaggageRepository baggageRepository;
    CargoRepository cargoRepository;

    public WeightDto getWeightInKg(int flightNumber, LocalDateTime departureDate) {

        Timestamp timestampDepartureDate = Timestamp.valueOf(departureDate);
        Flight flight = flightRepository.getByFlightNumberAndDepartureDate(flightNumber, timestampDepartureDate);
        int flightItemsIdByFlightId = flightItemsRepository.getFlightItemsIdByFlightId(flight.getFlightId());
        List<Baggage> baggageList = baggageRepository.getAllByFlightItemsId(flightItemsIdByFlightId);
        List<Cargo> cargoList = cargoRepository.getAllByFlightItemsId(flightItemsIdByFlightId);

        return calculateWeight(baggageList, cargoList);
    }

    private WeightDto calculateWeight(List<Baggage> baggageList, List<Cargo> cargoList) {
        return new WeightDto(baggageWeight(baggageList), cargoWeight(cargoList));
    }

    private int baggageWeight(List<Baggage> baggageList) {
        int baggageWeightInKg = 0;
        for (Baggage baggage : baggageList) {
            if ((baggage.getWeightUnit()).equals("kg"))
                baggageWeightInKg += baggage.getWeight();
            else
                baggageWeightInKg += baggage.getWeight() * LB_TO_KG_CONSTANT;
        }
        return baggageWeightInKg;
    }

    private int cargoWeight(List<Cargo> cargoList) {
        int cargoWeightInKg = 0;
        for (Cargo cargo : cargoList) {
            if ((cargo.getWeightUnit()).equals("kg"))
                cargoWeightInKg += cargo.getWeight();
            else
                cargoWeightInKg += cargo.getWeight() * LB_TO_KG_CONSTANT;
        }
        return cargoWeightInKg;
    }

    public FlightsAndBaggagesDto getFlightsAndBaggages(String iataCode, LocalDate departureDate) {

        List<Flight> departureFlightList = getDepartureFlights(iataCode, departureDate);

        List<Flight> arrivalFlightList = getArrivalFlights(iataCode, departureDate);

        int numberOfArrivalFlights = arrivalFlightList.size();
        int numberOfDepartureFlights = departureFlightList.size();

        List<Integer> departureFlightItemsId = getFlightItemsId(departureFlightList);
        List<Integer> arrivalFlightItemsId = getFlightItemsId(arrivalFlightList);

        int departureBaggagePieces = getBaggagePieces(departureFlightItemsId);
        int arrivalBaggagePieces = getBaggagePieces(arrivalFlightItemsId);

        return new FlightsAndBaggagesDto(numberOfDepartureFlights, numberOfArrivalFlights,
                departureBaggagePieces, arrivalBaggagePieces);
    }

    private List<Flight> getArrivalFlights(String iataCode, LocalDate departureDate) {
        List<Flight> arrivalFlightListWithoutDateCheck = flightRepository.getAllByArrivalAirportIATACode(iataCode);
        List<Flight> arrivalFlightList = new ArrayList<>();
        for (Flight flight : arrivalFlightListWithoutDateCheck) {
            LocalDate date = flight.getDepartureDate().toLocalDateTime().toLocalDate();
            if (date != departureDate)
                arrivalFlightList.add(flight);
        }
        return arrivalFlightList;
    }

    private List<Flight> getDepartureFlights(String iataCode, LocalDate departureDate) {
        List<Flight> departureFlightListWithoutDateCheck = flightRepository.getAllByDepartureAirportIATACode(iataCode);
        List<Flight> departureFlightList = new ArrayList<>();
        for (Flight flight : departureFlightListWithoutDateCheck) {
            LocalDate date = flight.getDepartureDate().toLocalDateTime().toLocalDate();
            if (date != departureDate)
                departureFlightList.add(flight);
        }
        return departureFlightList;
    }

    private int getBaggagePieces(List<Integer> flightItemsId) {
        int baggagePieces = 0;
        for (Integer integer : flightItemsId) {
            List<Baggage> baggageList = baggageRepository.getBaggageByFlightItemsId(integer);
            baggagePieces += sumPiecesFromList(baggageList);
        }
        return baggagePieces;
    }

    private int sumPiecesFromList(List<Baggage> baggageList) {
        int sum = 0;
        for (Baggage baggage : baggageList) {
            sum += baggage.getPieces();
        }
        return sum;
    }

    private List<Integer> getFlightItemsId(List<Flight> flightList) {
        List<Integer> flightItemsId = new ArrayList<>();
        for (Flight flight : flightList) {
            flightItemsId.add(flightItemsRepository.getFlightItemsIdByFlightId(flight.getFlightId()));
        }
        return flightItemsId;
    }

    public Flight createNewFlight(FlightDto toCreateDto) {
        Flight flightToCreate = new Flight();
        flightToCreate.setFlightNumber(toCreateDto.getFlightNumber());
        flightToCreate.setArrivalAirportIATACode(toCreateDto.getArrivalAirportIATACode());
        flightToCreate.setDepartureAirportIATACode(toCreateDto.getDepartureAirportIATACode());
        flightToCreate.setDepartureDate(toCreateDto.getDepartureDate());
        flightRepository.save(flightToCreate);
        return flightToCreate;
    }
}
