<template>
  <div class="main">
  <div class="frame-21">
    <div class="temp">
      <div class="frame-13">
        <div class="frame">
          <img class="temperature" src="../assets/rawdata-img/temperature0.png" />
          <div class="temperature2">Temperature</div>
        </div>
        <div class="temp-data">
          <div class="_100-c">{{ displayedData.allData.Temperature }} °C</div>
        </div>
      </div>
    </div>
    <div class="hum">
      <div class="frame-16">
        <div class="frame-14">
          <img class="hygrometer" src="../assets/rawdata-img/hygrometer0.png" />
          <div class="humidity">Humidity</div>
        </div>
        <div class="hum-data">
          <div class="_70">{{ displayedData.allData.Humidity }} %</div>
        </div>
      </div>
    </div>
    <div class="press">
      <div class="frame-18">
        <div class="frame-17">
          <img class="pressure-gauge" src="../assets/rawdata-img/pressure-gauge0.png" />
          <div class="pressure">Pressure</div>
        </div>
        <div class="pres-data">
          <div class="_998-7-h-pa">{{ displayedData.allData.Pressure }} hPa</div>
        </div>
      </div>
    </div>
    <div class="dir">
      <div class="frame-20">
        <div class="frame-172">
          <img class="north-direction" src="../assets/rawdata-img/north-direction0.png" />
          <div class="wind-direction">Wind Direction</div>
        </div>
        <div class="ws-data">
          <div class="dir2">{{ displayedData.windDirectionData }} 향</div>
        </div>
      </div>
    </div>
    <div class="wind-speed">
      <div class="frame-19">
        <div class="frame-173">
          <img class="wind-speed-18-22" src="../assets/rawdata-img/wind-speed-18-220.png" />
          <div class="wind-speed2">Wind Speed</div>
        </div>
        <div class="ws-data">
          <div class="_15-7-m-s">{{ displayedData.allData.WindSpeed }} m / s</div>
        </div>
      </div>
    </div>
  </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';

export default {
  name: 'RawData',
  computed: {
    ...mapGetters([
      'allData',
      'windDirectionData'
    ]),
    displayedData() {
      return {
        allData: this.allData ?? {
          Temperature: 0,
          Humidity: 0,
          Pressure: 0,
          WindSpeed: 0,
          WindDirection: 0
        },
        windDirectionData: this.windDirectionData ?? 'none'
      };
    }
  }
};
</script>

<style>
@import '../vars.css';

.main {
  display: flex;
  justify-content: center; /* 수평 중앙 정렬 */
  align-items: center;     /* 수직 중앙 정렬 */
  height: 80vh;
  margin: 0;
}

.frame-21 {
  display: flex;
  flex-direction: row;
  gap: 42px;
  row-gap: 41px;
  align-items: flex-start;
  justify-content: flex-start;
  flex-wrap: wrap;
  align-content: flex-start;
  width: 1098px;
  position: absolute;
  top: 20vh;
}

.temp, .hum, .press, .dir, .wind-speed {
  background: rgba(217, 217, 217, 0);
  padding: 27px;
  display: flex;
  flex-direction: column;
  gap: 10px;
  row-gap: 0px;
  align-items: center;
  justify-content: center;
  flex-shrink: 0;
  width: 338px;
  height: 164px;
  position: relative;
}

.frame-13, .frame-16, .frame-18, .frame-20, .frame-19 {
  display: flex;
  flex-direction: column;
  gap: 1px;
  row-gap: 0px;
  align-items: center;
  justify-content: flex-start;
  flex-shrink: 0;
  position: relative;
}

.frame, .frame-14, .frame-17, .frame-172, .frame-173 {
  display: flex;
  flex-direction: row;
  gap: 10px;
  row-gap: 0px;
  align-items: flex-start;
  justify-content: center;
  flex-shrink: 0;
  position: relative;
}

.temperature, .hygrometer, .pressure-gauge, .north-direction, .wind-speed-18-22 {
  flex-shrink: 0;
  width: 47px;
  height: 47px;
  position: relative;
  object-fit: cover;
}

.temperature2, .humidity, .pressure, .wind-direction, .wind-speed2 {
  color: #ffffff;
  text-align: left;
  font-family: "Arial-Black", sans-serif;
  font-size: 30px;
  font-weight: 900;
  position: relative;
}

.temp-data, .hum-data, .pres-data, .ws-data {
  padding: 10px;
  display: flex;
  flex-direction: row;
  gap: 10px;
  row-gap: 0px;
  align-items: center;
  justify-content: center;
  flex-shrink: 0;
  position: relative;
}

._100-c, ._70, ._998-7-h-pa, .dir2, ._15-7-m-s {
  color: #ffffff;
  text-align: center;
  font-family: "Arial-Black", sans-serif;
  font-size: 30px;
  font-weight: 900;
  position: relative;
}
</style>
