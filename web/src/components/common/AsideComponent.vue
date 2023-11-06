<!--  -->
<template>
    <div class="ctn">
        <router-link :to="{ path: '/home' }" class="link" active-class="active">
            <img :src="require('@/assets/发现.svg')" alt="">
            <span>发现</span>
        </router-link>
        <router-link :to="{ path: '/message' }" class="link" active-class="active">
            <img :src="require('@/assets/消息.svg')" alt="">
            <span>消息</span>
        </router-link>
        <el-button @click="changeLoginDialogVisible" round>
            <span>登录</span>
        </el-button>
    </div>
</template>

<script setup lang="ts">
// 改变登录注册弹框状态
import store from '@/store';
const changeLoginDialogVisible = () => {
    store.commit('changeLoginDialogVisible');
};

</script>

<style lang="less" scoped>
.ctn {
    display: flex;
    flex-direction: column;

    .link {
        cursor: pointer;
        display: flex;
        align-items: center;
        padding: 0 1rem;
        border-radius: 0 2rem 2rem 0;
        margin: 0.3rem 0;
        height: 2.5rem;

        img {
            height: 1.8rem;
        }

        span {
            margin-left: 1rem;
        }
    }

    .link:hover {
        background-color: #f3f4f660;
    }

    .active {
        background-color: #f3f4f650;
    }

    .el-button {
        height: 2rem;
        background-color: #0091eb;
        font-size: 1rem;
        color: white;
        border-radius: 2rem;
    }
}
</style>