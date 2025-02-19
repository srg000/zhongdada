<template>
  <div id="globalHeader">
    <a-row class="grid-demo" align="center" :wrap="false">
      <a-col flex="auto">
        <div class="menu-demo">
          <a-menu
            mode="horizontal"
            :selected-keys="selectedKeys"
            @menu-item-click="doClickMenu"
          >
            <a-menu-item
              key="0"
              :style="{ padding: 0, marginRight: '38px' }"
              disabled
            >
              <div class="titleBar">
                <img class="logo" src="../assets/logo.png" alt="网站logo" />
                <div class="title">柊答答</div>
              </div>
            </a-menu-item>
            <a-menu-item v-for="item in visibleRoutes" :key="item.path">
              {{ item.name }}
            </a-menu-item>
          </a-menu>
        </div>
      </a-col>
      <a-col flex="100px">
        <div v-if="loginUserStore.loginUser.id">
          {{ loginUserStore.loginUser.userName ?? "无名" }}
        </div>
        <div v-else>
          <a-button type="primary" href="/user/login">登录</a-button>
        </div>
      </a-col>
    </a-row>
  </div>
</template>

<script setup lang="ts">
import { routes } from "@/router/routes";
import { useRouter } from "vue-router";
import { computed, ref } from "vue";
import { useLoginUserStore } from "@/store/userStore";
import checkAccess from "@/access/checkAccess";

const route = useRouter();
// 当前选中的菜单项
const selectedKeys = ref(["/"]);
// 路由跳转后，自动更新选中的菜单项
route.afterEach((to) => {
  selectedKeys.value = [to.path];
});

const visibleRoutes = computed(() => {
  return routes.filter((item) => {
    if (item.meta?.hideInMenu) {
      return false;
    }
    // 根据权限过滤菜单
    if (!checkAccess(loginUserStore.loginUser, item.meta?.access as string)) {
      return false;
    }
    return true;
  });
});

let loginUserStore = useLoginUserStore();

// 点击菜单跳转到对应页面
const doClickMenu = (key: string) => {
  route.push(key);
};
</script>

<style scoped>
#globalHeader {
}
.titleBar {
  display: flex;
  align-items: center;
}
.title {
  color: black;
  font-size: 20px;
  font-weight: bolder;
}
.logo {
  width: 40px;
  height: 40px;
  margin-right: 10px;
}
</style>
