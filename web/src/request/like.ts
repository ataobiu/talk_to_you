import request from ".";
import { ElNotification } from 'element-plus'
import 'element-plus/dist/index.css'

// 点赞文章
export const likeArticle = async (id: number) => {
    try {
        const res: any = await request.post('/like/' + id)
        if (res.code === 200) {
            ElNotification({
                title: '成功',
                message: res.msg,
                type: 'success',
            })
            return res.code;
        } else if (res.code === 400) {
            ElNotification({
                title: '失败',
                message: res.msg,
                type: 'warning',
            })
        }
    } catch (error) {
        console.log(error);
    }
}