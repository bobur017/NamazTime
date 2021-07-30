package com.example.demo.weatherService;


import com.example.demo.Repository.UserRepository;
import com.example.demo.entity.Users;
import com.example.demo.model.weather.City;
import com.example.demo.model.weather.Weather;
import com.example.demo.model.weather.current.Current;
import com.example.demo.model.weather.daily.Daily;
import com.example.demo.model.weather.hourly.Hourly;
import com.example.demo.service.MenuButtons;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

@Service
public class WeatherService {


    @Autowired
    UserRepository userRepository;

    @Autowired
    MenuButtons menuButtons;

    @SneakyThrows
    public String getDayWeather(double lat, double lon) {
        URL url = new URL("https://api.openweathermap.org/data/2.5/onecall?lat=" + lat + "&lon=" + lon + "&exclude=alerts&appid=cd5bc3a13b97ec73117b518561d9a4b2&lang=ru&units=metric");
        ObjectMapper objectMapper = new ObjectMapper();
        Weather weather = objectMapper.readValue(url, Weather.class);

        StringBuilder java_date = new StringBuilder();
        ArrayList<Hourly> hourlies = weather.getHourly();

        for (Hourly hourl : hourlies) {
            long unix_seconds = hourl.getDt().getTime();
            //convert seconds to milliseconds
            Date date = new Date(unix_seconds * 1000L);
            // format of the date
            SimpleDateFormat jdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            jdf.setTimeZone(TimeZone.getTimeZone("GMT+5"));
            java_date.append(jdf.format(date)).append("\n").append(hourl.getWeather().get(0).getIcon()).append("  \u1F32");

        }

        return null;
    }

    @SneakyThrows
    public String getCurrentWeather(Long chatId) {
        Users users = userRepository.findById(chatId).get();
        double lat = users.getLat();
        double lon = users.getLon();
        URL url = new URL("https://api.openweathermap.org/data/2.5/onecall?lat=" + lat + "&lon=" + lon + "&exclude=alerts&appid=cd5bc3a13b97ec73117b518561d9a4b2&lang=ru&units=metric");
        ObjectMapper objectMapper = new ObjectMapper();
        Weather weather = objectMapper.readValue(url, Weather.class);
        Current current = weather.getCurrent();
        StringBuilder weatherText = new StringBuilder();
        StringBuilder java_date = new StringBuilder();

        long unix_seconds = current.getDt().getTime();
        //convert seconds to milliseconds
        Date date = new Date(unix_seconds * 1000L);
        // format of the date
        SimpleDateFormat jdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        jdf.setTimeZone(TimeZone.getTimeZone("GMT+5"));
        java_date.append(users.getCityName()).append("\n\n Sana :").append(jdf.format(date)).append(getEmoji("Sana")).append("  \n");
        weatherText.append(java_date);
        weatherText.append("Quyosh chiqishi :").append(getTimeStamp(current.getSunrise()).substring(10)).append(getEmoji("Quyosh")).append("\n");
        weatherText.append("Quyosh botishi :").append(getTimeStamp(current.getSunset()).substring(10)).append(getEmoji("Oy")).append("\n\n\n");
        weatherText.append("HOZIR \n Harorat : ").append(current.getFeels_like()).append(" C ").append(getEmoji("Harorat")).append("\n");
        weatherText.append("Namlik : ").append(current.getHumidity()).append(getEmoji("Namlik")).append("  \n ");
        weatherText.append("Shamol tezligi : ").append(current.getWind_speed()).append(" metr/s ").append(getEmoji("Shamol")).append("\n");
        weatherText.append("Kun ob-havosi :").append(findByIdGetWeather(current.getWeather().get(0).getId()));
        return weatherText.toString();
    }

    public String findByIdGetWeather(int weatherId) {
        String weather = "";
        String bulut = "☁";
        String yomgir = "\uD83C\uDF27";
        String joylardaBulut = "\uD83C\uDF24";
        String quyoshli = "☀";
        String ozYomgirli = "\uD83C\uDF26";
        String yomgirliMomaqaldiroq = "⛈";
        String joylardaQor = "\uD83C\uDF28";
        String qor = "❄";
        String tuman = "\uD83C\uDF2B";
        String shamol = "\uD83C\uDF2C";
        String oqshom = "\uD83C\uDF19";
        if (weatherId > 199 && weatherId < 232) {
            return weather += yomgirliMomaqaldiroq;
        } else if (weatherId > 299 && weatherId < 311) {
            weather += "Joylarda yomg'ir  ";
            return weather += ozYomgirli;
        } else if (weatherId > 312 && weatherId < 322) {
            weather += "Yomg'irli  ";
            return weather += yomgir;
        } else if (weatherId > 499 && weatherId < 532) {
            weather += "Momaqaldiroqli uomg'ir  ";
            return weather += yomgirliMomaqaldiroq;
        } else if (weatherId > 599 && weatherId < 616) {
            weather += "Kam Yomg'irli  ";
            return weather += joylardaQor;
        } else if (weatherId > 619 && weatherId < 623) {
            weather += "Qorli  ";
            return weather += qor;
        } else if (weatherId > 700 && weatherId < 761) {
            weather += "Tumanli  ";
            return weather += tuman;
        } else if (weatherId > 761 && weatherId < 782) {
            weather += "Shamolli  ";
            return weather += shamol;
        } else if (weatherId == 800) {
            weather += "Quyoshli  ";
            return weather += quyoshli;
        } else if (weatherId > 800 && weatherId < 804) {
            weather += "Bulutli   ";
            return weather += bulut;
        }
        return weather;
    }

    public String getTimeStamp(Timestamp time) {
        String getTime = "";
        long unix_seconds = time.getTime();
        //convert seconds to milliseconds
        Date date = new Date(unix_seconds * 1000L);
        // format of the date
        SimpleDateFormat jdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        jdf.setTimeZone(TimeZone.getTimeZone("GMT+5"));
        return getTime += jdf.format(date);
    }

    public String getEmoji(String text) {
        switch (text) {
            case "Quyosh":
                return "  ☀";
            case "Namlik":
                return "  \uD83D\uDCA7";
            case "Harorat":
                return "  \uD83C\uDF21";
            case "Sana":
                return "  \uD83D\uDCC5";
            case "Oy":
                return "  \uD83C\uDF19";
            case "Shamol":
                return "  \uD83C\uDF2C";
            case "Bulut":
                return "  ☁";
        }
        return text;
    }

    public ReplyKeyboardMarkup saveWeatherCity(String cityName, Long chatId, double lat, double lon) {
        Users users = userRepository.findById(chatId).get();
        users.setId(chatId);
        users.setLat(lat);
        users.setLon(lon);
        users.setCityName(cityName);
        userRepository.save(users);
        return menuButtons.weatherButton();
    }


    @SneakyThrows
    public String getHourlyWeather(Long chatId) {
        Users users = userRepository.findById(chatId).get();
        double lat = users.getLat();
        double lon = users.getLon();
        URL url = new URL("https://api.openweathermap.org/data/2.5/onecall?lat=" + lat + "&lon=" + lon + "&exclude=alerts&appid=cd5bc3a13b97ec73117b518561d9a4b2&lang=ru&units=metric");
        ObjectMapper objectMapper = new ObjectMapper();
        Weather weather = objectMapper.readValue(url, Weather.class);
        ArrayList<Hourly> hourly = weather.getHourly();
        StringBuilder weatherText = new StringBuilder();
        int count = 0;
        weatherText.append(users.getCityName()).append("\n").append("Sana : ").append(getTimeStamp(weather.getCurrent().getDt()).substring(0, 10)).append(getEmoji("Sana")).append("\n\n");
        weatherText.append("Quyosh chiqishi : ").append(getTimeStamp(weather.getCurrent().getSunrise()).substring(10)).append(getEmoji("Quyosh")).append("\n");
        weatherText.append("Quyosh botishi : ").append(getTimeStamp(weather.getCurrent().getSunset()).substring(10)).append(getEmoji("Oy")).append("\n\n\n");
        for (Hourly hourl : hourly) {
            weatherText.append("Umumiy holati : ").append(findByIdGetWeather(hourl.getWeather().get(0).getId())).append("\n");
            weatherText.append("Soat : ").append(getTimeStamp(hourl.getDt()).substring(10)).append("\t\t Sana :").append(getTimeStamp(hourl.getDt()).substring(0, 10)).append("\n");
            weatherText.append("Harorat : ").append(hourl.getFeels_like()).append(" C").append(getEmoji("Harorat")).append("\n");
            weatherText.append("Namlik : ").append(hourl.getHumidity()).append(" %").append(getEmoji("Namlik")).append("\n");
            weatherText.append("Shamol tezligi : ").append(hourl.getWind_speed()).append(" Metr/s").append(getEmoji("Shamol")).append("\n");
            weatherText.append("Bulut : ").append(hourl.getClouds()).append(" %").append(getEmoji("Bulut")).append("\n\n");
            count++;
            if (count == 24) {
                break;
            }
        }
        return weatherText.toString();
    }

    @SneakyThrows
    public String getWeatherDaily(Long chatId) {
        Users users = userRepository.findById(chatId).get();
        double lat = users.getLat();
        double lon = users.getLon();
        URL url = new URL("https://api.openweathermap.org/data/2.5/onecall?lat=" + lat + "&lon=" + lon + "&exclude=alerts&appid=cd5bc3a13b97ec73117b518561d9a4b2&lang=ru&units=metric");
        ObjectMapper objectMapper = new ObjectMapper();
        Weather weather = objectMapper.readValue(url, Weather.class);
        ArrayList<Hourly> hourly = weather.getHourly();
        int count = 0;
        StringBuilder text = new StringBuilder();
        ArrayList<Daily> dailies = weather.getDaily();
        text.append(users.getCityName()).append("\n\n");
        for (Daily daily : dailies) {
        text.append("Sana : ").append(getTimeStamp(daily.getDt()).substring(0,10)).append(getEmoji("Sana")).append("\n");
        text.append("Quyosh chiqishi: ").append(getTimeStamp(daily.getSunrise()).substring(10)).append(getEmoji("Quyosh")).append("\n");
        text.append("Quyosh botishi: ").append(getTimeStamp(daily.getSunrise()).substring(10)).append(getEmoji("Oy")).append("\n");
        text.append("Kun ob-havosi: ").append(findByIdGetWeather(daily.getWeather().get(0).getId())).append("\n");
            text.append("Yuqori harorat: ").append(daily.getTemp().getMax()).append(" C").append(getEmoji("Harorat")).append("\n");;
            text.append("Past harorat: ").append(daily.getTemp().getMin()).append(" C").append(getEmoji("Harorat")).append("\n");;
            text.append("Tongi harorat: ").append(daily.getTemp().getMorn()).append(" C").append(getEmoji("Harorat")).append("\n");;
            text.append("Kunduzgi harorat: ").append(daily.getTemp().getDay()).append(" C").append(getEmoji("Harorat")).append("\n");;
            text.append("Kechqurungi harorat: ").append(daily.getTemp().getEve()).append(" C").append(getEmoji("Harorat")).append("\n");;
            text.append("Oqshomgi harorat: ").append(daily.getTemp().getNight()).append(" C").append(getEmoji("Harorat")).append("\n");;
            text.append("Namlik : ").append(daily.getHumidity()).append(" %").append(getEmoji("Namlik")).append("\n");;
            text.append("Shamol tezligi: ").append(daily.getWind_speed()).append(" Metr/s").append(getEmoji("Shamol")).append("\n");
            text.append("Bulut : ").append(daily.getClouds()).append(getEmoji("Bulut")).append(" % \n\n\n");
            count++;
            if (count==7){
                break;
            }
        }
        return text.toString();
    }
}
