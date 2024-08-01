package com.weather.websocket;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.json.Json;
import javax.json.JsonObject;

@ServerEndpoint("/websocket")	 // WebSocket 서버 엔드포인트를 정의
public class WebSocketServer {

    private static final String DB_URL = System.getenv("DB_URL");		// 자신의 MySQL 테이블명 ex)jdbc:mysql://localhost:3306/myappdb
    private static final String DB_USER = System.getenv("DB_USER");		// 자신의 MySQL 유저명
    private static final String DB_PASS = System.getenv("DB_PASS");		// 자신의 MySQL 비밀번호
    
    private static final Set<Session> sessions = new HashSet<>();	 // 현재 연결된 세션을 저장하는 집합
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);	// 데이터 폴링을 위한 스케줄러

    private static JsonObject lastSentData = Json.createObjectBuilder().add("timestamp", "initial").build();	// 마지막으로 전송된 데이터를 저장

    @OnOpen
    public void onOpen(Session session) {
        sessions.add(session); // 새로운 클라이언트 세션을 추가
        System.out.println("Open Connection ...");
        startDataPolling(); // 데이터 폴링 시작
    }

    @OnClose
    public void onClose(Session session) {
        sessions.remove(session); // 클라이언트 세션을 제거
        System.out.println("Close Connection ...");
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        System.out.println("Message from Client: " + message);
        JsonObject response = Json.createObjectBuilder()
            .add("message", "Hello from Server!") // 클라이언트에게 응답 메시지를 생성
            .build();
        session.getBasicRemote().sendText(response.toString()); // 응답 메시지를 클라이언트에게 전송
    }

    private void startDataPolling() {
    	// 일정 간격으로 최신 날씨 데이터를 가져오는 작업을 예약
        scheduler.scheduleAtFixedRate(() -> {
            try {
                JsonObject latestData = getLatestWeatherData(); // 최신 날씨 데이터를 가져옴
                if (!latestData.equals(lastSentData)) { // 데이터가 변경된 경우에만 전송
                    lastSentData = latestData;
                    String jsonResponse = latestData.toString();
                    for (Session session : sessions) {
                        if (session.isOpen()) {
                            session.getBasicRemote().sendText(jsonResponse); // 변경된 데이터를 모든 클라이언트에게 전송
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, 0, 10, TimeUnit.SECONDS); // 10초 간격으로 실행
    }

    private JsonObject getLatestWeatherData() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver"); // MySQL 드라이버 로드
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             Statement statement = connection.createStatement()) {

        	// 최신 날씨 데이터를 가져오는 SQL 쿼리
            String query = "SELECT * FROM Weather ORDER BY timestamp DESC LIMIT 1";
            try (ResultSet resultSet = statement.executeQuery(query)) {
                if (resultSet.next()) {
                	// JSON 객체로 변환하여 반환
                    return Json.createObjectBuilder()
                        .add("id", resultSet.getInt("id"))
                        .add("Temperature", resultSet.getDouble("Temperature"))
                        .add("Humidity", resultSet.getInt("Humidity"))
                        .add("Pressure", resultSet.getDouble("Pressure"))
                        .add("WindSpeed", resultSet.getDouble("WindSpeed"))
                        .add("WindDirection", resultSet.getInt("WindDirection"))
                        .add("timestamp", resultSet.getTimestamp("timestamp").toString())
                        .build();
                } else {
                	// 데이터가 없는 경우 에러 메시지를 반환
                    return Json.createObjectBuilder()
                        .add("status", "error")
                        .add("message", "No data found")
                        .build();
                }
            }
        }
    }
}
