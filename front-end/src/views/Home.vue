<template>
  <div class="home-page">
    <div class="legend-container">
      <div v-for="(image, index) in images" 
           :key="index" class="legend-item" 
           :class="{ active: currentImage1Index === index }" 
           @click="changeImage(index)">
        <span class="diamond"></span>
        <svg v-if="currentImage1Index === index" class="timer-circle" viewBox="0 0 100 100">
          <circle  cx="50" cy="50" r="45"  class="rotated-circle" />
        </svg>
      </div>
    </div>
    <h1 class="centered-text">Weather Info</h1>
    <h4 class="centered-text1">This is a website where you can check virtual weather data that is updated every minute.
      You can check the data in two ways.
    </h4>
  </div>
</template>

<script>
import EventBus from '../EventBus';

export default {
  name : 'Home-view',
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
    currentImage1() {
      return this.images[this.currentImage1Index];
    }
  },
  methods: {
    startImageRotation() {
      if(this.interval) {
        clearInterval(this.interval);
      }
      this.interval = setInterval(this.nextImage, 5000);
    },
    nextImage() {
      this.currentImage1Index = (this.currentImage1Index + 1) % this.images.length;
      EventBus.emit('change-image', this.currentImage1Index);
    },
    changeImage(index) {
      this.currentImage1Index = index;
      EventBus.emit('change-image', index);
      this.startImageRotation();
    }
  },
  mounted() {
    this.startImageRotation();
  },
  beforeUnmount() {
    if (this.interval) {
      clearInterval(this.interval);
    }
  }
};
</script>

<style>
body {
  margin: 0;
  font-family: Arial, sans-serif;
  background-color: black;
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

.legend-container {
  position: absolute;
  bottom: 17vh;
  left: 15%;
  transform: translateX(-50%);
  display: flex;
  flex-direction: row;
  gap: 60px;
  z-index: 4;
}

.legend-item {
  width: 15px;
  height: 15px;
  cursor: pointer;
  position: relative;
  z-index: 5;
  pointer-events: auto;
}

.legend-item .diamond {
  width: 15px;
  height: 15px;
  background-color: rgba(255, 255, 255, 0.8);
  transform: rotate(45deg);
  display: block;
  border: 1px solid gray;
  border-radius: 3px;
}

.legend-item.active .diamond {
  background-color: white;
}

.legend-item .timer-circle {
  position: absolute;
  top: 50%;
  left: 50%;
  width: 50px;
  height: 40px;
  transform: translate(-50%, -50%);
}

.legend-item .rotated-circle {
  transform: rotate(-90deg); /* 원을 -90도 회전하여 시작점을 왼쪽으로 이동 */
  transform-origin: 50% 50%; /* 회전의 중심을 원의 중심으로 설정 */
}

.legend-item .timer-circle circle {
  fill: none;
  stroke: red;
  stroke-width: 5;
  stroke-dasharray: 283;
  stroke-dashoffset: 212.25;
  animation: drawCircle 5s linear infinite;
}

@keyframes drawCircle {
  0% {
    stroke-dashoffset: 283;
  }
  100% {
    stroke-dashoffset: 0;
  }
}

.centered-text {
  position: absolute;
  top: 40%;
  left: 50%;
  transform: translate(-50%, -50%);
  color: white;
  font-size: 5rem;
  z-index: 6;
}

.centered-text1 {
  position: absolute;
  top: 60%;
  left: 50%;
  transform: translate(-50%, -50%);
  color: white;
  font-size: 1.5rem;
  z-index: 6;
  text-align: center;
  line-height: 1.8em;
}

/* 반응형 디자인을 위한 미디어 쿼리 */
@media (max-width: 768px) {
  .legend-container {
    bottom: 5%;
    gap: 15px;
  }

  .legend-item {
    width: 12px;
    height: 12px;
  }

  .legend-item .diamond {
    width: 12px;
    height: 12px;
  }

  .legend-item .timer-circle {
    width: 27px;
    height: 27px;
  }


  .centered-text {
    font-size: 2.4rem; /* 중간 화면에서의 폰트 크기 조정 */
    margin-top: 1vh; /* 제목과의 간격 조정 */
  }

  .centered-text1 {
    font-size: 1.2rem; /* 중간 화면에서의 폰트 크기 조정 */
    margin-top: 3rem; /* 제목과의 간격 조정 */
    line-height: 100%;

  }
}

@media (min-width: 769px) and (max-width: 1024px) {
  .legend-container {
    bottom: 7%;
    gap: 20px;
  }

  .legend-item {
    width: 15px;
    height: 15px;
  }

  .legend-item .diamond {
    width: 15px;
    height: 15px;
  }

  .legend-item .timer-circle {
    width: 30px;
    height: 30px;
  }

  .centered-text {
    font-size: 4rem; /* 큰 화면에서의 폰트 크기 조정 */
  }

  .centered-text1 {
    font-size: 1.4rem; /* 큰 화면에서의 폰트 크기 조정 */
    margin-top: 3rem; /* 제목과의 간격 조정 */
  }
}

@media (min-width: 1025px) {
  .legend-container {
    bottom: 10%;
    gap: 50px;
  }

  .legend-item {
    width: 15px;
    height: 15px;
  }

  .legend-item .diamond {
    width: 15px;
    height: 15px;
  }

  .legend-item .timer-circle {
    width: 35px;
    height: 35px;
  }

  .centered-text {
    font-size: 5rem;
  }

  .centered-text1 {
    font-size: 1.5rem;
    margin-top: 2rem; /* 제목과의 간격 조정 */
  }
  
}
</style>
  