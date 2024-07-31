package com.weather.client;

import java.util.Random;

public class WeatherDataGenerator {
    private static final Random random = new Random();

    public static String generateWeatherData() {
        double Temperature = random.nextDouble() * 40; // 기온, 0 ~ 40
        int Humidity = random.nextInt(101); // 습도, 0 ~ 100
        double Pressure = random.nextDouble() * (1003.3 - 998.5) + 998.5; // 기압, 998.5 ~ 1003.3
        double WindSpeed = random.nextDouble() * 34.6; // 풍량, 0 ~ 34.6
        int WindDirection = random.nextInt(16) + 1; // 풍향, 1 ~ 16

        return String.format("%.1f/%d/%.1f/%.1f/%d",
                Temperature, Humidity, Pressure, WindSpeed, WindDirection);   // 기온/습도/기압/풍량/풍향
    
    }
    
}