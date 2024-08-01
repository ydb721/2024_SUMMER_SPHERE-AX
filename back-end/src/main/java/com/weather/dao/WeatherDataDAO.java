package com.weather.dao;

import com.weather.model.WeatherData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class WeatherDataDAO {

    private JdbcTemplate jdbcTemplate;

    // 기본 생성자
    public WeatherDataDAO() {
    }

    // 생성자 주입 (필요 시)
    @Autowired
    public WeatherDataDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // setter 주입
    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 날씨 데이터를 데이터베이스에 저장하는 메서드
    public void saveWeatherData(WeatherData data) {
        String sql = "INSERT INTO Weather (temperature, humidity, pressure, windspeed, winddirection, timestamp) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
            data.getTemperature(),
            data.getHumidity(),
            data.getPressure(),
            data.getWindSpeed(),
            data.getWindDirection(),
            data.getTimestamp()
        );
    }

    // 가장 최근의 날씨 데이터를 가져오는 메서드
    public List<WeatherData> getMostRecentWeatherData() {
        String sql = "SELECT * FROM Weather ORDER BY timestamp DESC LIMIT 1";
        return jdbcTemplate.query(sql, new WeatherDataRowMapper());
    }

    // RowMapper 구현
    private static class WeatherDataRowMapper implements RowMapper<WeatherData> {
        @Override
        public WeatherData mapRow(ResultSet rs, int rowNum) throws SQLException {
            WeatherData weatherData = new WeatherData();
            weatherData.setTemperature(rs.getDouble("temperature"));
            weatherData.setHumidity(rs.getInt("humidity"));
            weatherData.setPressure(rs.getDouble("pressure"));
            weatherData.setWindSpeed(rs.getDouble("windspeed"));
            weatherData.setWindDirection(rs.getInt("winddirection"));
            weatherData.setTimestamp(rs.getTimestamp("timestamp")); // 수정: Timestamp로 설정
            return weatherData;
        }
    }
}
