<!--  -->
<template>
    <el-dialog style="--el-dialog-border-radius:1rem" v-model="cardDialogVisible" width="60rem" align-center
        @close="changeCardDialogVisible" :show-close="false">
        <div class="ctn">
            <div class="left">
                <el-carousel height="70vh" autoplay>
                    <el-carousel-item v-for="(item, index) in images" :key="index">
                        <img :src="item" style="height: 70vh; object-fit: contain;" alt="">
                    </el-carousel-item>
                </el-carousel>
            </div>
            <div class="right">
            </div>
        </div>
    </el-dialog>
</template>

<script setup lang="ts">
import { ref, watch, defineProps, onMounted, computed, reactive } from 'vue'
import store from '@/store'
import { getDetailByArticleId } from '@/request/article'

const cardDialogVisible = ref(store.state.cardDialogVisible)
const changeCardDialogVisible = () => {
    store.commit('changeCardDialogVisible')
}
watch(
    () => store.state.cardDialogVisible,
    (newValue) => {
        cardDialogVisible.value = newValue;
    }
);
const props = defineProps({
    articleId: {
        type: Number,
        required: true
    }
})
const article: any = ref()
const images = ref([]);
onMounted(async () => {
    const data = await getDetailByArticleId(props.articleId)
    article.value = data
    images.value = data.images.split(',');

})
</script>

<style lang="less" scoped>
.ctn {
    display: flex;

    .left {
        width: 65%;

    }

    .right {
        width: 35%;
    }
}
</style>