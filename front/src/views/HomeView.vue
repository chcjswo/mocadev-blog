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

      <div class="d-flex sub">
        <div class="category">개발</div>
        <div class="regDate">2022-07-18</div>
      </div>
    </li>
  </ul>
</template>

<style scoped lang="scss">
ul {
  list-style: none;
  padding: 0;

  li {
    margin-bottom: 1.6rem;

    .title {
      a {
        font-size: 1.1rem;
        color: #383838;
        text-decoration: none;
      }

      &:hover {
        text-decoration: underline;
      }
    }

    .content {
      font-size: 0.9rem;
      margin-top: 8px;
      color: #5d5d5d;
    }

    &:last-child {
      margin-bottom: 0;
    }

    .sub {
      margin-top: 8px;
      font-size: 0.78rem;

      .regDate {
        color: #6b6b6b;
        margin-left: 10px;
      }
    }

  }
}
</style>
