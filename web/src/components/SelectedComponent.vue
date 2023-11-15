<!--  -->
<template>
    <el-dialog style="--el-dialog-border-radius:1rem" v-model="cardDialogVisible" width="60rem" align-center
        @close="changeCardDialogVisible" :show-close="false">
        <div class="ctn">
            <div class="left">
                <el-carousel height="70vh" autoplay>
                    <el-carousel-item v-for="(item, index) in images" :key="index">
                        <img :src="item" style="width: 100%; object-fit: contain;" alt="">
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
                        </div>
                        <div class="article">
                            <div style="display: flex;justify-content: space-between;align-items: center;">
                                <h3>{{ article.title }}</h3>
                                <img @click="deleteArticle" style="height: 1rem; cursor: pointer;"
                                    v-if="loginUser.id === user.id" :src="require('@/assets/delete.svg')" alt="">
                            </div>
                            <p>{{ article.content }}</p>
                            <span>{{ article.time }}</span>
                        </div>
                        <div class="comment" v-for="(item, index) in comments" :key="index">
                            <div class="leftComment">
                                <img :src="item.avatar" alt="">
                                <div class="commentRight">
                                    <h4>{{ item.name }}</h4>
                                    <p>{{ item.comment }}</p>
                                    <span>{{ item.time }}</span>
                                </div>
                            </div>
                            <img v-if="loginUser.id === item.id" @click="deleteSelfComment(item.commentId)" class="delete"
                                :src="require('@/assets/delete.svg')" alt="">
                        </div>
                        <div v-show="comments == ''"
                            style="display: flex;flex-direction: column; justify-content: center;align-items: center; height: 5rem;">
                            <p>还没有人评论</p>
                            <el-button type="primary" round @click="goToComment">抢沙发</el-button>
                        </div>
                    </div>
                </div>
                <div class="rEnd">
                    <div class="buttons">
                        <div class="left">
                            <img v-if="article.isLike === 0" @click="goToLike" :src="require('@/assets/unLike.svg')" alt="">
                            <img v-else @click="goToLike" :src="require('@/assets/like.svg')" alt="">
                            <span>{{ article.likes }}</span>
                            <img v-if="article.isCollect === 0" @click="goToCollect"
                                :src="require('@/assets/unCollect.svg')" alt="">
                            <img v-else @click="goToCollect" :src="require('@/assets/Collect.svg')" alt="">
                            <span>{{ article.collects }}</span>
                            <img :src="require('@/assets/comment.svg')" @click="goToComment" alt="">
                            <span>{{ commentsCount }}</span>
                        </div>
                        <img :src="require('@/assets/Share.svg')" alt="">
                    </div>
                    <div style="display: flex;">
                        <el-input ref="refInput" @keyup.enter="addNewComment" style="margin-right: 0.5rem;" maxlength="300"
                            show-word-limit v-model.trim="newComment" :autosize="{ minRows: 1, maxRows: 4 }" type="textarea"
                            placeholder="说点什么..." />
                        <el-button type="primary" v-show="newComment != ''" @click="addNewComment">发送</el-button>
                    </div>
                </div>
            </div>
        </div>
    </el-dialog>
</template>

<script setup lang="ts">
import { ref, watch, defineProps, onMounted, defineEmits } from 'vue'
import store from '@/store'
import { getDetailByArticleId, deleteArticleById } from '@/request/article'
import { addComment, deleteComment } from '@/request/comment'
import { likeArticle } from '@/request/like'
import { collectArticle } from '@/request/collect'

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
const newComment = ref('')

const addNewComment = async () => {
    if (newComment.value) {
        const commentData = {
            id: article.value.id,
            comment: newComment.value
        }
        const code: number = await addComment(commentData)
        if (code === 200) {
            getData()
        }
    }
    refInput.value.clear();
}
const refInput = ref()
const goToComment = () => {
    refInput.value.focus()
}

// 删除评论
const deleteSelfComment = async (id: number) => {
    const code: number = await deleteComment(id)
    if (code === 200) {
        getData()
    }
}

const loginUser = ref()
const getData = async () => {
    const data = await getDetailByArticleId(props.articleId)
    article.value = data
    user.value = data.user
    images.value = data.images.split(',');
    commentsCount.value = data.comments.total
    comments.value = data.comments.comments
}

// 点赞文章
const goToLike = async () => {
    await likeArticle(article.value.id)
    getData()
}

// 去收藏
const goToCollect = async () => {
    await collectArticle(article.value.id)
    getData()
}

const emit = defineEmits(['reloadArticle'])
const deleteArticle = async () => {
    await deleteArticleById(article.value.id)
    emit('reloadArticle')
    store.commit("changeCardDialogVisible");
}
onMounted(() => {
    loginUser.value = localStorage.getItem('userInfo') ? JSON.parse(localStorage.getItem('userInfo') as string) : {}
    getData();
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
                        width: 3rem;
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
                    justify-content: space-between;

                    .leftComment {
                        display: flex;

                        img {
                            height: 2rem;
                            width: 2rem;
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

                    .delete {
                        height: 0.8rem;
                        cursor: pointer;
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
                    height: 1.3rem;
                    width: 1.3rem;
                    margin-left: 0.5rem;
                    cursor: pointer;
                }

                .left {
                    display: flex;
                    align-items: center;

                    span {
                        margin-left: 0.2rem;
                        font-size: 0.8rem;
                    }
                }
            }

        }

    }
}
</style>