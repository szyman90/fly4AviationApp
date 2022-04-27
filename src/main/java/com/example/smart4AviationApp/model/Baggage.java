package com.example.smart4AviationApp.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "baggage", schema ="public")
@AllArgsConstructor
public class Baggage extends LoadItem{

}
