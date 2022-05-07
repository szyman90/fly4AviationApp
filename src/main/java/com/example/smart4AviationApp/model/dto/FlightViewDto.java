package com.example.smart4AviationApp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FlightViewDto {
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
    private String departureDate;
}
