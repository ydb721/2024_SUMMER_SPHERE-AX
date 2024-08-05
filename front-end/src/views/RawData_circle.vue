<template>
  <div class="frame-7">
  <div class="frame-6">
    <div class="temperature">
      <div class="temperature-text">Temperature</div>
      <div class="frame-8">
        <div class="c">{{ displayedData.allData.Temperature }} °C</div>
      </div>
    </div>
    <div class="humidity">
      <div class="humidity-text">Humidity</div>
      <div class="frame-8">
        <div class="c">{{ displayedData.allData.Humidity }} %</div>
      </div>
    </div>
    <div class="pressure">
      <div class="pressure-text">Pressure</div>
      <div class="frame-8">
        <div class="c">{{ displayedData.allData.Pressure }} hPa</div>
      </div>
    </div>
    <div class="wind-speed">
      <div class="wind-speed2">Wind Speed</div>
      <div class="frame-8">
        <div class="c">{{ displayedData.allData.WindSpeed }} m / s</div>
      </div>
    </div>
    <div class="wind-direction">
      <div class="wind-direction-text">Wind Direction</div>
      <div class="frame-8">
        <div class="c">{{ displayedData.windDirectionData }} Dir</div>
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

.frame-7,
.frame-7 * {
  box-sizing: border-box;
}
.frame-7 {
  display: flex;
  flex-direction: column;
  gap: 77px;
  align-items: center;
  justify-content: center;
  height: 883px;
  position: relative;
}
.frame-6 {
  display: flex;
  flex-direction: row;
  gap: 258px;
  row-gap: 0px;
  align-items: center;
  justify-content: center;
  flex-wrap: wrap;
  align-content: center;
  flex-shrink: 0;
  width: 1440px;
  height: 735px;
  position: relative;
}
.temperature {
  display: flex;
  flex-direction: column;
  gap: 7px;
  row-gap: 0px;
  align-items: flex-start;
  justify-content: flex-start;
  flex-shrink: 0;
  width: 308px;
  position: relative;
}
.temperature-text {
  color: #ffffff;
  text-align: center;
  font-family: "Arial-Black", sans-serif;
  font-size: 32px;
  font-weight: 900;
  position: relative;
  align-self: stretch;
  height: 70px;
}
.frame-8 {
  background: rgba(217, 217, 217, 0.1);
  border-radius: 154px;
  border-style: solid;
  border-color: #ffffff;
  border-width: 6px;
  padding: 128px 0px 128px 0px;
  display: flex;
  flex-direction: column;
  gap: 10px;
  row-gap: 0px;
  align-items: center;
  justify-content: center;
  align-self: stretch;
  flex-shrink: 0;
  height: 308px;
  position: relative;
}
.c {
  color: #ffffff;
  text-align: center;
  font-family: "Arial-Black", sans-serif;
  font-size: 32px;
  font-weight: 900;
  position: relative;
}
.humidity {
  display: flex;
  flex-direction: column;
  gap: 7px;
  row-gap: 0px;
  align-items: flex-start;
  justify-content: flex-start;
  flex-shrink: 0;
  width: 308px;
  position: relative;
}
.humidity-text {
  color: #ffffff;
  text-align: center;
  font-family: "Arial-Black", sans-serif;
  font-size: 32px;
  font-weight: 900;
  position: relative;
  align-self: stretch;
  height: 70px;
}
.pressure {
  display: flex;
  flex-direction: column;
  gap: 7px;
  row-gap: 0px;
  align-items: flex-start;
  justify-content: flex-start;
  flex-shrink: 0;
  width: 308px;
  position: relative;
}
.pressure-text {
  color: #ffffff;
  text-align: center;
  font-family: "Arial-Black", sans-serif;
  font-size: 32px;
  font-weight: 900;
  position: relative;
  align-self: stretch;
  height: 70px;
}
.wind-speed {
  display: flex;
  flex-direction: column;
  gap: 7px;
  row-gap: 0px;
  align-items: flex-start;
  justify-content: flex-start;
  flex-shrink: 0;
  width: 308px;
  position: relative;
}
.wind-speed2 {
  color: #ffffff;
  text-align: center;
  font-family: "Arial-Black", sans-serif;
  font-size: 32px;
  font-weight: 900;
  position: relative;
  align-self: stretch;
  height: 70px;
}
.wind-direction {
  display: flex;
  flex-direction: column;
  gap: 7px;
  row-gap: 0px;
  align-items: flex-start;
  justify-content: flex-start;
  flex-shrink: 0;
  width: 308px;
  position: relative;
}
.wind-direction-text {
  color: #ffffff;
  text-align: center;
  font-family: "Arial-Black", sans-serif;
  font-size: 32px;
  font-weight: 900;
  position: relative;
  align-self: stretch;
  height: 70px;
}

</style>
