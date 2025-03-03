<template>
  <div id="BasicLayout">
    <a-layout style="height: 100vh">
      <a-layout-header class="header">
        <GlobalHeader />
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

<script setup lang="ts">
import GlobalHeader from "@/components/GlobalHeader.vue";
</script>

<style scoped>
#BasicLayout {
}
#BasicLayout .header {
  margin-bottom: 16px;
  box-shadow: #eee 1px 1px 5px;
}
#BasicLayout .content {
  max-width: 1400px;
  width: 100%;
  background: linear-gradient(to right, #fefefe, #fff);
  margin: 0 auto 28px;
  box-sizing: border-box;
  padding: 20px;
}
.footer {
  text-align: center;
  padding: 16px 0;
  background: #efefef;
}
</style>
