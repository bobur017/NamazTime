package com.example.demo.model.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Timings {
    @JsonProperty("Fajr")
    private String fajr;
    @JsonProperty("Sunrise")
    private String sunrise;
    @JsonProperty("Dhuhr")
    private String dhuhr;
    @JsonProperty("Asr")
    private String asr;
    @JsonProperty("Sunset")
    private String sunset;
    @JsonProperty("Maghrib")
    private String maghrib;
    @JsonProperty("Isha")
    private String isha;
    @JsonProperty("Imsak")
    private String imsak;
    @JsonProperty("Midnight")
    private String midnight;
}
