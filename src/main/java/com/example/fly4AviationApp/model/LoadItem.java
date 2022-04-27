package com.example.fly4AviationApp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@MappedSuperclass
@Getter
@Setter
public abstract class LoadItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "itemId")
    private int itemId;
    @Min(1)
    @Max(999)
    @Column (name = "weight", nullable = false)
    private int weight;
    @Column (name = "weight_unit", nullable = false, length = 2)
    @Pattern(regexp = "(?i)(\\W|^)(lb|kg)(\\W|$)")
    private String weightUnit;
    @Column (name = "pieces", nullable = false)
    @Min(1)
    @Max(999)
    private int pieces;
}
