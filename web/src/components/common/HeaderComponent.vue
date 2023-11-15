<!-- 头部组件 -->
<template>
    <div class="ctn">
        <div class="left" @click="router.replace('/')">
            <img :src="require('@/assets/communication.png')" alt="">
            <span>Talk To You</span>
        </div>
        <div class="right">
            <img class="btn" @click="toggleDark()"
                 :src="isDark ? require('@/assets/sun.svg') : require('@/assets/moon.svg')" alt="">
            <span @click="router.push('/private')">{{ user.name }}</span>
            <el-popover popper-style="width: 20rem;overflow: hidden;background-color: #e5e7eb;border-radius: 1rem;"
                :show-arrow="false" placement="bottom-start" trigger="click" :show-after="300"
                content="this is content, this is content, this is content">
                <template #reference>
                    <img v-show="user.avatar" class="avatar" :src="user.avatar" alt="">
                </template>
                <div class="top-popper">
                    <img style="" v-show="user.avatar" class="avatar-popper" :src="user.avatar" alt="">
                    <div style="display: flex;flex-direction: column;margin: 0.5rem auto;text-align: center;">
                        <span style="color: #0091eb;margin-top: 0.5rem;font-size: 1rem;">{{ user.name }}</span>
                        <span>{{ user.email }}</span>
                    </div>
                    <div>
                        <el-button @click="router.push('/update')" round style="width: 10rem">
                            <img style="height: 1rem;" :src="require('@/assets/edit.svg')" alt="">
                            <span style="margin-left: 0.5rem;color: #0091eb; font-weight: 600;">修改信息</span>
                        </el-button>
                    </div>
                </div>
                <div class="buttom-popper" @click="logout">
                    <img style="height: 1rem;" :src="require('@/assets/quit.svg')" alt="">
                    <span style="margin-left: 0.5rem;color: #0091eb;">退出登录</span>
                </div>
            </el-popover>
        </div>
    </div>
</template>

<script setup lang="ts">
import { ref, computed } from 'vue';

// 切换暗黑模式
import { useDark, useToggle } from '@vueuse/core'
import router from '@/router';

const isDark = useDark()
const toggleDark = useToggle(isDark)

// 从localstore获取用户名
const user = computed(() => {
    const userInfo: string | undefined = localStorage.getItem('userInfo') as string;
    return userInfo ? JSON.parse(userInfo) : {}
})

// 退出登录
const logout = () => {
    localStorage.removeItem('userInfo');
    localStorage.removeItem('token');
    window.location.reload()
}
</script>

<style lang="less" scoped>
.ctn {
    display: flex;
    justify-content: space-between;
    align-items: center;
    height: 4rem;

    img {
        cursor: pointer;
        height: 3rem;
        width: 3rem;
    }

    .left {
        display: flex;
        align-items: center;
        cursor: pointer;
    }

    .right {
        width: 15rem;
        display: flex;
        justify-content: space-around;
        align-items: center;

        .btn {
            border-radius: 50%;
            height: 1.5rem;
        }

        .btn:hover {
            background-color: #f3f4f6;
        }

        .avatar {
            border-radius: 50%;
            border: 0.5px solid #0091eb;
        }

        .avatar:hover {
            border-radius: 50%;
            border: 0.5px solid #d2d9dd;
        }

        span {
            cursor: pointer;
            // text-decoration: underline;
            filter: brightness(0.5);
        }

        span:hover {
            filter: brightness(1);
        }

    }

}

.top-popper {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    background-color: #ffffff95;
    margin: 0.2rem;
    padding: 1rem;
    border-radius: 1rem;
}

.buttom-popper {
    width: 100%;
    height: 2rem;
    display: flex;
    align-items: center;
    justify-content: center;
    cursor: pointer;
    border-radius: 1rem;
}

.avatar-popper {
    height: 5rem;
    width: 5rem;
    border-radius: 50%;
}

.buttom-popper:hover {
    background-color: #d5d7e5;
}
</style>