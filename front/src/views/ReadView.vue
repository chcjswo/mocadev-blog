<script setup lang="ts">
import {defineProps, onMounted, ref} from "vue";
import axios from "axios";

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

  onMounted(() => {
    axios.get(`http//localhost:8080/posts/${props.postId}`)
    .then(response => {
      response.data.forEach((item: any) => {
      })
    });
  })
</script>

<template>
  <h2>{{ post.title }}}</h2>
  <div>{{ post.content }}</div>
</template>
