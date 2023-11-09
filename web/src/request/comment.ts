import request from ".";
import { ElNotification } from 'element-plus'
import 'element-plus/dist/index.css'

// 新增评论
export const addComment = async (data: any) => {
    try {
        const res: any = await request.post('/comment/add', data)
        if (res.code === 200) {
            ElNotification({
                title: '成功',
                message: res.data,
                type: 'success',
            })
            return res.code;
        } else if (res.code === 400) {
            ElNotification({
                title: '失败',
                message: res.data,
                type: 'warning',
            })
        }
    } catch (error) {
        console.log(error);
    }
}

export const deleteComment = async (id: number) => {
    try {
        const res: any = await request.post('/comment/delete/' + id)
        if (res.code === 200) {
            ElNotification({
                title: '成功',
                message: res.data,
                type: 'success',
            })
            return res.code;
        } else if (res.code === 400) {
            ElNotification({
                title: '失败',
                message: res.data,
                type: 'warning',
            })
        }
    } catch (error) {
        console.log(error);
    }
}