<script setup lang="ts">
import {defineProps, onMounted, ref} from "vue";
import axios from "axios";
import {useRouter} from "vue-router";

const props = defineProps({
  postId: {
    type: Number,
    require: true
  }
});

const post = ref({
  id: 0,
  title: "",
  content: ""
});

const router = useRouter();

onMounted(() => {
  axios.get(`http//localhost:8080/posts/${props.postId}`)
  .then(response => {
    response.data.forEach((item: any) => {
    })
  });
})

const moveToEdit = () => {
  router.push({name: "edit", params: {postId: props.postId}});
}
</script>

<template>
  <el-row>
    <el-col>
      <h2>{{ post.title }}}</h2>

      <div class="sub d-flex">
        <div class="category">개발</div>
        <div class="regDate">2022-07-19 00:00:00</div>
      </div>
    </el-col>
  </el-row>

  <el-row class="mt-3">
    <el-col>
      <div>{{ post.content }}</div>
    </el-col>
  </el-row>

  <el-row class="mt-3">
    <el-col>
      <div class="d-flex justify-content-end">
        <el-button type="warning" @click="moveToEdit()">수정하기</el-button>
      </div>
    </el-col>
  </el-row>
</template>
