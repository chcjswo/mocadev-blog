<script setup lang="ts">
</script>

<template>
  <el-header class="header">
    <el-menu mode="horizontal">
      <el-menu-item index="1">Home</el-menu-item>
      <el-menu-item index="2">글 작성</el-menu-item>
    </el-menu>
  </el-header>

  <RouterView/>
</template>

<style scoped>
header {
  padding: 0;
}
</style>
