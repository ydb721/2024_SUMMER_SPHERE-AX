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

@ServerEndpoint("/websocket")
public class WebSocketServer {

    private static final String DB_URL = System.getenv("DB_URL");		// 자신의 MySQL 테이블명 ex)jdbc:mysql://localhost:3306/myappdb
    private static final String DB_USER = System.getenv("DB_USER");		// 자신의 MySQL 유저명
    private static final String DB_PASS = System.getenv("DB_PASS");		// 자신의 MySQL 비밀번호
    
    private static final Set<Session> sessions = new HashSet<>();
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    private static JsonObject lastSentData = Json.createObjectBuilder().add("timestamp", "initial").build();

    @OnOpen
    public void onOpen(Session session) {
        sessions.add(session);
        System.out.println("Open Connection ...");
        startDataPolling();
    }

    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
        System.out.println("Close Connection ...");
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        System.out.println("Message from Client: " + message);
        JsonObject response = Json.createObjectBuilder()
            .add("message", "Hello from Server!")
            .build();
        session.getBasicRemote().sendText(response.toString());
    }

    private void startDataPolling() {
        scheduler.scheduleAtFixedRate(() -> {
            try {
                JsonObject latestData = getLatestWeatherData();
                if (!latestData.equals(lastSentData)) {
                    lastSentData = latestData;
                    String jsonResponse = latestData.toString();
                    for (Session session : sessions) {
                        if (session.isOpen()) {
                            session.getBasicRemote().sendText(jsonResponse);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, 0, 10, TimeUnit.SECONDS); // Adjust polling interval as needed
    }

    private JsonObject getLatestWeatherData() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             Statement statement = connection.createStatement()) {

            String query = "SELECT * FROM Weather ORDER BY timestamp DESC LIMIT 1";
            try (ResultSet resultSet = statement.executeQuery(query)) {
                if (resultSet.next()) {
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
                    return Json.createObjectBuilder()
                        .add("status", "error")
                        .add("message", "No data found")
                        .build();
                }
            }
        }
    }
}
