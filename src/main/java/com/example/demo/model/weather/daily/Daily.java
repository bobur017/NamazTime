package com.example.demo.model.weather.daily;

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

public class Daily {
    private Timestamp dt;
    private Timestamp sunrise;
    private Timestamp sunset;
    private Temp temp;
    private Feels_like feels_like;
    private double pressure;
    private double humidity;
    private double wind_speed;
    private List<Weathers> weather;
    private double clouds;
    private double pop;

}
