<template>
  <div class="container-fluid charts-container" style="margin-top: 7%;">
    <div class="row">
      <div class="col chart-box">
        Temperature
        <Line id="tempChart" :data="tempData" :options="chartOptions" />
      </div>
      <div class="col chart-box">
        Humidity
        <Line id="humidityChart" :data="humidityData" :options="chartOptions" />
      </div>
      <div class="col chart-box">
        Pressure
        <Line id="pressureChart" :data="presData" :options="chartOptions" />
      </div>
    </div>
    <div class="row">
      <div class="col chart-box">
        WindSpeed
        <Line id="windSpeedChart" :data="windSpeedData" :options="chartOptions" />
      </div>
      <div class="col chart-box">
        WindDirection
        <Line id="windDirChart" :data="windDirectionData" :options="chartOptions" />
      </div>
    </div>
  </div>
</template>

<script>
import 'chart.js/auto';
import { Line } from 'vue-chartjs';
import { Chart as ChartJS, Title, Tooltip, Legend, BarElement, CategoryScale, LinearScale, PointElement } from 'chart.js';
import { mapGetters } from 'vuex';

ChartJS.register(PointElement, Title, Tooltip, Legend, BarElement, CategoryScale, LinearScale);

export default {
  name: 'Realtime-Charts',
  components: { Line },
  computed: {
    ...mapGetters([
      'tempData',
      'presData',
      'humidityData',
      'windSpeedData',
      'windDirectionData'
    ])
  },
  data() {
    return {
      chartOptions: {
        plugins: {
          legend: {
            display: false
          }
        },
        interaction: {
          intersect: false, // hover 기능. 정보를 보여줌 false일 때 보여준다.
        },
        responsive: true,
        scales: {
          y: {
            beginAtZero: true,
            ticks: {
              color: '#000'
            },
            border: {
              dash: [5, 10]
            }
          },
          x: {
            grid: {
              display: false, //차트의 x축 선 표시
            },
            border: {
              display: false, // 차트의 x축 하단 border line 표시
            },
            ticks: {
              color: '#C0C0C0'
            }
          }
        }
      }
    };
  }
};
</script>

<style>
.container-fluid {
  padding: 0px;
}

.charts-container {
  flex: 1;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

.row {
  margin: 10px;
}

.col {
  background-color: #fff;
  border: 1px solid #333;
  padding: 0;
  border-radius: 10px;
  text-align: center;
  color: #000;
}

.chart-box {
  padding: 10px;
}
</style>
