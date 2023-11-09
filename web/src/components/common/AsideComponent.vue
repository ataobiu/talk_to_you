<!--  -->
<template>
    <div class="ctn">
        <router-link :to="{ path: '/home' }" class="link" active-class="active">
            <img :src="require('@/assets/发现.svg')" alt="">
            <span>发现</span>
        </router-link>
        <router-link v-show="isLogin" :to="{ path: '/search' }" class="link" active-class="active">
            <img :src="require('@/assets/Search bar_selected.svg')" alt="">
            <span>搜索</span>
        </router-link>
        <router-link :to="{ path: '/private' }" class="link" active-class="active">
            <img :src="require('@/assets/我的.svg')" alt="">
            <span>我的</span>
        </router-link>
        <router-link v-show="isLogin" :to="{ path: '/publish' }" class="link" active-class="active">
            <img :src="require('@/assets/发布.svg')" alt="">
            <span>发布</span>
        </router-link>
        <el-button v-show="!isLogin" @click="changeLoginDialogVisible" round>
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
import { computed } from 'vue';
const isLogin = computed(() => {
    return localStorage.getItem('token') ? true : false;
})
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