<template>
  <div class="row">
    <div class="col-md-6 chart-box text-center d-flex justify-content-center align-items-center">
      <span class="current-data-id">Current Data ID : {{ displayedData.id }}</span>
    </div>
    <div class="col-md-6 chart-box text-center d-flex justify-content-center align-items-center">
      <span class="current-data-id">Wind Direction : {{ displayedData.windDirectionData }}</span>
    </div>
  </div>
  <div class="container-fluid charts-container">
    <div class="row">
      <div class="col-md-6 chart-box">
        <div class="chart-title">Temperature (°C)</div>
        <Line id="tempChart" :data="tempData" :options="chartOptions" />
      </div>
      <div class="col-md-6 chart-box">
        <div class="chart-title">Humidity (%)</div>
        <Doughnut id="humidityChart" :data="humidityData" :options="humidityOptions" />
      </div>
    </div>
    <div class="row">
      <div class="col-md-6 chart-box">
        <div class="chart-title">Pressure (hPa)</div>
        <Line id="pressureChart" :data="presData" :options="chartOptions" />
      </div>
      <div class="col-md-6 chart-box">
        <div class="chart-title">WindSpeed (m / s)</div>
        <Line id="windSpeedChart" :data="windSpeedData" :options="chartOptions" />
      </div>
    </div>
  </div>
</template>


<script>
import 'chart.js/auto';
import { Line, Doughnut } from 'vue-chartjs';
import { Chart as ChartJS, Title, Tooltip, Legend, BarElement, CategoryScale, LinearScale, PointElement } from 'chart.js';
import { mapGetters } from 'vuex';

ChartJS.register(PointElement, Title, Tooltip, Legend, BarElement, CategoryScale, LinearScale);

export default {
  name: 'Realtime-Charts',
  components: { Line, Doughnut },
  computed: {
    ...mapGetters([
      'tempData',   //기온, 0 ~ 40
      'presData',   //기압, 998.5 ~ 1003.3
      'humidityData',   //습도, 0 ~ 100
      'windSpeedData',    //풍량, 0 ~ 34.6
      'windDirectionData',    //풍향, 1 ~ 16
      'id'
    ]),
    displayedData() {
      return {
        id: this.id ?? 0,
        windDirectionData: this.windDirectionData ?? 'none'
      };
    }
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
            beginAtZero: false,
            ticks: {
              color: '#FFFFFF'
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
      },
      humidityOptions: {
        plugins: {
          legend: {
            display: false
          }
        },
        interaction: {
          intersect: true, // hover 기능. 정보를 보여줌 false일 때 보여준다.
        },
        responsive: true,
        rotation: -90, // 반원 시작 각도 설정
        circumference: 180, // 반원 그리기 (180도)
        cutout: '50%', // 도넛 형태를 유지
        aspectRatio: 2, // 반원 형태를 유지비율
        scales: {
          x: {
            grid: {
              display: false,
            },
            border: {
              display: false,
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
  width: 100%;
  height: 10%;
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

.chart-title {
  font-size: 1.2rem;
  font-weight: bold;
  margin-bottom: 10px;
}

.current-data-id {
  font-size: 1.5rem;
  font-weight: bold;
}
</style>
