package com.example.demo.model.weather;

import com.example.demo.model.weather.current.Current;
import com.example.demo.model.weather.daily.Daily;
import com.example.demo.model.weather.hourly.Hourly;
import com.example.demo.model.weather.minutely.Minutely;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather {
    private String timezone;
    private double lat;
    private double lon;
    private Current current;
    private ArrayList<Minutely> minutely;
    private ArrayList<Hourly> hourly;
    private ArrayList<Daily> daily;
}
