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
  <h2>{{ post.title }}}</h2>
  <div>{{ post.content }}</div>

  <el-button type="warning" @click="moveToEdit()">수정하기</el-button>
</template>
