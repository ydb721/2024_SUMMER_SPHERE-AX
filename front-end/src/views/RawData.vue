<template>
<div class="desktop-2">
  <div class="frame-9">
    <img class="line-1" src="../assets/rawdata-img/line-10.svg" />
    <div class="real-time-weather-data">
      Real-Time
      <br />
      Weather Data
    </div>
  </div>
  <div class="frame-21">
    <div class="temp">
      <div class="frame-13">
        <div class="frame">
          <img class="temperature" src="../assets/rawdata-img/temperature0.png" />
          <div class="temperature2">Temperature</div>
        </div>
        <div class="temp-data">
          <div class="_100-c">100 °C</div>
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
          <div class="_70">70 %</div>
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
          <div class="_998-7-h-pa">998.7 hPa</div>
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
          <div class="dir2">동남동 Dir</div>
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
          <div class="_15-7-m-s">15.7 m / s</div>
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

.desktop-2,
.desktop-2 * {
  box-sizing: border-box;
}
.desktop-2 {
  background: #000000;
  position: relative;
  height: 80vh;
  overflow: hidden;
}
.frame-9 {
  display: flex;
  flex-direction: row;
  gap: 28px;
  row-gap: 0px;
  align-items: flex-start;
  justify-content: flex-end;
  width: 250px;
  position: absolute;
  left: calc(50% - 695px);
  top: 17px;
}
.line-1 {
  flex-shrink: 0;
  width: 411px;
  height: 0px;
  position: relative;
  overflow: visible;
}
.real-time-weather-data {
  color: #ffffff;
  text-align: left;
  font-family: "Arial-Black", sans-serif;
  font-size: 30px;
  font-weight: 900;
  position: absolute;
  right: 28px;
  left: 0px;
  top: 0px;
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
  left: calc(50% - 403px);
  top: 39px;
}
.temp {
  background: rgba(217, 217, 217, 0);
  padding: 27px 35px 27px 35px;
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
.frame-13 {
  display: flex;
  flex-direction: column;
  gap: 1px;
  row-gap: 0px;
  align-items: center;
  justify-content: flex-start;
  flex-shrink: 0;
  width: 267px;
  position: relative;
}
.frame {
  display: flex;
  flex-direction: row;
  gap: 10px;
  row-gap: 0px;
  align-items: flex-start;
  justify-content: center;
  flex-shrink: 0;
  width: 267px;
  position: relative;
}
.temperature {
  flex-shrink: 0;
  width: 47px;
  height: 47px;
  position: relative;
  object-fit: cover;
}
.temperature2 {
  color: #ffffff;
  text-align: left;
  font-family: "Arial-Black", sans-serif;
  font-size: 30px;
  font-weight: 900;
  position: relative;
}
.temp-data {
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
._100-c {
  color: #ffffff;
  text-align: center;
  font-family: "Arial-Black", sans-serif;
  font-size: 30px;
  font-weight: 900;
  position: relative;
}
.hum {
  background: rgba(217, 217, 217, 0);
  padding: 27px 64px 27px 64px;
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
.frame-16 {
  display: flex;
  flex-direction: column;
  gap: 1px;
  row-gap: 0px;
  align-items: center;
  justify-content: flex-start;
  flex-shrink: 0;
  width: 209px;
  position: relative;
}
.frame-14 {
  display: flex;
  flex-direction: row;
  gap: 10px;
  row-gap: 0px;
  align-items: flex-start;
  justify-content: center;
  align-self: stretch;
  flex-shrink: 0;
  position: relative;
}
.hygrometer {
  flex-shrink: 0;
  width: 47px;
  height: 47px;
  position: relative;
  object-fit: cover;
}
.humidity {
  color: #ffffff;
  text-align: left;
  font-family: "Arial-Black", sans-serif;
  font-size: 30px;
  font-weight: 900;
  position: relative;
}
.hum-data {
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
._70 {
  color: #ffffff;
  text-align: center;
  font-family: "Arial-Black", sans-serif;
  font-size: 30px;
  font-weight: 900;
  position: relative;
}
.press {
  background: rgba(217, 217, 217, 0);
  padding: 27px 67px 27px 67px;
  display: flex;
  flex-direction: column;
  gap: 10px;
  row-gap: 0px;
  align-items: flex-start;
  justify-content: flex-start;
  flex-shrink: 0;
  width: 338px;
  height: 164px;
  position: relative;
}
.frame-18 {
  display: flex;
  flex-direction: column;
  gap: 1px;
  row-gap: 0px;
  align-items: center;
  justify-content: flex-start;
  flex-shrink: 0;
  width: 204px;
  position: relative;
}
.frame-17 {
  display: flex;
  flex-direction: row;
  gap: 10px;
  row-gap: 0px;
  align-items: flex-start;
  justify-content: center;
  align-self: stretch;
  flex-shrink: 0;
  position: relative;
}
.pressure-gauge {
  flex-shrink: 0;
  width: 47px;
  height: 47px;
  position: relative;
  object-fit: cover;
}
.pressure {
  color: #ffffff;
  text-align: left;
  font-family: "Arial-Black", sans-serif;
  font-size: 30px;
  font-weight: 900;
  position: relative;
}
.pres-data {
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
._998-7-h-pa {
  color: #ffffff;
  text-align: center;
  font-family: "Arial-Black", sans-serif;
  font-size: 30px;
  font-weight: 900;
  position: relative;
}
.dir {
  background: rgba(217, 217, 217, 0);
  padding: 27px 16px 27px 16px;
  display: flex;
  flex-direction: column;
  gap: 10px;
  row-gap: 0px;
  align-items: center;
  justify-content: flex-start;
  flex-shrink: 0;
  width: 338px;
  height: 164px;
  position: relative;
}
.frame-20 {
  display: flex;
  flex-direction: column;
  gap: 1px;
  row-gap: 0px;
  align-items: center;
  justify-content: flex-start;
  flex-shrink: 0;
  width: 305px;
  position: relative;
}
.frame-172 {
  display: flex;
  flex-direction: row;
  gap: 10px;
  row-gap: 0px;
  align-items: flex-start;
  justify-content: center;
  flex-shrink: 0;
  width: 300px;
  position: relative;
}
.north-direction {
  flex-shrink: 0;
  width: 47px;
  height: 47px;
  position: relative;
  object-fit: cover;
}
.wind-direction {
  color: #ffffff;
  text-align: left;
  font-family: "Arial-Black", sans-serif;
  font-size: 30px;
  font-weight: 900;
  position: relative;
}
.ws-data {
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
.dir2 {
  color: #ffffff;
  text-align: center;
  font-family: "Arial-Black", sans-serif;
  font-size: 30px;
  font-weight: 900;
  position: relative;
}
.wind-speed {
  background: rgba(217, 217, 217, 0);
  padding: 27px 67px 27px 67px;
  display: flex;
  flex-direction: column;
  gap: 10px;
  row-gap: 0px;
  align-items: center;
  justify-content: flex-start;
  flex-shrink: 0;
  width: 338px;
  height: 164px;
  position: relative;
}
.frame-19 {
  display: flex;
  flex-direction: column;
  gap: 1px;
  row-gap: 0px;
  align-items: center;
  justify-content: flex-start;
  flex-shrink: 0;
  width: 250px;
  position: relative;
}
.frame-173 {
  display: flex;
  flex-direction: row;
  gap: 10px;
  row-gap: 0px;
  align-items: flex-start;
  justify-content: center;
  flex-shrink: 0;
  width: 250px;
  position: relative;
}
.wind-speed-18-22 {
  flex-shrink: 0;
  width: 47px;
  height: 47px;
  position: relative;
  object-fit: cover;
}
.wind-speed2 {
  color: #ffffff;
  text-align: left;
  font-family: "Arial-Black", sans-serif;
  font-size: 30px;
  font-weight: 900;
  position: relative;
}
._15-7-m-s {
  color: #ffffff;
  text-align: center;
  font-family: "Arial-Black", sans-serif;
  font-size: 30px;
  font-weight: 900;
  position: relative;
}


</style>
