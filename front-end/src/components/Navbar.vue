<template>
  <nav class="fv-font">
    <ul id="nav-style">
      <li id="logo-item">
        <img src="../assets/kwiz.png" id="comp-logo">
      </li>
      <li><router-link to="/">Home</router-link></li>
      <li><router-link to="/rawData">Raw Data</router-link></li>
      <li><router-link to="/charts">Data Chart</router-link></li>
      <li><router-link to="/aboutUs">About us</router-link></li>
    </ul>
  </nav>
</template>

<script>
export default {
  name: 'Nav-bar'
};
</script>

<style>
@import '../vars.css';

.fv-font {
  position: relative;
  background: rgba(0, 0, 0, 0.01); /* 검정색 반투명 배경 */
  z-index: 10; /* 오버레이가 다른 요소보다 위에 위치하도록 설정 */
}

#nav-style {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-right: 10%;
  padding: 1rem;
  font-weight: bolder;
  font-size: larger;
  color: white;
  /* 글자가 배경 위에 표시되도록 설정 */
  z-index: 20;
}

#nav-style li > a:hover {
  color: rgb(128, 128, 128);
}

#logo-item {
  margin-right: auto;
  padding-left: 10%;
}

#comp-logo {
  display: block;
  height: 30px;
  width: auto;
}

nav ul {
  list-style-type: none;
  display: flex;
  gap: 3rem;
  margin: 0;
  padding: 0;
}

nav ul li {
  display: inline;
}

a {
  color: white;
  font-size: 25px;
  text-decoration: none;
}

a.router-link-active {
  font-weight: bold;
}
</style>