package com.weather.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Timestamp;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.weather.service.WeatherDataService;
import com.weather.model.WeatherData;

public class TCPServer {
    private static final int PORT = Integer.parseInt(System.getenv("SERVER_PORT"));		// 서버 포트 번호를 환견변수에서 가져옴

    public static void main(String[] args) {
        // try-with-resources 구문을 사용하여 ApplicationContext를 자동으로 닫도록 함
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context-datasource.xml")) {
            WeatherDataService weatherDataService = context.getBean(WeatherDataService.class);

            if (weatherDataService == null) {
                System.out.println("WeatherDataService bean is not created");
                return;
            }
            
            // ServerSocket을 생성하고 클라이언트 연결을 기다림
            try (ServerSocket serverSocket = new ServerSocket(PORT)) {
                System.out.println("Server started on port " + PORT);

                while (true) {
                    try (Socket clientSocket = serverSocket.accept();
                         BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                    	// 클라이언트로부터 데이터 수신
                        String receivedData = in.readLine();
                        System.out.println("Received: " + receivedData);

                        // 데이터 파싱 (예: "13.0/54/1000.7/30.0/7" -> ["13.0", "54", "1000.7", "30.0", "7"])
                        String[] dataParts = receivedData.split("/");
                        if (dataParts.length == 5) {
                            double temperature = Double.parseDouble(dataParts[0]);
                            int humidity = Integer.parseInt(dataParts[1]);
                            double pressure = Double.parseDouble(dataParts[2]);
                            double windSpeed = Double.parseDouble(dataParts[3]);
                            int windDirection = Integer.parseInt(dataParts[4]);
                            Timestamp timestamp = new Timestamp(System.currentTimeMillis()); // 현재 시간

                            WeatherData weatherData = new WeatherData();
                            weatherData.setTemperature(temperature);
                            weatherData.setHumidity(humidity);
                            weatherData.setPressure(pressure);
                            weatherData.setWindSpeed(windSpeed);
                            weatherData.setWindDirection(windDirection);
                            weatherData.setTimestamp(timestamp);

                            // WeatherDataService를 통해 데이터 저장
                            weatherDataService.addWeatherData(weatherData);
                        } else {
                            System.out.println("Received data format is incorrect");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
