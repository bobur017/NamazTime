package com.example.demo.model.weather.hourly;

import com.example.demo.model.weather.current.Weathers;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;



@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Hourly {
    private Timestamp dt;
    private double feels_like;
    private Timestamp sunrise;
    private Timestamp sunset;
    private double temp;
    private double pressure;
    private double humidity;
    private double wind_speed;
    private double clouds;
    private List<Weathers> weather;
    private double pop;
}
