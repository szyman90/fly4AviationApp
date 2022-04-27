package com.example.smart4AviationApp.model;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.sql.Timestamp;

@Entity
@Table(name = "flight", schema ="public")
@Getter
@Setter
@NoArgsConstructor
public class Flight {
        @Id
        @GeneratedValue (strategy = GenerationType.IDENTITY)
        @Column (name = "flight_id")
        private int flightId;
        @Column (name = "flight_number", nullable = false)
        @Min(1000)
        @Max(9999)
        private int flightNumber;
        @Column (name = "departure_airport_iata_code", nullable = false, length = 3)
        private String departureAirportIATACode;
        @Column (name = "arrival_airport_iata_code", nullable = false, length = 3)
        private String arrivalAirportIATACode;
        @Column (name = "departure_date", nullable = false)
        private Timestamp departureDate;
}
