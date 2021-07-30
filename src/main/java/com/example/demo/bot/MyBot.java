package com.example.demo.bot;

import com.example.demo.model.weather.City;
import com.example.demo.service.MenuButtons;
import com.example.demo.service.Servise;
import com.example.demo.weatherService.WeatherService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;


@Component
public class MyBot extends TelegramLongPollingBot {

    private Long userChatId;
    private String boToken = "1859212538:AAHp4StE4fSfljnU_cxb_1LrWnXCI8ltBX4";
    private String userName = "namoz_vaqti";
    private String textMassage = "Bosh menu";
    private String userNameOrg="";
    private final Servise servise;

    @Autowired
    private MenuButtons menuButtons;

    @Autowired
    WeatherService weatherService;

    @Autowired
    public MyBot(Servise servise) {
        this.servise = servise;
    }

    @Override
    public String getBotToken() {
        return boToken;
    }

    @Override
    public String getBotUsername() {
        return userName;
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        userChatId = update.getMessage().getChatId();
        userNameOrg = update.getMessage().getFrom().getUserName();
        userNameOrg=update.getMessage().getAuthorSignature();
        if (update.hasMessage()) {
            String text = update.getMessage().getText();
            if (text.endsWith("viloyati")||text.endsWith("Respublikasi")) {
                execute(null,menuButtons.getCity(text,userChatId));
            }if (text.endsWith("qoidasi")){
                sendMassage(servise.getRegulations(text));
            }else if (text.endsWith("Toshkent.")||text.endsWith("viloyati.")||text.endsWith("Respublikasi.")){
                execute(null,menuButtons.getWeatherCity(text,userChatId));
            }else {
                switch (text) {
                    case "/start":
                        execute(null, menuButtons.buttons());
                        servise.saveUser(userChatId,userNameOrg);
                        break;
                    case "BUGUN: Namoz vaqtlari":
                        sendMassage(servise.getDayTime(userChatId));
                        break;
                    case "Viloyatni Tanlang":
                        execute(null, menuButtons.getCity());
                        break;
                    case "Namoz vaqtlarini qoidalari":
                        execute(null,menuButtons.regulations());
                        break;
                    case "Haftalik Namoz vaqtlari":
                        sendMassage(servise.getDayTimeSeven(userChatId));
                        break;
                    case "Biz haqimizda":
                        break;
                    case "\u21A9 ortga":
                        execute(null, menuButtons.buttons());
                        break;
                    case "Toshkent":
                        execute(null,menuButtons.getCity(text,userChatId));
                        break;
                    case "Ob-Havo ma'lumotlari":
                        execute(null,menuButtons.getWeatherButtons(userChatId));
                        break;
                    case "Hozir":
                        sendMassage(weatherService.getCurrentWeather(userChatId));
                        break;
                    case "Viloyatni Tanlang.":
                        execute(null,menuButtons.getCityForWeather());
                        break;
                    case "Bugun (soatbay ma'lumot)":
                        sendMassage(weatherService.getHourlyWeather(userChatId));
                        break;
                    case "Ob-Havo 7-kun":
                        sendMassage(weatherService.getWeatherDaily(userChatId));
                        break;
                }

            }
        }
        if (update.hasCallbackQuery()) {

        }
    }

    public void execute(
            InlineKeyboardMarkup inKeyboard, ReplyKeyboardMarkup replyKeyboardMarkup
    ) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(userChatId.toString());
        sendMessage.setText(textMassage);
        if (replyKeyboardMarkup != null) {
            sendMessage.setReplyMarkup(replyKeyboardMarkup);
            replyKeyboardMarkup.setResizeKeyboard(true);
            replyKeyboardMarkup.setOneTimeKeyboard(true);
            replyKeyboardMarkup.setSelective(true);
        }
        if (inKeyboard != null) {

        }
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    public void sendMassage(String text){

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(userChatId.toString());
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}
