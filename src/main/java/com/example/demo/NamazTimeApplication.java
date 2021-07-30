package com.example.demo;

import com.example.demo.model.weather.City;
import com.example.demo.service.Servise;
import com.example.demo.weatherService.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class NamazTimeApplication implements CommandLineRunner {

    @Autowired
    private  Servise servise;

    @Autowired
    private WeatherService weatherService;
    public static void main(String[] args) {
        SpringApplication.run(NamazTimeApplication.class, args);


    }

    @Override
    public void run(String... args) throws Exception {
        City city=City.ANDIJAN;
        System.out.println();
    }
}
