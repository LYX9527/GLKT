<script setup lang="ts">
import {getToken} from "@/utils/token";
import {delFile} from "@/api/login";

const fileList = reactive([]);
const headers = reactive({
  Authorization: "Bearer " + getToken()
})
const imgKey = ref('');
const handleSuccess = (res: any) => {
  console.log(res.data.Key)
  imgKey.value = res.data.Key;
}
const delFileCtrl = () => {
  delFile({key: imgKey.value}).then(res => {
    console.log(res)
  })
}
</script>

<template>
  <el-upload
      v-model:file-list="fileList"
      class="upload-demo"
      :headers="headers"
      action="http://localhost:11001/admin/vod/upload/file"
      multiple
      :limit="3"
      :on-success='handleSuccess'
  >
    <el-button type="primary">Click to upload</el-button>
    <template #tip>
      <div class="el-upload__tip">
        jpg/png files with a size less than 500KB.
      </div>
    </template>
  </el-upload>
  <el-button @click="delFileCtrl">删除文件</el-button>
</template>

<style lang="scss">

</style>