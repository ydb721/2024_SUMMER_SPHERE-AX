package com.weather.service;

import com.weather.dao.WeatherDataDAO;
import com.weather.model.WeatherData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherDataService {

    private WeatherDataDAO weatherDataDAO;

    // 기본 생성자 추가
    public WeatherDataService() {
    }

    @Autowired
    public WeatherDataService(WeatherDataDAO weatherDataDAO) {
        this.weatherDataDAO = weatherDataDAO;
    }

    public void setWeatherDataDAO(WeatherDataDAO weatherDataDAO) {
        this.weatherDataDAO = weatherDataDAO;
    }

    public void addWeatherData(WeatherData data) {
        weatherDataDAO.saveWeatherData(data); // WeatherData 객체를 직접 전달
    }

    public List<WeatherData> getAllWeatherData() {
        // 여기에 적절한 메소드 구현 필요
        // 예: return weatherDataDAO.getAllWeatherData();
        return new ArrayList<>(); // 임시 반환
    }
}
