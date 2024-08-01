import { createStore } from 'vuex';
import { VuexPersistence } from 'vuex-persist';

const vuexLocal = new VuexPersistence({
  storage: window.sessionStorage,
  reducer: (state) => ({
    tempData: state.tempData,
    presData: state.presData,
    humidityData: state.humidityData,
    windSpeedData: state.windSpeedData,
    windDirectionData: state.windDirectionData,
    Chartlabels: state.Chartlabels,
  })
})

const store = createStore({
  state: {
    id: null,
    allData: null,
    tempData: [],
    presData: [],
    humidityData: [],
    windSpeedData: [],
    windDirectionData: [],
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
      commit('setTempData', updateDataArray(state.tempData, Temperature));
      commit('setPresData', updateDataArray(state.presData, Pressure));
      commit('setHumidityData', updateDataArray(state.humidityData, Humidity));
      commit('setWindSpeedData', updateDataArray(state.windSpeedData, WindSpeed));
      commit('setWindDirectionData', updateDataArray(state.windDirectionData, WindDirection));
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
        borderColor: '#0000FF',
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
        borderColor: '#0000FF',
        tension: 0.3,
        pointRadius: 0
      }]
    }),
    humidityData: state => ({
      labels: state.Chartlabels,
      datasets: [{
        label: 'Humidity',
        data: state.humidityData,
        borderWidth: 3,
        backgroundColor: '#0000FF',
        borderColor: '#0000FF',
        tension: 0.3,
        pointRadius: 0
      }]
    }),
    windSpeedData: state => ({
      labels: state.Chartlabels,
      datasets: [{
        label: 'Wind Speed',
        data: state.windSpeedData,
        borderWidth: 3,
        backgroundColor: '#0000FF',
        borderColor: '#0000FF',
        tension: 0.3,
        pointRadius: 0
      }]
    }),
    windDirectionData: state => ({
      labels: state.Chartlabels,
      datasets: [{
        label: 'Wind Direction',
        data: state.windDirectionData,
        borderWidth: 3,
        backgroundColor: '#0000FF',
        borderColor: '#0000FF',
        tension: 0.3,
        pointRadius: 0
      }]
    })
  },
  plugins: [vuexLocal.plugin]
});

export default store;