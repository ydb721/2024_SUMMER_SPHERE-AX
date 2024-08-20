package com.weather.controller;

import com.weather.cmmn.WPSampleExcepHndlr;
import com.weather.dao.WeatherDataDAO;
import com.weather.model.WeatherData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class WeatherDataController {
    // WeatherDataDAO 주입받아 날씨 데이터를 조회하는 데 사용
    @Autowired
    private WeatherDataDAO weatherDataDAO;

    /* 최신 날씨 데이터를 조회하여 뷰에 전달
     * @param model Spring의 Model 객체로 뷰에 전달할 데이터 추가
     * @return 뷰 이름 (JSP 또는 다른 뷰 템플릿)
     */
    @GetMapping("/latestWeatherData")
    public String viewLatestWeather(Model model, HttpServletRequest request, HttpServletResponse response) {
        try {
            List<WeatherData> weatherDataList = weatherDataDAO.getMostRecentWeatherData();
            // 날씨 데이터가 비어있다면 에러 메시지를 모델에 추가
            if (weatherDataList.isEmpty()) {
                model.addAttribute("error", "No weather data available");
            } else {
                // 날씨 데이터가 있을 경우, 모델에 데이터 목록 추가
                model.addAttribute("weatherDataList", weatherDataList);
            }
            return "latestWeatherDataView"; // JSP 또는 뷰 이름
        } catch (Exception ex) {
            // 예외 발생 시 예외 처리 핸들러 호출
            WPSampleExcepHndlr handler = new WPSampleExcepHndlr();
            handler.occur(ex, "com.weather.controller.WeatherDataController", request, response);
            return null; // 예외 처리 후 null 반환, 실제로는 예외 핸들러가 포워딩 처리
        }
    }
}
