<script setup lang="ts">
import {getInfo, login} from "@/api/login";
import {useVerificationCode} from "@/hooks/UseVerificationCode";
import {Ref} from "vue";
import {setToken} from "@/utils/token";

const router = useRouter();
const Login = async () => {
  const loginDetail = await login(loginFrom.value)
  if (loginDetail.code === 200) {
    setToken(loginDetail.data.token)
    GetInfo(loginDetail.data.id);
    await router.push({
      path: '/'
    })
  }
}
const GetInfo = (id: number) => {
  getInfo({id}).then(res => {
    console.log(res);
  });
}
const register = () => {
  router.push({
    path: '/register'
  })
}
// 获取验证码
const getCode = async () => {
  let code = await useVerificationCode();
  codeDetail.value = code;
  loginFrom.value.uuid = code.uuid
}

const codeDetail: Ref<IVerificationCode> = ref({
  image: '',
  uuid: ''
});
const loginFrom: Ref<ILoginFrom> = ref({
  user: '',
  password: '',
  code: '',
  uuid: ''
});
onMounted(() => {
  getCode();
})
</script>

<template>
  <div class="box">
    <div class="title">谷粒课堂后台系统</div>
    <div>
      <div>账号</div>
      <div>
        <el-input placeholder='账号' v-model="loginFrom.user"/>
      </div>
    </div>
    <div>
      <div>密码</div>
      <div>
        <el-input type='password' placeholder='密码' v-model="loginFrom.password"/>
      </div>
    </div>
    <div>
      <div>验证码</div>
      <div class="flex">
        <div>
          <img :src="codeDetail.image" alt="" @click="getCode">
        </div>
        <el-input placeholder='验证码' v-model="loginFrom.code"/>
      </div>
    </div>

    <div class="flex">
      <el-button class="w-full mt-5" @click="register">注册</el-button>
      <el-button class="w-full mt-5" @click="Login">登录</el-button>
    </div>
    <div>
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