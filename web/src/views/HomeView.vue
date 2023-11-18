<!--  -->
<template>
    <CardComponent :articleList="articleList" />
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { getArticles } from '@/request/article';

const articleList: any = ref([]);
onMounted(
    async () => {
        articleList.value = await getArticles()
    }
);
</script>