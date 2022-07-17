<script setup lang="ts">
import {onMounted, ref} from "vue";
import axios from "axios";
import {useRouter} from "vue-router";

const router = useRouter();

const props = defineProps({
  postId: {
    type: [Number, String],
    require: true
  }
});

const post = ref({
  id: 0,
  title: "",
  content: ""
});

const edit = () => {
  axios.patch(`http//localhost:8080/posts/${props.postId}`, post.value)
  .then(() => {
    router.replace({name: "home"});
  });
};

onMounted(() => {
  axios.get(`http//localhost:8080/posts/${props.postId}`)
  .then(response => {
    response.data.forEach((item: any) => {
    })
  });
})
</script>

<template>
  <div>
    <div class="mt-2">
      <el-input placeholder="제목을 입력해주세요." v-model="post.title"/>
    </div>
    <div class="mt-2">
      <el-input
        v-model="post.content"
        :rows="15"
        type="textarea"
        placeholder="내용 입력"
      />
    </div>
    <div class="mt-2">
      <el-button type="warning" @click="edit()">수정 완료</el-button>
    </div>
  </div>
</template>

<style>

</style>
