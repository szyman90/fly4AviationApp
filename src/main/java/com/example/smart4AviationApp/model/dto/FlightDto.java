package com.example.smart4AviationApp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.*;
import java.sql.Timestamp;
@Getter
@AllArgsConstructor
public class FlightDto {
    private int flightId;
    @Min(1000)
    @Max(9999)
    private int flightNumber;
    @Size(min =3, max = 3)
    @NotBlank
    private String departureAirportIATACode;
    @Size(min =3, max = 3)
    @NotBlank
    private String arrivalAirportIATACode;
    @NotNull
    private Timestamp departureDate;
}
