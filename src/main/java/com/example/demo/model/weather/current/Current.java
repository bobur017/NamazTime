package com.example.demo.model.weather.current;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Current {
    private Timestamp dt;
    private Timestamp sunrise;
    private Timestamp sunset;
    private double temp;
    private double pressure;
    private double humidity;
    private double wind_speed;
    private double feels_like;
    private ArrayList<Weathers> weather;
}
