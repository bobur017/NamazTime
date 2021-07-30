package com.example.demo.model.json.gregorian;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Gregorian {
    private String date;
    private String format;
    private String day;
    private Weekday weekday;
    private Month month;
    private String year;
}
