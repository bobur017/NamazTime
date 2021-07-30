package com.example.demo.service;

import com.example.demo.Repository.UserRepository;
import com.example.demo.entity.Users;
import com.example.demo.model.weather.City;
import com.example.demo.weatherService.WeatherService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuButtons {

    @Autowired
    private Servise servise;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WeatherService weatherService;

    public ReplyKeyboardMarkup buttons(){
        ReplyKeyboardMarkup replyKeyboardMarkup=new ReplyKeyboardMarkup();
        KeyboardRow keyboardRow=new KeyboardRow();
        List<KeyboardRow> keyboardRowList=new ArrayList<>();
        KeyboardButton keyboardButton=new KeyboardButton();
        KeyboardButton keyboardButton2=new KeyboardButton();
        keyboardButton.setText("BUGUN: Namoz vaqtlari");
        keyboardRow.add(keyboardButton);

        keyboardButton=new KeyboardButton();
        keyboardButton.setText("Namoz vaqtlarini qoidalari");
        keyboardRow.add(keyboardButton);
        keyboardRowList.add(keyboardRow);


        keyboardButton=new KeyboardButton();
        keyboardRow=new KeyboardRow();
        keyboardButton.setText("Viloyatni Tanlang");
        keyboardRow.add(keyboardButton);


        keyboardButton=new KeyboardButton();
        keyboardButton.setText("Ob-Havo ma'lumotlari");
        keyboardRow.add(keyboardButton);
        keyboardRowList.add(keyboardRow);


        keyboardButton=new KeyboardButton();
        keyboardRow=new KeyboardRow();
        keyboardButton.setText("Haftalik Namoz vaqtlari");
        keyboardRow.add(keyboardButton);

        keyboardButton=new KeyboardButton();
        keyboardButton.setText("Biz haqimizda");
        keyboardRow.add(keyboardButton);
        keyboardRowList.add(keyboardRow);

        replyKeyboardMarkup.setKeyboard(keyboardRowList);
        return replyKeyboardMarkup;
    }
    @SneakyThrows
    public ReplyKeyboardMarkup getCity(String name,Long chatId){
        ReplyKeyboardMarkup getTime=new ReplyKeyboardMarkup();
        switch (name){
            case "Toshkent":
                getTime= servise.getByCityDayTime(0,chatId,"Toshkent");
                break;
            case "Andijon viloyati":
               getTime= servise.getByCityDayTime(-11,chatId,"Andijon");
                break;
            case "Buxoro viloyati":
                getTime= servise.getByCityDayTime(21,chatId,"Buxoro");
                break;
            case "Fargʻona viloyati":
                getTime= servise.getByCityDayTime(-10,chatId,"Fargʻona");
                break;
            case "Namangan viloyati":
                getTime= servise.getByCityDayTime(-10,chatId,"Namangan");
                break;
            case "Jizzax viloyati":
                getTime= servise.getByCityDayTime(6,chatId,"Jizzax");
                break;
            case "Xorazm viloyati":
                getTime= servise.getByCityDayTime(35,chatId,"Xorazm");
                break;
            case "Navoiy viloyati":
                getTime= servise.getByCityDayTime(19,chatId,"Navoiy");
                break;
            case "Qashqadaryo viloyati":
                getTime= servise.getByCityDayTime(10,chatId,"Qashqadaryo");
                break;
            case "Qoraqalpogʻiston Respublikasi":
                getTime= servise.getByCityDayTime(42,chatId,"Qoraqalpogʻiston Respublikasi");
                break;
            case "Samarqand viloyati":
                getTime= servise.getByCityDayTime(9,chatId,"Samarqand");
                break;
            case "Surxondaryo viloyati":
                getTime= servise.getByCityDayTime(9,chatId,"Surxondaryo");
                break;
            case "Sirdaryo viloyati":
                getTime= servise.getByCityDayTime(7,chatId,"Sirdaryo");
                break;
        }
        return getTime;
    }
    public ReplyKeyboardMarkup getCity(){
        ReplyKeyboardMarkup replyKeyboardMarkup=new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboardRowList=new ArrayList<>();

        KeyboardRow keyboardRow=new KeyboardRow();
        KeyboardButton keyboardButton=new KeyboardButton();

        keyboardButton.setText("Toshkent");
        keyboardRow.add(keyboardButton);
        keyboardButton=new KeyboardButton();
        keyboardButton.setText("\u21A9 ortga");
        keyboardRow.add(keyboardButton);
        keyboardRowList.add(keyboardRow);

        keyboardRow=new KeyboardRow();
        keyboardButton=new KeyboardButton();
        keyboardButton.setText("Andijon viloyati");
        keyboardRow.add(keyboardButton);
        keyboardButton=new KeyboardButton();
        keyboardButton.setText("Buxoro viloyati");
        keyboardRow.add(keyboardButton);
        keyboardButton=new KeyboardButton();
        keyboardButton.setText("Fargʻona viloyati");
        keyboardRow.add(keyboardButton);
        keyboardRowList.add(keyboardRow);


        keyboardButton=new KeyboardButton();
        keyboardRow=new KeyboardRow();
        keyboardButton.setText("Namangan viloyati");
        keyboardRow.add(keyboardButton);
        keyboardButton=new KeyboardButton();
        keyboardButton.setText("Jizzax viloyati");
        keyboardRow.add(keyboardButton);
        keyboardButton=new KeyboardButton();
        keyboardButton.setText("Xorazm viloyati");
        keyboardRow.add(keyboardButton);
        keyboardRowList.add(keyboardRow);


        keyboardButton=new KeyboardButton();
        keyboardRow=new KeyboardRow();
        keyboardButton.setText("Navoiy viloyati");
        keyboardRow.add(keyboardButton);
        keyboardButton=new KeyboardButton();
        keyboardButton.setText("Qashqadaryo viloyati");
        keyboardRow.add(keyboardButton);
        keyboardButton=new KeyboardButton();
        keyboardButton.setText("Qoraqalpogʻiston Respublikasi");
        keyboardRow.add(keyboardButton);
        keyboardRowList.add(keyboardRow);

        keyboardButton=new KeyboardButton();
        keyboardRow=new KeyboardRow();
        keyboardButton.setText("Samarqand viloyati");
        keyboardRow.add(keyboardButton);
        keyboardButton=new KeyboardButton();
        keyboardButton.setText("Surxondaryo viloyati");
        keyboardRow.add(keyboardButton);
        keyboardButton=new KeyboardButton();
        keyboardButton.setText("Sirdaryo viloyati");
        keyboardRow.add(keyboardButton);
        keyboardRowList.add(keyboardRow);

        replyKeyboardMarkup.setKeyboard(keyboardRowList);
        return replyKeyboardMarkup;
    }
    public ReplyKeyboardMarkup regulations(){
        ReplyKeyboardMarkup replyKeyboardMarkup=new ReplyKeyboardMarkup();
        KeyboardRow keyboardRow=new KeyboardRow();
        List<KeyboardRow> keyboardRowList=new ArrayList<>();
        KeyboardButton keyboardButton=new KeyboardButton();


        keyboardButton.setText("Bomdod namozi vaqti qoidasi");
        keyboardRow.add(keyboardButton);
        keyboardButton=new KeyboardButton();
        keyboardButton.setText("Pеshin namozi vaqti qoidasi");
        keyboardRow.add(keyboardButton);
        keyboardButton=new KeyboardButton();
        keyboardButton.setText("Asr namozi vaqti qoidasi");
        keyboardRow.add(keyboardButton);
        keyboardRowList.add(keyboardRow);


        keyboardButton=new KeyboardButton();
        keyboardRow=new KeyboardRow();
        keyboardButton.setText("Shom namozi vaqti qoidasi");
        keyboardRow.add(keyboardButton);
        keyboardButton=new KeyboardButton();
        keyboardButton.setText("Xufton namozi vaqti qoidasi");
        keyboardRow.add(keyboardButton);
        keyboardButton=new KeyboardButton();
        keyboardButton.setText("Tahajjut namozi vaqti qoidasi");
        keyboardRow.add(keyboardButton);
        keyboardRowList.add(keyboardRow);


        keyboardButton=new KeyboardButton();
        keyboardRow=new KeyboardRow();
        keyboardButton.setText("\u21A9 ortga");
        keyboardRow.add(keyboardButton);
        keyboardRowList.add(keyboardRow);

        replyKeyboardMarkup.setKeyboard(keyboardRowList);
        return replyKeyboardMarkup;
    }
    public ReplyKeyboardMarkup getWeatherButtons(Long chatId){
        Users users = userRepository.findById(chatId).get();
        if (users.getLat()==null&&users.getLon()==null){
            return getCityForWeather();
        }else {
            return weatherButton();
        }
    }
    public ReplyKeyboardMarkup getCityForWeather(){
        ReplyKeyboardMarkup replyKeyboardMarkup=new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboardRowList=new ArrayList<>();

        KeyboardRow keyboardRow=new KeyboardRow();
        KeyboardButton keyboardButton=new KeyboardButton();

        keyboardButton.setText("Toshkent.");
        keyboardRow.add(keyboardButton);
        keyboardButton=new KeyboardButton();
        keyboardButton.setText("\u21A9 ortga");
        keyboardRow.add(keyboardButton);
        keyboardRowList.add(keyboardRow);

        keyboardRow=new KeyboardRow();
        keyboardButton=new KeyboardButton();
        keyboardButton.setText("Andijon viloyati.");
        keyboardRow.add(keyboardButton);
        keyboardButton=new KeyboardButton();
        keyboardButton.setText("Buxoro viloyati.");
        keyboardRow.add(keyboardButton);
        keyboardButton=new KeyboardButton();
        keyboardButton.setText("Fargʻona viloyati.");
        keyboardRow.add(keyboardButton);
        keyboardRowList.add(keyboardRow);


        keyboardButton=new KeyboardButton();
        keyboardRow=new KeyboardRow();
        keyboardButton.setText("Namangan viloyati.");
        keyboardRow.add(keyboardButton);
        keyboardButton=new KeyboardButton();
        keyboardButton.setText("Jizzax viloyati.");
        keyboardRow.add(keyboardButton);
        keyboardButton=new KeyboardButton();
        keyboardButton.setText("Xorazm viloyati.");
        keyboardRow.add(keyboardButton);
        keyboardRowList.add(keyboardRow);


        keyboardButton=new KeyboardButton();
        keyboardRow=new KeyboardRow();
        keyboardButton.setText("Navoiy viloyati.");
        keyboardRow.add(keyboardButton);
        keyboardButton=new KeyboardButton();
        keyboardButton.setText("Qashqadaryo viloyati.");
        keyboardRow.add(keyboardButton);
        keyboardButton=new KeyboardButton();
        keyboardButton.setText("Qoraqalpogʻiston Respublikasi.");
        keyboardRow.add(keyboardButton);
        keyboardRowList.add(keyboardRow);

        keyboardButton=new KeyboardButton();
        keyboardRow=new KeyboardRow();
        keyboardButton.setText("Samarqand viloyati.");
        keyboardRow.add(keyboardButton);
        keyboardButton=new KeyboardButton();
        keyboardButton.setText("Surxondaryo viloyati.");
        keyboardRow.add(keyboardButton);
        keyboardButton=new KeyboardButton();
        keyboardButton.setText("Sirdaryo viloyati.");
        keyboardRow.add(keyboardButton);
        keyboardRowList.add(keyboardRow);

        replyKeyboardMarkup.setKeyboard(keyboardRowList);
        return replyKeyboardMarkup;
    }

    public ReplyKeyboardMarkup weatherButton(){
        ReplyKeyboardMarkup replyKeyboardMarkup=new ReplyKeyboardMarkup();
        KeyboardRow keyboardRow=new KeyboardRow();
        List<KeyboardRow> keyboardRowList=new ArrayList<>();
        KeyboardButton keyboardButton=new KeyboardButton();
        KeyboardButton keyboardButton2=new KeyboardButton();
        keyboardButton.setText("Hozir");
        keyboardRow.add(keyboardButton);

        keyboardButton=new KeyboardButton();
        keyboardButton.setText("Bugun (soatbay ma'lumot)");
        keyboardRow.add(keyboardButton);
        keyboardRowList.add(keyboardRow);


        keyboardButton=new KeyboardButton();
        keyboardRow=new KeyboardRow();
        keyboardButton.setText("Viloyatni Tanlang.");
        keyboardRow.add(keyboardButton);


        keyboardButton=new KeyboardButton();
        keyboardButton.setText("Ob-Havo 7-kun");
        keyboardRow.add(keyboardButton);
        keyboardRowList.add(keyboardRow);


        keyboardButton=new KeyboardButton();
        keyboardRow=new KeyboardRow();
        keyboardButton.setText("\u21A9 ortga");
        keyboardRow.add(keyboardButton);
        keyboardRowList.add(keyboardRow);

        replyKeyboardMarkup.setKeyboard(keyboardRowList);
        return replyKeyboardMarkup;
    }
    @SneakyThrows
    public ReplyKeyboardMarkup getWeatherCity(String name, Long chatId){
        ReplyKeyboardMarkup getTime=new ReplyKeyboardMarkup();
        switch (name){
            case "Toshkent.":
                getTime=weatherService.saveWeatherCity(City.TASHKENT.getName1(), chatId,City.TASHKENT.getLat(), City.TASHKENT.getLon());
                break;
            case "Andijon viloyati.":
                getTime= weatherService.saveWeatherCity(City.ANDIJAN.getName1(), chatId,City.ANDIJAN.getLat(), City.ANDIJAN.getLon());
                break;
            case "Buxoro viloyati.":
                getTime= weatherService.saveWeatherCity(City.BUXARA.getName1(), chatId,City.BUXARA.getLat(), City.BUXARA.getLon());
                break;
            case "Fargʻona viloyati.":
                getTime= weatherService.saveWeatherCity(City.FAGANA.getName1(), chatId,City.FAGANA.getLat(), City.FAGANA.getLon());
                break;
            case "Namangan viloyati.":
                getTime= weatherService.saveWeatherCity(City.NAMANGAN.getName1(), chatId,City.NAMANGAN.getLat(), City.NAMANGAN.getLon());
                break;
            case "Jizzax viloyati.":
                getTime=weatherService.saveWeatherCity(City.JIZZAX.getName1(), chatId,City.JIZZAX.getLat(), City.JIZZAX.getLon());
                break;
            case "Xorazm viloyati.":
                getTime=weatherService.saveWeatherCity(City.XORAZM.getName1(), chatId,City.XORAZM.getLat(), City.XORAZM.getLon());
                break;
            case "Navoiy viloyati.":
                getTime=weatherService.saveWeatherCity(City.NAVAIY.getName1(), chatId,City.NAVAIY.getLat(), City.NAVAIY.getLon());
                break;
            case "Qashqadaryo viloyati.":
                getTime=weatherService.saveWeatherCity(City.QASHQADARYO.getName1(), chatId,City.QASHQADARYO.getLat(), City.QASHQADARYO.getLon());
                break;
            case "Qoraqalpogʻiston Respublikasi.":
                getTime=weatherService.saveWeatherCity(City.KARAKALPAK.getName1(), chatId,City.KARAKALPAK.getLat(), City.KARAKALPAK.getLon());
                break;
            case "Samarqand viloyati.":
                getTime=weatherService.saveWeatherCity(City.SAMARKAND.getName1(), chatId,City.SAMARKAND.getLat(), City.SAMARKAND.getLon());
                break;
            case "Surxondaryo viloyati.":
                getTime= weatherService.saveWeatherCity(City.SURXONDARYO.getName1(), chatId,City.SURXONDARYO.getLat(), City.SURXONDARYO.getLon());
                break;
            case "Sirdaryo viloyati.":
                getTime= weatherService.saveWeatherCity(City.SIRDARYA.getName1(), chatId,City.SIRDARYA.getLat(), City.SIRDARYA.getLon());
                break;

        }
        return getTime;
    }
}
