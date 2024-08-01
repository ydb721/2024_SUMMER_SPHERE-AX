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
    allData: state => state.allData,
    id: state => state.id,
    tempData: state => ({
      labels: state.Chartlabels,
      datasets: [{
        label: 'Temperature',
        data: state.tempData,
        borderWidth: 3,
        backgroundColor: '#0000FF',
        borderColor: '#FFFFFF',
        tension: 0.3,
        pointRadius: 0
      }]
    }),
    presData: state => ({
      labels: state.Chartlabels,
      datasets: [{
        label: 'Pressure',
        data: state.presData,
        borderWidth: 3,
        backgroundColor: '#0000FF',
        borderColor: '#FFFFFF',
        tension: 0.3,
        pointRadius: 0
      }]
    }),
    humidityData: state => ({
      labels: ['Humidity', '.'],
      datasets: [{
        borderWidth: 0,
        backgroundColor: ["#FFFFFF", "#151515"],
        hoverBackgroundColor: ["#FFFFFF", "#000000"],
        hoverBorderColor: ["#FFFFFF", "#000000"],
        hoverBorderWidth: 0,
        data: [state.humidityData, 100 - state.humidityData]
      }]
    }),
    windSpeedData: state => ({
      labels: state.Chartlabels,
      datasets: [{
        label: 'Wind Speed',
        data: state.windSpeedData,
        borderWidth: 3,
        backgroundColor: '#0000FF',
        borderColor: '#FFFFFF',
        tension: 0.3,
        pointRadius: 0
      }]
    }),
    windDirectionData: state => state.windDirectionData
  },
  plugins: [vuexLocal.plugin]
});

export default store;
