package com.example.demo.model.json;

import com.example.demo.model.json.gregorian.Date;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Data1 {
    private Timings timings;
    private Date date;
}
