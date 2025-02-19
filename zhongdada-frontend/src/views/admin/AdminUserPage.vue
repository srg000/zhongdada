<template>
  <a-form
    :model="formSearchParams"
    :style="{ marginBottom: '20px' }"
    layout="inline"
    @submit="doSearch"
  >
    <a-form-item field="userName" label="用户名">
      <a-input
        allow-clear
        v-model="formSearchParams.userName"
        placeholder="请输入用户名"
      />
    </a-form-item>
    <a-form-item field="userProfile" label="用户简介">
      <a-input
        allow-clear
        v-model="formSearchParams.userProfile"
        placeholder="请输入用户简介"
      />
    </a-form-item>
    <a-form-item>
      <a-button type="primary" html-type="submit" style="width: 100px">
        搜索
      </a-button>
    </a-form-item>
  </a-form>
  <a-table
    :columns="columns"
    :data="dataList"
    :column-resizable="true"
    :pagination="{
      showTotal: true,
      current: searchParam.current,
      pageSize: searchParam.pageSize,
      total: total,
    }"
    @page-change="onPageChange"
  >
    <template #userAvatar="{ record }">
      <a-image width="64" :src="record.userAvatar" />
    </template>
    <template #createTime="{ record }">
      {{ dayjs(record.createTime).format("YYYY-MM-DD HH:mm:ss") }}
    </template>
    <template #updateTime="{ record }">
      {{ dayjs(record.updateTime).format("YYYY-MM-DD HH:mm:ss") }}
    </template>
    <template #optional="{ record }">
      <a-space>
        <a-button status="danger" @click="doDelete(record)">删除</a-button>
      </a-space>
    </template>
  </a-table>
</template>

<script setup lang="ts">
import { ref, watchEffect } from "vue";
import API from "@/api";
import {
  deleteUserUsingPost,
  listUserByPageUsingPost,
} from "@/api/userController";
import { Message } from "@arco-design/web-vue";
import { dayjs } from "@arco-design/web-vue/es/_utils/date";
import User = API.User;

const formSearchParams = ref<API.UserQueryRequest>({});

// 初始搜索参数
const initParam = {
  current: 1,
  pageSize: 10,
};

const searchParam = ref<API.UserQueryRequest>({
  ...initParam,
});

const dataList = ref<API.User[]>([]);
const total = ref<number>(0);

/**
 * 加载数据
 */
const loadData = async () => {
  const res = await listUserByPageUsingPost(searchParam.value);
  if (res.data.code === 0) {
    dataList.value = res.data.data?.records || [];
    total.value = res.data.data?.total || 0;
  } else {
    Message.error("获取数据失败" + res.data.message);
  }
};

/**
 * 监听loadData() 变量，改变时触发数据的重新加载
 * 在进入页面 以及刷新页面都需要重新加载数据
 */
watchEffect(() => {
  loadData();
});

/**
 * 当页面变化时，改变搜索条件，重新加载页面
 * @param page
 */
const onPageChange = (page: number) => {
  searchParam.value = {
    ...searchParam.value,
    current: page,
  };
};

/**
 * 删除操作
 * @param record 当行记录
 */
const doDelete = async (record: User) => {
  const res = await deleteUserUsingPost({
    id: record.id,
  });
  if (res.data.code === 0) {
    Message.success("删除成功");
    await loadData();
  } else {
    Message.error("删除失败" + res.data.message);
  }
};

/**
 * 执行查询操作
 */
const doSearch = async () => {
  const res = await listUserByPageUsingPost({
    // 这里需要把两部分的参数都要加进来，要保证搜索参数和页面参数一起搜索
    ...formSearchParams.value,
    ...initParam,
  });
  if (res.data.code === 0) {
    dataList.value = res.data.data?.records || [];
    total.value = res.data.data?.total || 0;
  } else {
    Message.error("获取数据失败" + res.data.message);
  }
};

// 表格列配置
const columns = [
  {
    title: "id",
    dataIndex: "id",
  },
  {
    title: "账号",
    dataIndex: "userAccount",
  },
  {
    title: "用户名",
    dataIndex: "userName",
  },
  {
    title: "用户头像",
    dataIndex: "userAvatar",
    slotName: "userAvatar",
  },
  {
    title: "用户简介",
    dataIndex: "userProfile",
  },
  {
    title: "权限",
    dataIndex: "userRole",
  },
  {
    title: "创建时间",
    dataIndex: "createTime",
    slotName: "createTime",
  },
  {
    title: "更新时间",
    dataIndex: "updateTime",
    slotName: "updateTime",
  },
  {
    title: "操作",
    slotName: "optional",
  },
];
</script>

<style scoped></style>
