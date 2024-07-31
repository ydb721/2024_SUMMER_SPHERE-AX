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
  background: linear-gradient(90deg,
      rgba(255, 255, 255, 1) 0%,
      rgba(199, 199, 199, 1) 100%);
}

#nav-style {
  background-color: #000000;
  color: white;
  padding: 1rem;
  font-weight: bolder;
  font-size: larger;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding-right: 10%;
  padding-top: 2%;
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