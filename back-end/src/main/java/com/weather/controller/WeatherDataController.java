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
	// WeatherDataDAO를 주입받아 날씨 데이터를 조회하는 데 사용
    @Autowired
    private WeatherDataDAO weatherDataDAO;
    
    /**
     * 최신 날씨 데이터를 조회하여 뷰에 전달합니다.
     * 
     * @param model Spring의 Model 객체로, 뷰에 전달할 데이터를 추가합니다.
     * @return 뷰의 이름 (JSP 또는 다른 뷰 템플릿)
     */

    @GetMapping("/latestWeatherData")
    public String viewLatestWeather(Model model) {
        List<WeatherData> weatherDataList = weatherDataDAO.getMostRecentWeatherData();
        // 날씨 데이터가 비어있다면 에러 메시지를 모델에 추가
        if (weatherDataList.isEmpty()) {
            model.addAttribute("error", "No weather data available");
        } else {
        	// 날씨 데이터가 있을 경우, 모델에 데이터 목록을 추가
            model.addAttribute("weatherDataList", weatherDataList);
        }
        return "latestWeatherDataView"; // JSP 또는 뷰의 이름
    }
}
