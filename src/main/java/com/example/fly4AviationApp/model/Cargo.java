package com.example.fly4AviationApp.model;

import lombok.AllArgsConstructor;
import javax.persistence.*;

@Entity
@Table(name = "cargo", schema ="public")
@AllArgsConstructor
public class Cargo extends LoadItem{

}
