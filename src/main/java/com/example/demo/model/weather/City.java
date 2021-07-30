package com.example.demo.model.weather;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public enum City {
    TASHKENT("Toshkent",69.21627,41.264648,0),
    ANDIJAN("Andijon",72.333328,40.75,-11),
    FAGANA("Fargona",71.25,40.5,-10),
    NAVAIY("Navoiy",65.379173,40.084438,19),
    SIRDARYA("Sirdaryo",68.75,40.5,7),
    BUXARA("Buxoro",64.428612,39.774719,21),
    SAMARKAND("Samarqand",66.583328,39.583328,9),
    KARAKALPAK("Qoraqalpog'iston",58.829861,44.216358,42),
    NAMANGAN("Namangan",71.672569,40.998299,-10),
    QASHQADARYO("Qashqadaryo",66,38.583328,10),
    SURXONDARYO("Surxondaryo",67.5,38,9),
    XORAZM("Xorazm",60.5,41.5,35),
    JIZZAX("Jizzax",67.842216,40.115829,6);



    private String name1;
    private double lon;
    private double lat;
    private int cityNumber;

}
