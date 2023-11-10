<!--  -->
<template>
    <div class="ctn">
        <div class="user">
            <img :src="user.avatar" alt="">
            <div class="right">
                <h2>
                    {{ user.name }}
                </h2>
                <div>
                    <span>账号：{{ user.account }}</span>
                    <span>{{ user.intro }}</span>
                </div>
            </div>
        </div>
        <el-divider border-style="dashed" />
        <div class="content">
            <div class="btn">
                <el-affix :offset="100">
                    <el-button round @click="getPrivateArticle()">
                        <img :src="require('@/assets/个人.svg')" alt="">
                        <span>个人</span>
                    </el-button>
                    <el-button round @click="getLikesArticle()">
                        <img :src="require('@/assets/点赞.svg')" alt="">
                        <span>点赞</span>
                    </el-button>
                    <el-button round @click="getCollectArticle()">
                        <img :src="require('@/assets/收藏.svg')" alt="">
                        <span>收藏</span>
                    </el-button>
                </el-affix>
            </div>
            <div class="buttom">
                <CardComponent @reloadArticle="getPrivateArticle" :articleList="articleList.reverse()" />
            </div>
        </div>
    </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { getPrivateArticleByUserId, getLikesArticleByUserId, getCollectArticleByUserId } from '@/request/article';

// 从localstore获取用户名
const user: any = ref({})
const articleList: any = ref([])

const getPrivateArticle = async () => {
    articleList.value = await getPrivateArticleByUserId()
}

const getLikesArticle = async () => {
    articleList.value = await getLikesArticleByUserId()

}
const getCollectArticle = async () => {
    articleList.value = await getCollectArticleByUserId()

}

onMounted(() => {
    const userInfo: string | undefined = localStorage.getItem('userInfo') as string;
    user.value = userInfo ? JSON.parse(userInfo) : {}
    getPrivateArticle();
});
</script>

<style lang="less" scoped>
.ctn {
    .user {
        display: flex;
        align-items: center;
        justify-content: center;
        margin: 1rem auto;

        img {
            height: 8rem;
            border-radius: 50%;
        }

        .right {
            margin-left: 3rem;

            div {
                display: flex;
                flex-direction: column;
                align-items: start;
                color: #646566;

                span {
                    font-size: 1rem;
                }
            }
        }
    }

    .content {
        .btn {
            height: 2rem;

            img {
                height: 1rem;
            }
        }

        .buttom {}
    }
}
</style>