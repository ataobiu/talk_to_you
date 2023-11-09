<!--  -->
<template>
    <div class="search">
        <el-input @keyup.enter="searchArticle" style="--el-input-border-radius:2rem;" v-model="searchValue"
            placeholder="请输入关键字">
        </el-input>
    </div>
    <CardComponent :articleList="articleList" />
</template>

<script setup lang="ts">
import { ref } from 'vue';
import { searchArticleByTtle } from '@/request/article';

const articleList: any = ref([]);

const searchValue = ref('')
const searchArticle = async () => {
    const res = await searchArticleByTtle(searchValue.value)
    articleList.value = res.data
}
</script>

<style lang="less" scoped>
.search {
    margin-bottom: 1rem;
    display: flex;
    justify-content: end;

    .el-input {
        height: 2.5rem;
        width: 20rem;
    }

    img {
        height: 1.5rem;
    }
}
</style>