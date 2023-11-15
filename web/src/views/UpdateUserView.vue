<!--  -->
<template>
    <div class="ctn">
        <el-card class="user">
            <div style="display: flex;align-items: center;">
                <img :src="user.avatar" alt="">
                <el-upload :on-success="uploadSuccess" style="margin-left: 2rem;" :show-file-list="false" drag
                    action="/api/file/upload" multiple>
                    <img style="height: 2rem;" :src="require('@/assets/editAvatar.svg')" alt="">
                    <template #tip>
                        <div class="el-upload__tip">
                            点击或拖动图片修改头像
                        </div>
                    </template>
                </el-upload>
            </div>
            <div>
                <h4>当前信息：</h4>
                <p>账号：{{ user.account }}</p>
                <p>昵称：{{ user.name }}</p>
                <p>简介：{{ user.intro }}</p>
                <p>邮箱：{{ user.email }}</p>
            </div>
        </el-card>
        <el-card class="form">
            <h4>修改个人信息：</h4>
            <el-form label-position="top" label-width="100px" :model="updateInfo">
                <el-form-item label="昵称">
                    <el-input v-model.trim="updateInfo.name" />
                </el-form-item>
                <el-form-item label="简介">
                    <el-input v-model.trim="updateInfo.intro" />
                </el-form-item>
                <el-form-item label="邮箱">
                    <el-input v-model.trim="updateInfo.email" />
                </el-form-item>
                <el-form-item label="密码">
                    <el-input v-model.trim="updatePassword.password" />
                </el-form-item>
                <el-form-item label="确认密码">
                    <el-input v-model.trim="updatePassword.repassword" />
                </el-form-item>
                <el-button type="primary" @click="updateUser">修改基本信息</el-button>
                <el-button type="primary" @click="updatePwd">修改密码</el-button>
            </el-form>
        </el-card>
    </div>
</template>

<script setup lang="ts">
import { ref, reactive, onMounted, computed } from 'vue';
import { updateUserInfo } from '@/request/user';
import { ElNotification } from 'element-plus'

const user: any = ref({})

const updateInfo: any = reactive({
    name: '',
    intro: '',
    email: '',
});
const updatePassword: any = reactive({
    password: '',
    repassword: ''
})
const updateUser = async () => {
    if (updateInfo.name == '' && updateInfo.intro == '' && updateInfo.email == '') {
        ElNotification({
            title: '错误',
            message: '姓名,简介,邮箱至少有一项不为空',
            type: 'warning',
        })
        return
    }
    if (updateInfo.name == '') {
        updateInfo.name = user.value.name;
    }
    if (updateInfo.intro == '') {
        updateInfo.intro = user.value.intro;
    }
    if (updateInfo.email == '') {
        updateInfo.email = user.value.email;
    }
    const code = await updateUserInfo(updateInfo);
    if (code === 200) {
        getUserInfo();
    }
}
const updatePwd = async () => {
    if (updatePassword.password === '') {
        ElNotification({
            title: '警告',
            message: '密码不能为空',
            type: 'warning',
        })
        return
    } else if (updatePassword.password !== updatePassword.repassword) {
        ElNotification({
            title: '警告',
            message: '两次密码不一致！',
            type: 'warning',
        })
        return
    }
    const code = await updateUserInfo(updatePassword)
    if (code === 200) {
        getUserInfo();
    }
}
const getUserInfo = () => {
    user.value = localStorage.getItem('userInfo') ? JSON.parse(localStorage.getItem('userInfo') as string) : {}
}
const updateAvatar = reactive({
    avatar: ''
})
const uploadSuccess = async (res: any) => {
    if (res.code === 200) {
        updateAvatar.avatar = res.data;
        const code = await updateUserInfo(updateAvatar)
        if (code === 200) {
            getUserInfo();
        }
    }
}
onMounted(() => {
    getUserInfo();
});
</script>

<style lang="less" scoped>
.ctn {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100%;

    .user {
        width: 30vw;
        margin: 1rem;
        text-align: start;
        border-radius: 1rem;

        img {
            height: 5rem;
            width: 5rem;
        }

        p {
            font-size: 0.8rem;
        }
    }

    .form {
        width: 30vw;
        border-radius: 1rem;

    }
}
</style>