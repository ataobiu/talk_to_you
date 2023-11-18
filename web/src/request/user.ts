import request from ".";
import { ElNotification } from 'element-plus'

// 点赞文章
export const updateUserInfo = async (data: any) => {
    try {
        const res: any = await request.post('/user/update', data)
        if (res.code === 200) {

            ElNotification({
                title: '成功',
                message: res.msg,
                type: 'success',
            })
            // 将res.data存入localStorage
            localStorage.setItem('token', res.data.token);
            localStorage.setItem('userInfo', JSON.stringify(res.data.user));
        } else if (res.code === 400) {
            ElNotification({
                title: '失败',
                message: res.msg,
                type: 'warning',
            })
        }
        return res.code;
    } catch (error) {
        // console.log(error);
    }
}