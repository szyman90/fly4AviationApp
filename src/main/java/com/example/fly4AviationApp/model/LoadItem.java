package com.example.fly4AviationApp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@MappedSuperclass
@Getter
@Setter
public abstract class LoadItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "itemId")
    private int flightId;
    @Column (name = "weight", nullable = false)
    private int weight;
    @Column (name = "weight_unit", nullable = false, length = 2)
    private String weightUnit;
    @Column (name = "pieces", nullable = false)
    private int pieces;

    public abstract  ItemType getLoadItemType();
}
