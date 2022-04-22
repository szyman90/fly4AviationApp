package com.example.fly4AviationApp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "flight_items", schema ="public")
@Getter
@Setter
@NoArgsConstructor
public class FlightItems {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column (name = "flight_items_id")
        private int flightItemsId;
        @OneToOne
        @JoinColumn(name = "flight", referencedColumnName = "flight_id")
        private Flight Flight;
        @OneToMany(cascade=CascadeType.ALL)
        @JoinColumn(name = "flight_items_id")
        private List<Cargo> cargoList; //TODO Set zamiast List bo lazy
        @OneToMany(cascade=CascadeType.ALL)
        @JoinColumn(name = "flight_items_id")
        private List<Baggage> baggageList; //TODO Set zamiast List bo lazy

    }
