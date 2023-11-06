import request from ".";

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