import { createRouter, createWebHistory } from 'vue-router';
import Home from '../views/Home.vue';
import AboutUs from '../views/AboutUs.vue';
import RawData from '../views/RawData.vue';
import Charts from '../views/Charts.vue';
import MainLayout from '../components/MainLayout.vue';

const routes = [
  {
    path: '/',
    component: MainLayout,
    children: [
      {
        path: '',
        component: Home
      },
      {
        path: '/aboutUs',
        component: AboutUs
      },
      {
        path: '/rawData',
        component: RawData,
        props: (route) => ({
          allData: route.params.allData
        })
      },
      {
        path: '/charts',
        component: Charts,
        props: (route) => ({
          tempData: route.params.tempData,
          presData: route.params.presData,
          humidityData: route.params.humidityData,
          windSpeedData: route.params.windSpeedData,
          windDirectionData: route.params.windDirectionData
        })
      }
    ]
  }
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
});

export default router;
