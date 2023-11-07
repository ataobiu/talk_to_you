<!--  -->
<template>
    <div class="ctn">
        <div class="box" v-for="item in articleList" :key="item.id">
            <div class="t" @click="userSelect(item.id)">
                <img :src="item.cover" alt="">
                <el-text class="title" truncated>
                    {{ item.title }}
                </el-text>
            </div>
            <div class="b">
                <div class="left" @click="userSelectAuthor(item.user.id)">
                    <img :src="item.user.avatar" alt="">
                    <p>{{ item.user.name }}</p>
                </div>
                <div class="right">
                    <i class="iconfont icon-dianzan"></i>
                    <p>{{ item.likes }}</p>
                </div>
            </div>
        </div>
        <SelectedComponent v-if="destroyDialog" :articleId="articleId" />
    </div>
</template>

<script setup lang="ts">
import { ref, defineProps, computed, watch } from 'vue';
import store from '@/store';

const articleId = ref()
const destroyDialog = ref(store.state.cardDialogVisible)
const userSelect = (id: number) => {
    store.commit('changeCardDialogVisible')
    articleId.value = id
}
const userSelectAuthor = (id: number) => {
    console.log(id)
}
const props = defineProps({
    articleList: {
        type: Array
    }
})
const articleList: any = computed(() => {
    return props.articleList
});
watch(
    () => store.state.cardDialogVisible,
    (newValue) => {
        destroyDialog.value = newValue;
    }
);
</script>

<style lang="less" scoped>
.ctn {
    display: grid;
    grid-template-columns: 1fr 1fr 1fr 1fr 1fr;
    gap: 2rem 1rem;


    .box {
        height: 20rem;

        .t {
            cursor: pointer;

            img {
                height: 17rem;
                border-radius: 0.7rem;
                transition: filter 0.3s ease;

            }

            img:hover {
                filter: brightness(0.7);
            }


            .title {
                height: 1rem;
                color: inherit;
                width: 11rem;
                text-align: start;
            }
        }

        .b {
            height: 2rem;
            display: flex;
            align-items: center;
            justify-content: space-around;

            .left {
                cursor: pointer;
                display: flex;
                height: 2rem;
                align-items: center;

                img {
                    height: 1.5rem;
                    border-radius: 50%;
                }

                p {
                    margin-left: 0.2rem;
                    font-size: 0.6rem;
                    filter: brightness(0.7);

                }
            }

            .left:hover p {
                filter: brightness(1);
            }

            .right {
                cursor: pointer;
                height: 2rem;
                display: flex;
                align-items: center;


                .iconfont {
                    margin-right: 0.5rem;
                    filter: brightness(0.7);

                }

                p {
                    font-size: 0.8rem;
                    filter: brightness(0.7);

                }
            }

            .right:hover i,
            .right:hover p {
                filter: brightness(1);
            }
        }
    }


}
</style>