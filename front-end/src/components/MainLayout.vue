<template>
    <div :class="['main-layout', { 'home-page': isHomePage }]">
      <Navbar />
      <div id="show-routes">
        <router-view />
      </div>
      <div v-if="isHomePage" class="background-container">
        <div class="background-image">
          <img :src="currentImage1" class="fade" />
        </div>
        <div class="overlay"></div>
      </div>
    </div>
  </template>
  
  <script>
  import Navbar from '../components/Navbar';
  import EventBus from '../EventBus';
  
  export default {
    name: 'MainLayout',
    components: {
      Navbar
    },
    data() {
      return {
        images: [
          require('../assets/weather1-1.png'),
          require('../assets/weather2.jpg'),
          require('../assets/weather3.jpg'),
          require('../assets/weather4.jpg')
        ],
        currentImage1Index: 0,
        interval: null
      };
    },
    computed: {
      isHomePage() {
        return this.$route.path === '/';
      },
      currentImage1() {
        return this.images[this.currentImage1Index];
      }
    },
    methods: {
      nextImage() {
        this.currentImage1Index = (this.currentImage1Index + 1) % this.images.length;
      },
      changeImage(index) {
        this.currentImage1Index = index;
        clearInterval(this.interval);
        this.interval = setInterval(this.nextImage, 5000);
      }
    },
    mounted() {
      if (this.isHomePage) {
        this.interval = setInterval(this.nextImage, 5000);
        EventBus.on('change-image', this.changeImage);
      }
    },
    beforeUnmount() {
      if (this.interval) {
        clearInterval(this.interval);
      }
      EventBus.off('change-image', this.changeImage);
    }
  };
  </script>
  
  <style>
  /* 기존 스타일 그대로 유지 */
  body {
    margin: 0;
    font-family: Arial, sans-serif;
    background-color: black;
  }
  
  .main-layout {
    position: relative;
    min-height: 100vh;
    overflow: hidden;
  }
  
  .home-page .background-container {
    display: block;
  }
  
  .background-container {
    display: none;
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    overflow: hidden;
    z-index: -1;
  }
  
  .background-image img {
    width: 100%;
    height: 100%;
    object-fit: cover;
    opacity: 0;
    transition: opacity 1s ease-in-out;
  }
  
  .background-image img.fade {
    opacity: 1;
  }
  
  .overlay {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.5);
    z-index: 1;
    pointer-events: none;
  }
  
  #show-routes {
    padding-left: 10%;
    padding-right: 10%;
    z-index: 2;
  }
  </style>