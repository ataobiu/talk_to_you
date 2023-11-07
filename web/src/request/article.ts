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