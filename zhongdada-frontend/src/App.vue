<template>
  <div id="app">
    <template v-if="route.path.startsWith('/user')">
      <router-view />
    </template>
    <template v-else> <BasicLayout /> </template>
  </div>
</template>

<script setup lang="ts">
import BasicLayout from "@/layouts/BasicLayout.vue";
import { useRoute } from "vue-router";
import { onMounted } from "vue";
// useRoute是获取当前页面信息，useRouter执行页面的跳转
const route = useRoute();

/**
 * 全局初始化函数，有全局单次调用的代码，都可以写在这里
 */
const doInit = () => {
  console.log("hello 欢迎来到我的项目");
};
// onMounted() 页面加载后，只执行一次
onMounted(() => {
  doInit();
});
</script>

<style scoped>
# app {
}
</style>
