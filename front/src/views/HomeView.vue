<script setup lang="ts">
import axios from "axios";
import {ref} from "vue";

const posts = ref([]);

axios.get("http//localhost:8080/posts?page=1&size=5")
.then(response => {
  response.data.forEach((item: any) => {
    posts.value.push(item);
  })
});
</script>

<template>
  <ul>
    <li v-for="post in posts" :key="post.id">
      <div>
        {{ post.title }}
      </div>

      <div>
        {{ post.content }}
      </div>
    </li>
  </ul>
</template>
