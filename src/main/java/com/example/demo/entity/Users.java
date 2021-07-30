package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@ToString
public class Users {

    @Id
    private Long id;
    private Integer position;
    private String userName;
    private Integer cityNumber;
    private String cityName;
    private Double lat;
    private Double lon;
}
