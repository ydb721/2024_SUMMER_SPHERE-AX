import store from '../store/index.js';

const ws = new WebSocket(`ws://${process.env.BACKEND_SERVER_IP}:${process.env.BACKEND_SERVER_PORT}/testweb/websocket`);

console.log("Waiting ...");

ws.onmessage = (event) => {
  try {
    const Data = JSON.parse(event.data);
    console.log('WeatherData received:', Data);
    store.dispatch('updateAllData', Data);
  } catch (e) {
    console.error('Error parsing JSON:', e.message);
  }
};

ws.onopen = () => {
  console.log('Connected ...');
};

ws.onerror = (event) => {
  console.error('Error occurred:', event);
};

ws.onclose = () => {
  console.log('Disconnected ...');
};