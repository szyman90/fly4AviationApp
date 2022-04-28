package com.example.smart4AviationApp.model.dto;

import com.example.smart4AviationApp.model.Baggage;
import com.example.smart4AviationApp.model.Cargo;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@Getter
public class FlightItemsDto {
    private int flightItemsId;
    @NotNull
    private Integer flightId;
    private List<@Valid Cargo> cargoList;
    private List<@Valid Baggage> baggageList;

}
