<script setup lang="ts">
import {getInfo, login} from "@/api/login";

const Login = () => {
  login().then(res => {
    console.log(res);
  });
}
const GetInfo = () => {
  getInfo().then(res => {
    console.log(res);
  });
}
</script>

<template>
  <div class="box">
    <div class="title">谷粒课堂后台系统</div>
    <div>
      <div>账号</div>
      <div>
        <el-input placeholder='账号'/>
      </div>
    </div>
    <div>
      <div>密码</div>
      <div>
        <el-input type='password' placeholder='密码'/>
      </div>
    </div>
    <div>
      <el-button class="w-full mt-5" @click="Login">登录</el-button>
    </div>
    <div>
      <el-button class="w-full mt-5" @click="GetInfo">获取信息</el-button>
    </div>

  </div>
</template>

<style lang="scss" scoped>
.box {
  @apply absolute top-0 left-0 right-0 bottom-0 w-[500px] h-[600px] m-auto ;
  > div {
    @apply w-full;
  }

  .title {
    @apply text-center text-xl text-gray-600 font-bold mb-4;
  }
}
</style>