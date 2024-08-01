package com.weather.service;

import com.weather.dao.WeatherDataDAO;
import com.weather.model.WeatherData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service // 이 클래스를 서비스 계층의 빈으로 표시합니다.
public class WeatherDataService {

    private WeatherDataDAO weatherDataDAO; // 데이터베이스 작업을 수행하는 DAO 객체

    // 기본 생성자
    public WeatherDataService() {
    }

    // DAO를 주입받는 생성자
    @Autowired
    public WeatherDataService(WeatherDataDAO weatherDataDAO) {
        this.weatherDataDAO = weatherDataDAO;
    }

    // DAO를 설정하는 세터 메서드
    public void setWeatherDataDAO(WeatherDataDAO weatherDataDAO) {
        this.weatherDataDAO = weatherDataDAO;
    }

    // WeatherData 객체를 데이터베이스에 저장하는 메서드
    public void addWeatherData(WeatherData data) {
        weatherDataDAO.saveWeatherData(data); // DAO의 saveWeatherData 메서드를 호출하여 데이터를 저장합니다.
    }

    // 모든 날씨 데이터를 가져오는 메서드 (현재는 임시로 빈 리스트를 반환)
    public List<WeatherData> getAllWeatherData() {
        // 여기에 적절한 메소드 구현 필요
        // 예: return weatherDataDAO.getAllWeatherData();
        return new ArrayList<>(); // 현재는 임시 반환
    }
}