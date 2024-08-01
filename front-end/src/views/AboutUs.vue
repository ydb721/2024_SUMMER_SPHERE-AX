<template>
  <div align="center">
    <div class="img-container">
      <img src="https://i.vimeocdn.com/video/1900935094-1e2ddcdefc344bdea03046a5c9795e1cc775ceabadcac497db754d9202838663-d_640?f=webp" />
    </div>

    <div align="center">
      <a href="https://github.com/sty0133/2024_SUMMER_SPHERE-AX" target="_blank">
        <img src="https://img.shields.io/badge/GitHub Repo-181717?style=for-the-badge&logo=github&logoColor=white" class="badge-link" />
      </a>

      <table class="contributors-table">
        <tr>
          <td class="table-cell">🛠️ Front-end</td>
          <td class="table-cell">
            <a href="https://github.com/sty0133" target="_blank" class="table-link">황보재윤</a>
          </td>
          <td class="table-cell">
            <a href="https://github.com/sealclou" target="_blank" class="table-link">김기태</a>
          </td>
        </tr>
        <tr>
          <td class="table-cell">🛠️ Back-end</td>
          <td class="table-cell">
            <a href="https://github.com/lee-hyeon-jin" target="_blank" class="table-link">이현진</a>
          </td>
          <td class="table-cell">
            <a href="https://github.com/ydb721" target="_blank" class="table-link">다빈</a>
          </td>
        </tr>
      </table>
    </div>
  </div>

  <div class="content-left">
    <div class="section">
      <h3 class="section-title">🛠️ Front-end</h3>
      <!-- Front-end -->
      <img src="https://img.shields.io/badge/Vue.js-4FC08D?style=for-the-badge&logo=Vue.js&logoColor=white" />
      <img src="https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=HTML5&logoColor=white" />
      <img src="https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=CSS3&logoColor=white" />
      <img src="https://img.shields.io/badge/Javascript-F7DF1E?style=for-the-badge&logo=Javascript&logoColor=white" />
    </div>
    <div class="section">
      <h3 class="section-title">🛠️ Back-end</h3>
      <!-- Back-end -->
      <img src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=Spring&logoColor=white" />
      <img src="https://img.shields.io/badge/Tomcat-F8DC75?style=for-the-badge&logo=Apache%20Tomcat&logoColor=white" />
      <img src="https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white" />
    </div>
    <div class="section">
      <h3 class="section-title">🛠️ Database</h3>
      <!-- Database -->
      <img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=MySQL&logoColor=white" />
    </div>
    <h3 class="section-title">🛠️ Network</h3>
    <!-- Network -->
    <img src="https://img.shields.io/badge/WebSocket-FF6F00?style=for-the-badge&logo=WebSocket&logoColor=white" />
  </div>
  <br />
  <br />
  <div class="content-left">
    <h3 class="section-title">🔥 READ</h3>
    <div>
      <p style="font-weight: bold">
        본 프로젝트는 대구가톨릭대학교 SW중심대학 사업단에서 추진하는 2024학년도 여름계절제 국내현장실습 SPHERE AX(구 우경정보기술)에서 현장 실습생들끼리 전자정부프레임워크 공부 목적으로 시작한 개인 프로젝트로, 회사의 업무와는 무관합니다.
      </p>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Aboutus-view'
};
</script>

<style>
.img-container {
  margin-bottom: 1vh;
}

.badge-link {
  margin-bottom: 1vh;
}

.contributors-table {
  border-collapse: separate;
  border-spacing: 5px;
  width: 20%;
  border-radius: 10px;
  overflow: hidden;
  text-align: center;
  border: 1px solid white;
}

.table-cell {
  border: 1px solid white;
  padding: 8px;
}

.table-link {
  color: white;
  text-decoration: none;
  font-size: 18px;
}

.content-left {
  text-align: left;
  color: white;
  margin-left: 10%;
}

.section {
  margin-bottom: 3vh;
}

.section-title {
  border-bottom: 1px solid white;
}
</style>
