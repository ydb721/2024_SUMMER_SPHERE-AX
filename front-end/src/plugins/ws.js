import store from '../store/index.js';

const connectWebSocket = () => {
  const ws = new WebSocket(`ws://${process.env.VUE_APP_BACKEND_SERVER_IP}:${process.env.VUE_APP_BACKEND_SERVER_PORT}/testweb/websocket`);

  ws.onmessage = (event) => {
    try {
      const Data = JSON.parse(event.data);
      store.dispatch('updateAllData', Data);
    } catch (e) {
      console.error('Error parsing JSON:', e.message);
    }
  };

  ws.onopen = () => {
    console.log('Connected to server ..');
  };

  ws.onerror = (event) => {
    console.error('Error occurred:', event);
  };

  ws.onclose = () => {
    console.log('Disconnected ... Trying to reconnect in 5 seconds...');
    setTimeout(connectWebSocket, 5000);
  };
};

connectWebSocket();
