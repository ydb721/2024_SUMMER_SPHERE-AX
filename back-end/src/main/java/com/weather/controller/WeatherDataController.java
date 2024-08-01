package com.weather.controller;

import com.weather.dao.WeatherDataDAO;
import com.weather.model.WeatherData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class WeatherDataController {

    @Autowired
    private WeatherDataDAO weatherDataDAO;

    @GetMapping("/latestWeatherData")
    public String viewLatestWeather(Model model) {
        List<WeatherData> weatherDataList = weatherDataDAO.getMostRecentWeatherData();
        if (weatherDataList.isEmpty()) {
            model.addAttribute("error", "No weather data available");
        } else {
            model.addAttribute("weatherDataList", weatherDataList);
        }
        return "latestWeatherDataView"; // JSP 또는 뷰의 이름
    }
}
