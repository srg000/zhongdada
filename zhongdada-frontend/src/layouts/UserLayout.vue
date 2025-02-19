<template>
  <div id="UserLayout">
    <a-layout style="height: 100vh">
      <a-layout-header class="header">
        <a-space>
          <img class="logo" src="../assets/logo.png" alt="" />
          <div class="title">柊答答 AI 答题应用平台</div>
        </a-space>
      </a-layout-header>
      <a-layout-content class="content">
        <router-view />
      </a-layout-content>
      <a-layout-footer class="footer">
        <a
          href="https://baidu.com"
          target="_blank"
          style="text-decoration: none"
        >
          百度地址-柊泓迅测试
        </a>
      </a-layout-footer>
    </a-layout>
  </div>
</template>

<script setup lang="ts"></script>

<style scoped>
#UserLayout {
  text-align: center;
  background: url("https://gw.alipayobjects.com/zos/rmsportal/FfdJeJRQWjEeGTpqgBKj.png")
    0% 0% / 100% 100%;
}
#UserLayout .logo {
  width: 50px;
  height: 50px;
}
#UserLayout .header {
  margin-top: 16px;
  font-size: 21px;
}
#UserLayout .content {
  margin-bottom: 16px;
  padding: 20px;
}
.footer {
  text-align: center;
  padding: 16px;
  background: #efefef;
}
</style>
