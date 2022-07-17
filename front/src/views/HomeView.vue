<script setup lang="ts">
import axios from "axios";
import {ref} from "vue";
import {useRouter} from "vue-router";

const router = useRouter();
const posts = ref([
  {id: 1, title: "제목1", content: "내용1"},
  {id: 2, title: "제목2", content: "내용2"},
  {id: 3, title: "제목3", content: "내용3"},
  {id: 4, title: "제목4", content: "내용4"},
  {id: 5, title: "제목5", content: "내용5"},
  {id: 6, title: "제목6", content: "내용6"}
]);

axios.get("http//localhost:8080/posts?page=1&size=5")
.then(response => {
  response.data.forEach((item: any) => {
    posts.value.push(item);
  })
});

const moveToRead = () => {
  router.push({name: "read"});
}
</script>

<template>
  <ul>
    <li v-for="post in posts" :key="post.id" @click="moveToRead()">
      <div class="title">
        <router-link :to="{name: 'read', params: {postId: post.id}}">{{ post.title }}</router-link>
      </div>

      <div class="content">
        {{ post.content }}
      </div>
    </li>
  </ul>
</template>

<style scoped>
ul {
  list-style: none;
  padding: 0;
}

li {
  margin-bottom: 1.3rem;
}

li .title a {
  font-size: 1.2rem;
  color: #383838;
  text-decoration: none;
}

li .content {
  font-size: 0.9rem;
  color: #5d5d5d;
}

li:last-child {
  margin-bottom: 0;
}
</style>
