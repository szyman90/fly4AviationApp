package com.example.fly4AviationApp.model;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
        private int flightNumber;
        @Column (name = "departure_airport_iata_code", nullable = false)
        private String departureAirportIATACode;
        @Column (name = "arrival_airport_iata_code", nullable = false)
        private String arrivalAirportIATACode;
        @Column (name = "departure_date", nullable = false)
        private Timestamp departureDate;
        //TODO poprawienie formatu
}
