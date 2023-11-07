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
                    <el-button round @click="getPrivateArticle">
                        <img :src="require('@/assets/个人.svg')" alt="">
                        <span>个人</span>
                    </el-button>
                    <el-button round>
                        <img :src="require('@/assets/点赞.svg')" alt="">
                        <span>点赞</span>
                    </el-button>
                    <el-button round>
                        <img :src="require('@/assets/收藏.svg')" alt="">
                        <span>收藏</span>
                    </el-button>
                </el-affix>
            </div>
            <div class="buttom">
                <CardComponent :articleList="articleList" />
            </div>
        </div>
    </div>
</template>

<script setup lang="ts">
import { ref, reactive, onMounted, computed } from 'vue';
import { getArticles } from '@/request/article';

// 从localstore获取用户名
const user = computed(() => {
    const userInfo: string | undefined = localStorage.getItem('userInfo') as string;
    return userInfo ? JSON.parse(userInfo) : {}
})
const articleList: any = ref([])
const getPrivateArticle = async () => {
    // 获取文章
    articleList.value = await getArticles()
}
onMounted(() => {
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