<script setup lang="ts">
import {ref} from "vue";
import axios from "axios";
import {useRouter} from "vue-router";

const title = ref("");
const content = ref("");

const router = useRouter();

const write = () => {
  axios.post("http//localhost:8080/posts", {
    title: title.value,
    content: content.value
  })
  .then(() => {
    router.replace({name: "home"});
  });
};
</script>

<template>
  <div>
    <div class="mt-2">
      <el-input placeholder="제목을 입력해주세요." v-model="title"/>
    </div>
    <div class="mt-2">
      <el-input
        v-model="content"
        :rows="15"
        type="textarea"
        placeholder="내용 입력"
      />
    </div>
    <div class="mt-2 d-flex justify-content-end">
      <el-button type="primary" @click="write()">글 작성</el-button>
    </div>
  </div>
</template>

<style>

</style>
