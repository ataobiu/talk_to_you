<!-- 页面布局文件 -->
<template>
  <div class="common-layout">
    <el-container>
      <el-header>
        <HeaderComponent></HeaderComponent>
      </el-header>
      <el-container>
        <el-aside width="12rem">
          <AsideComponent></AsideComponent>
        </el-aside>
        <el-main>
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
  <!-- 登录组件 -->
  <LoginComponent></LoginComponent>
</template>

<script setup lang="ts">
import HomeView from '@/views/HomeView.vue';


</script>

<style lang="less" scoped>
.el-container {
  height: 100vh;

  .el-header {
    margin: 0;
    height: 4rem;
    overflow: hidden;
    border-bottom: 1px solid #f3f4f6;

  }

  .el-main {
    max-height: calc(100vh - 4rem);
  }
}
</style>