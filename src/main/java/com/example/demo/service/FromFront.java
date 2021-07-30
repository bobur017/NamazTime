package com.example.demo.service;

import com.example.demo.Repository.UserRepository;
import com.example.demo.model.TimeAndWeather;
import com.example.demo.model.json.Data1;
import com.example.demo.model.json.GetApi;
import com.example.demo.model.json.Timings;
import com.example.demo.model.weather.City;
import com.example.demo.model.weather.Weather;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/api/users")
public class FromFront {


    @Autowired
    UserRepository userRepository;


    @ResponseBody
    @GetMapping("/getByCity/{minute}")
    public TimeAndWeather getDayTime(
            @PathVariable Integer minute
    ) throws IOException {
        double lat = 0;
        double lon = 0;

        TimeAndWeather timeAndWeather = new TimeAndWeather();
        if (minute == 0) {
            lat = City.TASHKENT.getLat();
            lon = City.TASHKENT.getLon();
        } else {
            for (City city : City.values()) {
                if (city.getCityNumber() == minute) {
                    lat = city.getLat();
                    lon = city.getLon();
                }
            }
        }

        int year = LocalDate.now().getYear();
        int month = LocalDate.now().getMonthValue();
        LocalDate localDate = LocalDate.now();
        localDate = localDate.plusMonths(1);
        int month2 = localDate.getMonthValue();
        String monthS = "";
        String monthS2 = "";
        if (month < 10 && month2 < 10) {
            monthS += "0";
            monthS += String.valueOf(month);
            monthS2 += "0";
            monthS2 += String.valueOf(month2);
        } else {
            monthS = String.valueOf(month);
            monthS2 = String.valueOf(month2);
        }
        URL url = new URL("https://api.aladhan.com/v1/calendarByCity?city=Tashkent&country=Uzbekistan%&method=2&latitudeAdjustmentMethod=3&school=1&month=" + monthS + "&year=" + year);
        ObjectMapper objectMapper = new ObjectMapper();
        GetApi getApi = objectMapper.readValue(url, GetApi.class);
        URL url2 = new URL("https://api.openweathermap.org/data/2.5/onecall?lat=" + lat + "&lon=" + lon + "&exclude=alerts&appid=cd5bc3a13b97ec73117b518561d9a4b2&lang=ru&units=metric");
        ObjectMapper objectMapper1 = new ObjectMapper();
        Weather weather = objectMapper1.readValue(url2, Weather.class);

        URL url1 = new URL("https://api.aladhan.com/v1/calendarByCity?city=Tashkent&country=Tashkent%&method=2&latitudeAdjustmentMethod=3&school=1&month=" + monthS2 + "&year=" + year);
        ObjectMapper objectMapper2 = new ObjectMapper();
        GetApi getApi11 = objectMapper2.readValue(url1, GetApi.class);
        ArrayList<Data1> data11 = getApi11.getData();
        ArrayList<Data1> data = getApi.getData();

        data.addAll(data11);
        getApi.setData(cityByMinute(data,minute));
        timeAndWeather.setGetApi(getApi);
        timeAndWeather.setWeather(weather);

        return timeAndWeather;
    }

    public ArrayList<Data1> cityByMinute(List<Data1> data, Integer minute){
        LocalDate localDate1 = LocalDate.now();

        for (Data1 data1 : data) {
            Timings timings= data1.getTimings();
                LocalTime fajr = LocalTime.parse(data1.getTimings().getFajr().substring(0, 5));
                LocalTime fajr2 = fajr.plusMinutes(minute);
                timings.setFajr(fajr2.toString()+" 05");
                LocalTime sunrise = LocalTime.parse(data1.getTimings().getSunrise().substring(0, 5));
                LocalTime sunrise2 = sunrise.plusMinutes(minute);
                timings.setSunrise(sunrise2.toString()+" 05");
                LocalTime Dhuhr = LocalTime.parse(data1.getTimings().getDhuhr().substring(0, 5));
                LocalTime Dhuhr2 = Dhuhr.plusMinutes(minute);
                timings.setDhuhr(Dhuhr2.toString()+" 05");
                LocalTime Asr = LocalTime.parse(data1.getTimings().getAsr().substring(0, 5));
                LocalTime Asr2 = Asr.plusMinutes(minute);
                timings.setAsr(Asr2.toString()+" 05");
                LocalTime Maghrib = LocalTime.parse(data1.getTimings().getMaghrib().substring(0, 5));
                LocalTime Maghrib2 = Maghrib.plusMinutes(minute);
                timings.setMaghrib(Maghrib2.toString()+" 05");
                LocalTime Isha = LocalTime.parse(data1.getTimings().getIsha().substring(0, 5));
                LocalTime Isha2 = Isha.plusMinutes(minute);
                timings.setIsha(Isha2.toString()+" 05");
                LocalTime Imsak = LocalTime.parse(data1.getTimings().getImsak().substring(0, 5));
                LocalTime Imsak2 = Imsak.plusMinutes(minute);
                timings.setImsak(Imsak2.toString()+" 05");
                data1.setTimings(timings);
            }
        return (ArrayList<Data1>) data;
        }


}

