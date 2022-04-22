package com.example.fly4AviationApp.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class Weight {
    int cargoWeight;
    int baggageWeight;
    int allWeight;

    public Weight(int baggageWeightInKg, int cargoWeightInKg) {
        this.baggageWeight = baggageWeightInKg;
        this.cargoWeight = cargoWeightInKg;
        this.allWeight = cargoWeightInKg + baggageWeightInKg;
    }
}
