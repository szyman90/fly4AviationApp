package com.example.smart4AviationApp.model.dto;

import com.example.smart4AviationApp.model.Baggage;
import com.example.smart4AviationApp.model.Cargo;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@Getter
public class FlightItemsDto {
    private int flightItemsId;
    private int flightId;
    private List<@Valid Cargo> cargoList;
    private List<@Valid Baggage> baggageList;
}
