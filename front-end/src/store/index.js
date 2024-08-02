import { createStore } from 'vuex';
import { VuexPersistence } from 'vuex-persist';

const vuexLocal = new VuexPersistence({
  storage: window.sessionStorage,
  reducer: (state) => ({
    id: state.id,
    allData: state.allData,
    tempData: state.tempData,
    presData: state.presData,
    humidityData: state.humidityData,
    windSpeedData: state.windSpeedData,
    windDirectionData: state.windDirectionData,
    Chartlabels: state.Chartlabels,
  })
});

// const directions = [
//   'N', 'NNE', 'NE', 'ENE', 'E', 'ESE', 'SE', 'SSE',
//   'S', 'SSW', 'SW', 'WSW', 'W', 'WNW', 'NW', 'NNW'
// ];

const directions = [
  '북', '북북동', '북동', '동북동', '동', '동남동', '남동', '남남동',
  '남', '남남서', '남서', '서남서', '서', '서북서', '북서', '북북서'
];

const store = createStore({
  state: {
    id: null,
    allData: null,
    tempData: [],
    presData: [],
    humidityData: null,
    windSpeedData: [],
    windDirectionData: null,
    Chartlabels: [],
    maxLength: 20
  },
  mutations: {
    setAllData(state, data) {
      state.allData = data;
    },
    setTempData(state, data) {
      state.tempData = data;
    },
    setPresData(state, data) {
      state.presData = data;
    },
    setHumidityData(state, data) {
      state.humidityData = data;
    },
    setWindSpeedData(state, data) {
      state.windSpeedData = data;
    },
    setWindDirectionData(state, data) {
      state.windDirectionData = data;
    },
    setChartLabels(state, labels) {
      state.Chartlabels = labels;
    },
    setID(state, id) {
      state.id = id;
    }
  },
  actions: {
    updateAllData({ commit, state }, data) {
      const { Temperature, Pressure, Humidity, WindSpeed, WindDirection, id } = data;

      const newLabels = [...state.Chartlabels, id];
      if (newLabels.length > state.maxLength) {
        newLabels.shift();
      }

      const updateDataArray = (dataArray, newData) => {
        const updatedArray = [...dataArray, newData];
        if (updatedArray.length > state.maxLength) {
          updatedArray.shift();
        }
        return updatedArray;
      };

      commit('setID', id);
      commit('setAllData', data);
      commit('setChartLabels', newLabels);
      commit('setHumidityData', Humidity);
      commit('setWindDirectionData', directions[WindDirection - 1]);
      commit('setTempData', updateDataArray(state.tempData, Temperature));
      commit('setPresData', updateDataArray(state.presData, Pressure));
      commit('setWindSpeedData', updateDataArray(state.windSpeedData, WindSpeed));
    }
  },
  getters: {
    id: state => state.id,
    allData: state => state.allData,
    windDirectionData: state => state.windDirectionData,

    // 라인 차트
    tempData: state => ({
      labels: state.Chartlabels,
      datasets: [{
        label: 'Temperature',   //데이터의 라벨
        data: state.tempData,   //리스트형
        borderWidth: 3,   //선의 굵기
        backgroundColor: 'rgb(255,255,255, 0.15)',   //FILL 시 채워지는 색
        borderColor: '#FFFFFF',   //라인의 색
        tension: 0.3,   //선의 텐션
        pointRadius: 3,   //특정 데이터 포인터의 크기
        fill: true   //Y축 0부터 데이터 까지 채워넣을것인지
      }]
    }),
    windSpeedData: state => ({
      labels: state.Chartlabels,
      datasets: [{
        label: 'Wind Speed',
        data: state.windSpeedData,
        borderWidth: 3,
        backgroundColor: 'rgb(255,255,255, 0.15)',
        borderColor: '#FFFFFF',
        tension: 0.3,
        pointRadius: 3,
        fill: true
      }]
    }),

    // 막대 차트 (라인 차트와 설정방법 같음)
    presData: state => ({
      labels: state.Chartlabels,
      datasets: [{
        label: 'Pressure',
        data: state.presData,
        borderWidth: 0,
        backgroundColor: '#FFFFFF',
        borderColor: '#FFFFFF',
        tension: 0.3,
        pointRadius: 0
      }]
    }),

    //도넛 차트
    humidityData: state => ({
      labels: ['Humidity', '.'],
      datasets: [{
        borderWidth: 2,
        backgroundColor: ["#FFFFFF", "#151515"],
        hoverBackgroundColor: ["#FFFFFF", "#000000"],
        hoverBorderColor: ["#FFFFFF", "#000000"],
        hoverBorderWidth: 0,
        data: [state.humidityData, 100 - state.humidityData]
      }]
    }),
  },
  plugins: [vuexLocal.plugin]
});

export default store;
