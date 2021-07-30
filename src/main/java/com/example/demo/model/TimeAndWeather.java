package com.example.demo.model;

import com.example.demo.model.json.GetApi;
import com.example.demo.model.weather.Weather;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TimeAndWeather {
    private GetApi getApi;
    private Weather weather;
}
