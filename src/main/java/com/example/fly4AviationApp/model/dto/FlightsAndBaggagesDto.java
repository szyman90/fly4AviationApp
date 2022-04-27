package com.example.fly4AviationApp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class FlightsAndBaggagesDto {
    int numberOfDepartingFlights;
    int numberOfArrivingFlights;
    int numberOfDepartingBaggage;
    int numberOfArrivingBaggage;
}