<template>
  <div class="frame-16" style="justify-content: center; margin-top: 10vh;">
    <div class="frame-9">
      <div class="frame-4">
        <div class="nav-bar">
          <div class="nav">Temperature</div>
        </div>
      </div>
      <div class="frame-92">
        <div class="nav-bar">
          <div class="nav">Humidity</div>
        </div>
      </div>
      <div class="frame-6">
        <div class="nav-bar">
          <div class="nav">Pressure</div>
        </div>
      </div>
      <div class="frame-7">
        <div class="nav-bar">
          <div class="nav">Wind Speed</div>
        </div>
      </div>
      <div class="frame-8">
        <div class="nav-bar">
          <div class="nav">Wind Direction</div>
        </div>
      </div>
    </div>
    <div class="frame-12">
      <div class="frame-10">
        <div class="frame-4">
          <div class="nav-bar">
            <div class="nav">{{ displayedData.allData.Temperature }}</div>
          </div>
        </div>
        <div class="frame-92">
          <div class="nav-bar">
            <div class="nav">{{ displayedData.allData.Humidity }}</div>
          </div>
        </div>
        <div class="frame-6">
          <div class="nav-bar">
            <div class="nav">{{ displayedData.allData.Pressure }}</div>
          </div>
        </div>
        <div class="frame-7">
          <div class="nav-bar">
            <div class="nav">{{ displayedData.allData.WindSpeed }}</div>
          </div>
        </div>
        <div class="frame-8">
          <div class="nav-bar">
            <div class="nav">{{ displayedData.windDirectionData }}</div>
          </div>
        </div>
      </div>
      <div class="frame-11">
        <div class="frame-4">
          <div class="nav-bar">
            <div class="nav">°C</div>
          </div>
        </div>
        <div class="frame-92">
          <div class="nav-bar">
            <div class="nav">%</div>
          </div>
        </div>
        <div class="frame-6">
          <div class="nav-bar">
            <div class="nav">hPa</div>
          </div>
        </div>
        <div class="frame-7">
          <div class="nav-bar">
            <div class="nav">m / s</div>
          </div>
        </div>
        <div class="frame-8">
          <div class="nav-bar">
            <div class="nav">향</div>
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

.frame-16,
.frame-16 * {
  box-sizing: border-box;
}

.frame-16 {
  display: flex;
  flex-direction: row;
  gap: 200px;
  align-items: center;
  justify-content: flex-start;
  position: relative;
}

.frame-9 {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-between;
  flex-shrink: 0;
  width: 305px;
  height: 718px;
  position: relative;
}

.frame-4 {
  padding: 10px;
  display: flex;
  flex-direction: column;
  gap: 10px;
  align-items: flex-start;
  justify-content: flex-start;
  align-self: stretch;
  flex-shrink: 0;
  position: relative;
}

.nav-bar {
  display: flex;
  flex-direction: row;
  gap: 0px;
  align-items: center;
  justify-content: center;
  align-self: stretch;
  flex-shrink: 0;
  position: relative;
}

.nav {
  background: linear-gradient(90deg,
      rgba(255, 255, 255, 1) 0%,
      rgba(199, 199, 199, 1) 100%);
  background-clip: text;
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  text-align: left;
  font-family: "Inter-Bold", sans-serif;
  font-size: 24px;
  font-weight: 700;
  position: relative;
}

.frame-92 {
  padding: 10px;
  display: flex;
  flex-direction: column;
  gap: 10px;
  align-items: flex-start;
  justify-content: flex-start;
  align-self: stretch;
  flex-shrink: 0;
  position: relative;
}

.frame-6 {
  padding: 10px;
  display: flex;
  flex-direction: column;
  gap: 10px;
  align-items: flex-start;
  justify-content: flex-start;
  align-self: stretch;
  flex-shrink: 0;
  position: relative;
}

.frame-7 {
  padding: 10px;
  display: flex;
  flex-direction: column;
  gap: 10px;
  align-items: flex-start;
  justify-content: flex-start;
  align-self: stretch;
  flex-shrink: 0;
  position: relative;
}

.frame-8 {
  padding: 10px;
  display: flex;
  flex-direction: column;
  gap: 10px;
  align-items: flex-start;
  justify-content: flex-start;
  align-self: stretch;
  flex-shrink: 0;
  position: relative;
}

.frame-12 {
  display: flex;
  flex-direction: row;
  gap: 0px;
  align-items: center;
  justify-content: flex-start;
  flex-shrink: 0;
  width: 449px;
  height: 718px;
  position: relative;
}

.frame-10 {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-between;
  flex-shrink: 0;
  width: 329px;
  height: 718px;
  position: relative;
}

.frame-11 {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-between;
  flex-shrink: 0;
  width: 82px;
  height: 718px;
  position: relative;
}
</style>
