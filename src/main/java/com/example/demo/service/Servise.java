package com.example.demo.service;

import com.example.demo.Repository.UserRepository;
import com.example.demo.entity.Users;
import com.example.demo.model.json.Data1;
import com.example.demo.model.json.GetApi;
import com.example.demo.model.json.gregorian.Date;
import com.example.demo.model.json.gregorian.Month;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

import java.io.IOException;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Locale;

@Service
@RestController
@RequestMapping("api/time")
public class Servise {
    @Autowired
    private MenuButtons menuButtons;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/test")
    public GetApi getTest() throws IOException {
        URL url = new URL("https://api.aladhan.com/v1/calendarByCity?city=Tashkent&country=Tashkent%&method=1&school=1&month=07&year=2021");
        ObjectMapper objectMapper = new ObjectMapper();
        GetApi getApi = new GetApi();
        getApi = objectMapper.readValue(url, GetApi.class);
        System.out.println(getApi.toString());
        return getApi;
    }

    public String getDayTime(Long chatId) throws IOException {
        Users users = userRepository.findById(chatId).get();
        Integer minute = users.getCityNumber();
        if (minute == null) {
            minute = 0;
        }
        int year = LocalDate.now().getYear();
        int month = LocalDate.now().getMonthValue();
        String monthS = "";
        if (month < 10) {
            monthS = String.valueOf(month);
            monthS += "0";
        } else {
            monthS = String.valueOf(month);
        }
        URL url = new URL("https://api.aladhan.com/v1/calendarByCity?city=Tashkent&country=Tashkent%&method=2&latitudeAdjustmentMethod=3&school=1&month=" + monthS + "&year=" + year);
        ObjectMapper objectMapper = new ObjectMapper();
        GetApi getApi = new GetApi();
        getApi = objectMapper.readValue(url, GetApi.class);
        ArrayList<Data1> data = getApi.getData();
        Date gregory = new Date();
        StringBuilder dataaa = new StringBuilder();

        for (Data1 data1 : data) {
            int localDate = LocalDate.now().getDayOfMonth();
            if (Integer.parseInt(data1.getDate().getGregorian().getDay()) == localDate) {
                dataaa.append("Sana :");
                dataaa.append(data1.getDate().getGregorian().getDate()).append("\t\t").append(users.getCityName()).append("\n\n DIQQAT: Toshkent shahar vaqtiga nisbatan hisoblangan, \n ").append(minute).append(" daqiqa farq qiladi\n\n");
                LocalTime fajr = LocalTime.parse(data1.getTimings().getFajr().substring(0, 5));
                LocalTime fajr2 = fajr.plusMinutes(minute);
                dataaa.append("\u0031\u20E3  Bomdod, kirish vaqti : ").append(fajr2.toString()).append("\n");
                LocalTime sunrise = LocalTime.parse(data1.getTimings().getSunrise().substring(0, 5));
                LocalTime sunrise2 = sunrise.plusMinutes(minute);
                dataaa.append("\u2600  Quyosh  chiqish vaqti : ").append(sunrise2.toString()).append("\n");
                LocalTime Dhuhr = LocalTime.parse(data1.getTimings().getDhuhr().substring(0, 5));
                LocalTime Dhuhr2 = Dhuhr.plusMinutes(minute);
                dataaa.append("\u0032\u20E3  Peshin, kirish vaqti : ").append(Dhuhr2).append("\n");
                LocalTime Asr = LocalTime.parse(data1.getTimings().getAsr().substring(0, 5));
                LocalTime Asr2 = Asr.plusMinutes(minute);
                dataaa.append("\u0033\u20E3  Asr, kirish vaqti : ").append(Asr2).append("\n");
                LocalTime Maghrib = LocalTime.parse(data1.getTimings().getMaghrib().substring(0, 5));
                LocalTime Maghrib2 = Maghrib.plusMinutes(minute);
                dataaa.append("\uD83C\uDF19  Quyosh  botish vaqti : ").append(Maghrib2).append("\n");
                dataaa.append("\u0034\u20E3  Shom, kirish vaqti (+ 5 min): ").append(Maghrib2).append("\n");
                LocalTime Isha = LocalTime.parse(data1.getTimings().getIsha().substring(0, 5));
                LocalTime Isha2 = Isha.plusMinutes(minute);
                dataaa.append("\u0035\u20E3  Xufton, kirish vaqti : ").append(Isha2).append("\n");
                LocalTime Imsak = LocalTime.parse(data1.getTimings().getImsak().substring(0, 5));
                LocalTime Imsak2 = Imsak.plusMinutes(minute);
                dataaa.append("\u0036\u20E3  Taxajjud, chiqish vaqti : ").append(Imsak2).append("\n");
            }
        }
        return dataaa.toString();
    }

    public ReplyKeyboardMarkup getByCityDayTime(Integer minute, Long chatId, String cityName) throws IOException {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        Users users = userRepository.findById(chatId).get();
        users.setId(chatId);
        users.setCityNumber(minute);
        users.setCityName(cityName);
        userRepository.save(users);

        /*int year = LocalDate.now().getYear();
        int month = LocalDate.now().getMonthValue();
        String monthS = "";
        if (month < 10) {
            monthS = String.valueOf(month);
            monthS += "0";
        } else {
            monthS = String.valueOf(month);
        }
        URL url = new URL("https://api.aladhan.com/v1/calendarByCity?city=Tashkent&country=Tashkent%&method=2&latitudeAdjustmentMethod=3&school=1&month=" + monthS + "&year=" + year);
        ObjectMapper objectMapper = new ObjectMapper();
        GetApi getApi = new GetApi();
        getApi = objectMapper.readValue(url, GetApi.class);
        ArrayList<Data1> data = getApi.getData();
        Date gregory = new Date();
        StringBuilder dataaa = new StringBuilder();

        for (Data1 data1 : data) {
            int localDate = LocalDate.now().getDayOfMonth();
            if (Integer.parseInt(data1.getDate().getGregorian().getDay()) == localDate) {
                dataaa.append("Sana :");
                dataaa.append(data1.getDate().getGregorian().getDate()).append("\t\t").append(cityName).append("\n\n DIQQAT: Toshkent shahar vaqtiga nisbatan hisoblangan, \n ").append(minute).append(" daqiqa farq qiladi\n\n");
                LocalTime fajr = LocalTime.parse(data1.getTimings().getFajr().substring(0, 5));
                LocalTime fajr2 = fajr.plusMinutes(minute);
                dataaa.append("\u0031\u20E3  Bomdod, kirish vaqti : ").append(fajr2.toString()).append("\n");
                LocalTime sunrise = LocalTime.parse(data1.getTimings().getSunrise().substring(0, 5));
                LocalTime sunrise2 = sunrise.plusMinutes(minute);
                dataaa.append("\u2600  Quyosh, vaqti : ").append(sunrise2.toString()).append("\n");
                LocalTime Dhuhr = LocalTime.parse(data1.getTimings().getDhuhr().substring(0, 5));
                LocalTime Dhuhr2 = Dhuhr.plusMinutes(minute);
                dataaa.append("\u0032\u20E3  Peshin, kirish vaqti : ").append(Dhuhr2).append("\n");
                LocalTime Asr = LocalTime.parse(data1.getTimings().getAsr().substring(0, 5));
                LocalTime Asr2 = Asr.plusMinutes(minute);
                dataaa.append("\u0033\u20E3  Asr, kirish vaqti : ").append(Asr2).append("\n");
                LocalTime Maghrib = LocalTime.parse(data1.getTimings().getMaghrib().substring(0, 5));
                LocalTime Maghrib2 = Maghrib.plusMinutes(minute);
                dataaa.append("\uD83C\uDF19  Quyosh  botish vaqti : ").append(Maghrib2).append("\n");
                dataaa.append("\u0034\u20E3  Shom, kirish vaqti (+ 5 min): ").append(Maghrib2).append("\n");
                LocalTime Isha = LocalTime.parse(data1.getTimings().getIsha().substring(0, 5));
                LocalTime Isha2 = Isha.plusMinutes(minute);
                dataaa.append("\u0035\u20E3  Xufton, kirish vaqti : ").append(Isha2).append("\n");
                LocalTime Imsak = LocalTime.parse(data1.getTimings().getImsak().substring(0, 5));
                LocalTime Imsak2 = Imsak.plusMinutes(minute);
                dataaa.append("\u0036\u20E3  Taxajjud, chiqish vaqti : ").append(Imsak2).append("\n");
            }*/

     return menuButtons.buttons();
    }

    public String getRegulations(String regulations) {
        String text = "";
        switch (regulations) {
            case "Bomdod namozi vaqti qoidasi":
                text = "Bomdod namozi \n\n" +
                        "Subhi sodiqdan (chin tong otgandan) kun chiqqunchadir.\n" +
                        "Bomdod namozini tong yorishganda oʻqish mustahab,\n" +
                        "a'loroqdir. Soat boʻyicha hisoblansa, bomdodni kun\n" +
                        "chiqishidan 40 daqiqacha ilgari oʻqish mustahab vaqtiga\n" +
                        "muvofiq boʻladi.\n" +
                        "manba: Islom.uz";
                break;
            case "Pеshin namozi vaqti qoidasi":
                text = "Pеshin namozi\n\n" +
                        "Quyosh zavolga (ogʻishga) kеtganidan soʻng to\n" +
                        "narsalarning soyasi quyosh tikkaga kеlgan paytdagi so-yasidan tashqari\n" +
                        "ortguniga qadar.\n" +
                        "Pеshin namozini yoz faslida biroz kеchiktirib, qish faslida\n" +
                        "esa vaqti kirishi bilan oʻqish mustahabdir.\n" +
                        "manba: Islom.uz";
                break;
            case "Asr namozi vaqti qoidasi":
                text = "Asr namozi\n\n" +
                        "Har bir narsaning soyasi quyosh tikkaga kеlgan paytdagi \n" +
                        "soyasidan tashqari oʻziga nisbatan ikki baravar\n" +
                        "ortganidan boshlab quyosh botgunchadir.\n" +
                        "Asr namozini quyosh tigʻini oʻzgartirmay, nursiz holatga\n" +
                        "kiri-shidan oldinroq oʻqish mustahabdir.\n" +
                        "manba: Islom.uz";
                break;
            case "Shom namozi vaqti qoidasi":
                text = "Shom namozi\n\n" +
                        "Kun botgan paytdan boshlab kunbotar tomonda shafaq\n" +
                        "(qizgʻish nurlar) gʻoyib boʻlgunchadir.\n" +
                        "Shom namozini doimo quyosh botishi bilan oʻqish\n" +
                        "mustahabdir.\n" +
                        "manba: Islom.uz";
                break;
            case "Xufton namozi vaqti qoidasi":
                text = "Xufton namozi\n\n" +
                        "Shafaq batamom yoʻqolgandan kеyin kiradi.\n" +
                        "Xufton namozini kеchaning uchdan birining oxirida oʻqish\n" +
                        "afzal va nihoyatda a'lo boʻladi.\n" +
                        "manba: Islom.uz";
                break;
            case "Tahajjut namozi vaqti qoidasi":
                text = "Tahajjud namozi vaqti qoidasi\n\n" +
                        "Хуфтон намозидан кейин бир муддат ухлаб туриб ўқилган ҳар қандай\n" +
                        "тунги намозга “таҳажжуд намози” дейилади. Чунки Таҳажжуд – кечаси уйқудан\n" +
                        "намоз учун туриш дегани. Субҳ содиқ, яъни тонг шафақлари бирлашишдан\n" +
                        "аввалги ўқилган намозлар таҳажжуд намозига киради. Ундан кейин бамдод\n" +
                        "намозини вақти киради\n" +
                        "manba: islom.uz";
                break;
            default:
                text = "bunaqa buyruq topilmadi";
        }
        return text;
    }

    public String getDayTimeSeven(Long chatId) throws IOException {
        Users users = userRepository.findById(chatId).get();
        int minute = users.getCityNumber();
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
        URL url = new URL("https://api.aladhan.com/v1/calendarByCity?city=Tashkent&country=Tashkent%&method=2&latitudeAdjustmentMethod=3&school=1&month=" + monthS + "&year=" + year);
        ObjectMapper objectMapper = new ObjectMapper();
        GetApi getApi = new GetApi();
        getApi = objectMapper.readValue(url, GetApi.class);
        ArrayList<Data1> data = getApi.getData();


        URL url1 = new URL("https://api.aladhan.com/v1/calendarByCity?city=Tashkent&country=Tashkent%&method=2&latitudeAdjustmentMethod=3&school=1&month=" + monthS2 + "&year=" + year);
        ObjectMapper objectMapper1 = new ObjectMapper();
        GetApi getApi11 = new GetApi();
        getApi11 = objectMapper1.readValue(url1, GetApi.class);
        ArrayList<Data1> data11 = getApi11.getData();
        StringBuilder dataaa = new StringBuilder();
        LocalDate localDate1 = LocalDate.now();
        data.addAll(data11);
        int count = 0;
        for (Data1 data1 : data) {
            if (Integer.parseInt(data1.getDate().getGregorian().getDay()) == localDate1.getDayOfMonth()) {
                dataaa.append("Sana :");
                dataaa.append(data1.getDate().getGregorian().getDate());
                if (count==0){
                    dataaa.append("\t\t").append(users.getCityName()).append("\n\n").append("DIQQAT: Toshkent shahar vaqtiga nisbatan hisoblangan, \n ").append(minute).append(" daqiqa farq qiladi");
                }
                LocalTime fajr = LocalTime.parse(data1.getTimings().getFajr().substring(0, 5));
                LocalTime fajr2 = fajr.plusMinutes(minute);
                dataaa.append("\n\n \u0031\u20E3  Bomdod, kirish vaqti : ").append(fajr2.toString()).append("\n");
                LocalTime sunrise = LocalTime.parse(data1.getTimings().getSunrise().substring(0, 5));
                LocalTime sunrise2 = sunrise.plusMinutes(minute);
                dataaa.append("\u2600  Quyosh  chiqish vaqti : ").append(sunrise2.toString()).append("\n");
                LocalTime Dhuhr = LocalTime.parse(data1.getTimings().getDhuhr().substring(0, 5));
                LocalTime Dhuhr2 = Dhuhr.plusMinutes(minute);
                dataaa.append("\u0032\u20E3  Peshin, kirish vaqti : ").append(Dhuhr2).append("\n");
                LocalTime Asr = LocalTime.parse(data1.getTimings().getAsr().substring(0, 5));
                LocalTime Asr2 = Asr.plusMinutes(minute);
                dataaa.append("\u0033\u20E3  Asr, kirish vaqti : ").append(Asr2).append("\n");
                LocalTime Maghrib = LocalTime.parse(data1.getTimings().getMaghrib().substring(0, 5));
                LocalTime Maghrib2 = Maghrib.plusMinutes(minute);
                dataaa.append("\uD83C\uDF19  Quyosh  botish vaqti : ").append(Maghrib2).append("\n");
                dataaa.append("\u0034\u20E3  Shom, kirish vaqti (+ 5 min): ").append(Maghrib2).append("\n");
                LocalTime Isha = LocalTime.parse(data1.getTimings().getIsha().substring(0, 5));
                LocalTime Isha2 = Isha.plusMinutes(minute);
                dataaa.append("\u0035\u20E3  Xufton, vaqti : ").append(Isha2).append("\n");
                LocalTime Imsak = LocalTime.parse(data1.getTimings().getImsak().substring(0, 5));
                LocalTime Imsak2 = Imsak.plusMinutes(minute);
                dataaa.append("\u0036\u20E3  Taxajjud, chiqish vaqti : ").append(Imsak2).append("\n\n");
                count += 1;
                if (count < 8) {
                    localDate1 = localDate1.plusDays(1);
                }
                if (count==8){
                    break;
                }
            }
        }

        return dataaa.toString();
    }


    public  void saveUser(Long id,String userName){
        Users users = new Users();
        users.setUserName(userName);
        users.setCityNumber(0);
        users.setId(id);
        users.setCityName("Toshkent");
        userRepository.save(users);
    }
}