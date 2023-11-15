import request from ".";
import { ElNotification } from 'element-plus'
import 'element-plus/dist/index.css'
import store from '@/store'

// 登录
export const goToLogin = async (data: any) => {
    try {
        const res: any = await request.post("/user/login", data);
        if (res.code === 200) {
            ElNotification({
                title: '登录成功',
                message: '',
                type: 'success',
            })
            // 将res.data存入localStorage
            localStorage.setItem('token', res.data.token);
            localStorage.setItem('userInfo', JSON.stringify(res.data.user));
            window.location.reload();
        } else if (res.code === 400) {
            // 处理其他业务逻辑
            ElNotification({
                title: '登录失败',
                message: res.msg,
                type: 'warning',
            })
        }
    } catch (error) {
        // console.log(error);
    }
};

// 注册
export const goToRegister = async (data: any) => {
    try {
        const res: any = await request.post('/user/register', data);
        if (res.code === 200) {
            ElNotification({
                title: '注册成功',
                message: '',
                type: 'success',
            })
            // red.data存入cookie
            localStorage.setItem('token', res.data.token);
            localStorage.setItem('userInfo', JSON.stringify(res.data.user));
            window.location.reload();
        } else if (res.code === 400) {
            ElNotification({
                title: '登录失败',
                message: res.msg,
                type: 'warning',
            })
        }
    } catch (error) {
        // console.log(error);
    }

};
