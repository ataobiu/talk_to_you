<!--  -->
<template>
    <el-dialog style="--el-dialog-border-radius:1rem" v-model="cardDialogVisible" width="60rem" align-center
        @close="changeCardDialogVisible" :show-close="false">
        <div class="ctn">
            <div class="left">
                <el-carousel height="70vh" autoplay>
                    <el-carousel-item v-for="(item, index) in images" :key="index">
                        <img :src="item" style="height: 70vh; object-fit: contain;" alt="">
                    </el-carousel-item>
                </el-carousel>
            </div>
            <div class="right">
                <div class="rTop">
                    <div class="rCenter">
                        <div class="header">
                            <div style="display: flex; align-items: center;">
                                <img :src="user.avatar">
                                <span>{{ user.name }}</span>
                            </div>
                            <el-button round type="danger">关注</el-button>
                        </div>
                        <div class="article">
                            <h3>{{ article.title }}</h3>
                            <p>{{ article.content }}</p>
                            <!-- <span>{{ article.time }}</span> -->
                            <span>2022-02-26</span>
                        </div>
                        <div class="comment" v-for="(item, index) in comments" :key="index">
                            <img :src="item.avatar" alt="">
                            <div class="commentRight">
                                <h4>{{ item.name }}</h4>
                                <p>{{ item.comment }}</p>
                                <span>{{ item.time }}</span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="rEnd">
                    <div class="buttons">
                        <div class="left">
                            <img :src="require('@/assets/点赞.svg')" alt="">
                            <span>{{ article.likes }}</span>
                            <img :src="require('@/assets/收藏.svg')" alt="">
                            <span>{{ article.collects }}</span>
                        </div>
                        <img :src="require('@/assets/分享.svg')" alt="">
                        <span>{{ commentsCount }}</span>

                    </div>
                    <div>
                        <el-form>
                            <el-input v-model="newComment" rows="1" autosize type="textarea" placeholder="说点什么...">
                                <template #button>
                                    <van-button size="small" type="primary" v-show="newComment != ''">发送</van-button>
                                </template>
                            </el-input>
                        </el-form>
                    </div>
                </div>
            </div>
        </div>
    </el-dialog>
</template>

<script setup lang="ts">
import { ref, watch, defineProps, onMounted, computed, reactive } from 'vue'
import store from '@/store'
import { getDetailByArticleId } from '@/request/article'
import { da } from 'element-plus/es/locale';

const cardDialogVisible = ref(store.state.cardDialogVisible)
const changeCardDialogVisible = () => {
    store.commit('changeCardDialogVisible')
}
watch(
    () => store.state.cardDialogVisible,
    (newValue) => {
        cardDialogVisible.value = newValue;
    }
);
const props = defineProps({
    articleId: {
        type: Number,
        required: true
    }
})
const article: any = ref({})
const user: any = ref({});
const images = ref([]);
const commentsCount = ref(0);
const comments = ref();
const newComment = ref()
onMounted(async () => {
    const data = await getDetailByArticleId(props.articleId)
    article.value = data
    user.value = data.user
    images.value = data.images.split(',');
    commentsCount.value = data.comments.total
    comments.value = data.comments.comments

})
</script>

<style lang="less" scoped>
.ctn {
    display: flex;
    max-height: 70vh;

    .left {
        width: 65%;
    }

    .right {
        width: 35%;
        display: flex;
        flex-direction: column;
        border-left: 1px solid #999;

        .rTop {
            margin: 0 1rem;
            height: 100%;
            overflow-x: hidden;

            .rCenter {
                margin: 0 1rem;

                .header {
                    display: flex;
                    justify-content: space-between;
                    align-items: center;

                    img {
                        height: 3rem;
                        border-radius: 50%;
                    }

                    span {
                        margin-left: 1rem;
                        font-size: 1rem;
                    }
                }

                .article {
                    border-bottom: 1px solid hsla(0, 22%, 75%, 0.226);
                    text-align: start;

                    h3 {
                        margin: 1rem 0;
                    }
                }

                .comment {
                    display: flex;
                    margin: 1rem 0;
                    align-items: start;
                    text-align: start;

                    img {
                        height: 2rem;
                        border-radius: 50%;
                        cursor: pointer;
                    }

                    .commentRight {
                        margin-left: .5rem;

                        h4 {
                            margin: 0;
                        }

                        span {
                            font-size: 0.6rem;
                        }
                    }
                }
            }

        }



        .rEnd {
            padding: 0rem 1rem;
            border-top: 1px solid hsla(0, 22%, 75%, 0.226);

            .buttons {
                display: flex;
                justify-content: space-between;
                align-items: center;
                padding: 0.5rem;

                img {
                    height: 1rem;
                    margin-left: 0.5rem;
                    cursor: pointer;
                }

                .left {
                    display: flex;
                    align-items: center;

                    span {
                        margin-left: 0.5rem;
                        font-size: 1rem;
                    }
                }
            }

        }

    }
}
</style>