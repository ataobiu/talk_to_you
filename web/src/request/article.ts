import request from ".";
import { ElNotification } from 'element-plus'
import 'element-plus/dist/index.css'

// 获取首页发现内容
export const getArticles = async () => {
    try {
        const res: any = await request.get('/article/explore')
        if (res.code === 200) {
            return res.data
        }
    } catch (error) {
        console.log(error);
    }
}

// 获取文章详细内容
export const getDetailByArticleId = async (id: number) => {
    try {
        const res: any = await request.get('/article/detail/' + id)
        if (res.code === 200) {
            return res.data
        }
    } catch (error) {
        console.log(error);
    }
}
// 获取个人发布的文章
export const getPrivateArticleByUserId = async () => {
    try {
        const res: any = await request.get('/article/private')
        if (res.code === 200) {
            return res.data
        }
    } catch (error) {
        console.log(error);
    }
}

// 获取个人点赞的文章
export const getLikesArticleByUserId = async () => {
    try {
        const res: any = await request.get('/article/likes')
        if (res.code === 200) {
            return res.data
        }
    } catch (error) {
        console.log(error);
    }
}

// 获取个人收藏的文章
export const getCollectArticleByUserId = async () => {
    try {
        const res: any = await request.get('/article/collects')
        if (res.code === 200) {
            return res.data
        }
    } catch (error) {
        console.log(error);
    }
}
// 删除个人发布的文章
export const deleteArticleById = async (id: number) => {
    try {
        const res: any = await request.post('/article/delete/' + id)
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

// 发布新文章
export const addNewArticle = async (data: any) => {
    try {
        const res: any = await request.post('/article/add', data)
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

// 搜索文章
export const searchArticleByTtle = async (title: any) => {
    try {
        const res: any = await request.post('/article/search/' + title)
        if (res.code === 200) {
            ElNotification({
                title: '成功',
                message: res.msg,
                type: 'success',
            })
            return res;
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