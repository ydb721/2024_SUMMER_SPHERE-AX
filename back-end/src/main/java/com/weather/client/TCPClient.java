package com.weather.client;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TCPClient {
    public static void main(String[] args) {
        String hostname = System.getenv("SERVER_IP"); // 서버의 호스트 이름 (상대방의 공인 IP 주소)
        int port = Integer.parseInt(System.getenv("SERVER_PORT")); // 서버의 포트 번호
        
        // 스케줄러 생성
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        // 서버에 날씨 데이터를 전송하는 작업을 정의
        Runnable sendWeatherData = () -> {
        	// 날씨 데이터 생성
            String weatherData = WeatherDataGenerator.generateWeatherData();
            boolean sent = false;	// 전송 성공 여부
            while (!sent) {		// 전송 성공할 때까지 반복
                try (Socket socket = new Socket(hostname, port)) {
                    PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
                    out.println(weatherData);
                    System.out.println("Sent: " + weatherData);
                   
                    // 서버로부터 응답을 받기 위해 대기 (예시: 응답을 기다리지 않는 간단한 예제이므로 실제 사용 시에는 수정 필요)
                    // 서버로부터 응답을 받은 후, 다음 코드를 사용하여 JSP 파일에 데이터를 전달할 수 있습니다.
                    // request.setAttribute("weatherData", receivedData);
                   
                    sent = true; // 전송 성공
                } catch (Exception ex) {
                	// 연결 실패 시 메시지를 출력
                    System.out.println("Connection failed, retrying...");
                    try {
                        Thread.sleep(5000); // 5초 후 재시도
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();		// 스레드의 인터럽트 상태를 복원
                    }
                }
            }
        };
        // 테스트용 30초마다
        // scheduler.scheduleAtFixedRate(sendWeatherData, 0, 30, TimeUnit.SECONDS);

        // 1분마다 난수 생성 및 서버로 전송 시도
        scheduler.scheduleAtFixedRate(sendWeatherData, 0, 1, TimeUnit.MINUTES);
    }
}
