package com.example.fly4AviationApp.model.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WeightDto {
    int cargoWeight;
    int baggageWeight;
    int allWeight;

    public WeightDto(int baggageWeightInKg, int cargoWeightInKg) {
        this.baggageWeight = baggageWeightInKg;
        this.cargoWeight = cargoWeightInKg;
        this.allWeight = cargoWeightInKg + baggageWeightInKg;
    }
}
