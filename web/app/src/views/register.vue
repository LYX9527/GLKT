<script setup lang="ts">
import {Ref} from "vue";
import {useVerificationCode} from "@/hooks/UseVerificationCode";
import {register} from "@/api/login";

const router = useRouter();
const Register = () => {
  register(registerFrom.value).then(res => {
    if (res.data.code === 200) {
      router.push({
        path: '/login'
      })
    }
  });
}
const codeDetail: Ref<IVerificationCode> = ref({
  image: '',
  uuid: ''
});
const getCode = async () => {
  let code = await useVerificationCode();
  codeDetail.value = code;
  registerFrom.value.uuid = code.uuid
}
const confirmCode = ref('')
const registerFrom: Ref<IRegisterFrom> = ref({
  user: '',
  password: '',
  nickName: '',
  phone: '',
  email: '',
  code: '',
  uuid: ''
});
const Login = () => {
  router.push({
    path: '/login'
  })
}
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
        <el-input placeholder='账号' v-model="registerFrom.user"/>
      </div>
    </div>
    <div>
      <div>昵称</div>
      <div>
        <el-input placeholder='昵称' v-model="registerFrom.nickName"/>
      </div>
    </div>
    <div>
      <div>密码</div>
      <div>
        <el-input type='password' placeholder='密码' v-model="registerFrom.password"/>
      </div>
    </div>
    <div>
      <div>确认密码</div>
      <div>
        <el-input type='password' placeholder='确认密码' v-model="confirmCode"/>
      </div>
    </div>
    <div>
      <div>手机号</div>
      <div>
        <el-input maxlength="11" placeholder='手机号(仅限大陆手机号)' v-model="registerFrom.phone"/>
      </div>
    </div>
    <div>
      <div>邮箱</div>
      <div>
        <el-input placeholder='邮箱' v-model="registerFrom.email"/>
      </div>
    </div>
    <div>
      <div>验证码</div>
      <div class="flex">
        <div>
          <img :src="codeDetail.image" alt="" @click="getCode">
        </div>
        <el-input placeholder='验证码' v-model="registerFrom.code"/>
      </div>
    </div>

    <div class="flex">
      <el-button class="w-full mt-5" @click="Login">登录</el-button>
      <el-button class="w-full mt-5" @click="Register">注册</el-button>
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