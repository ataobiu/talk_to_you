<!--  -->
<template>
    <div class="ctn">
        <el-card class="box-card">
            <div style="display: flex;">
                <img style="height: 2rem;" :src="require('@/assets/发布.svg')" alt="">
                <h4>新发布：</h4>
            </div>
            <el-upload :on-success="uploadSuccess" :limit="6" action="http://localhost:9090/api/file/upload"
                list-type="picture-card" :on-remove="handleRemove">
                <img style="height: 2rem;" :src="require('@/assets/新增.svg')" alt="">
                <template #file="{ file }">
                    <div>
                        <img class="el-upload-list__item-thumbnail" :src="file.url" alt="" />
                        <span class="el-upload-list__item-actions">
                            <span class="el-upload-list__item-delete" v-if="!disabled" @click="handleRemove(file)">
                                <img style="height: 1.5rem;" :src="require('@/assets/删除.svg')" alt="">
                            </span>
                        </span>
                    </div>
                </template>
            </el-upload>
            <el-form label-position="top" label-width="100px" :model="newArticle">
                <el-form-item label="标题">
                    <el-input v-model.trim="newArticle.title" clearable />
                </el-form-item>
                <el-form-item label="内容">
                    <el-input v-model.trim="newArticle.content" type="textarea" clearable
                        :autosize="{ minRows: 4, maxRows: 4 }" maxlength="500" show-word-limit />
                </el-form-item>
                <el-form-item label="是否展示">
                    <el-radio-group v-model="newArticle.isShow">
                        <el-radio label="是" />
                        <el-radio label="否" />
                    </el-radio-group>
                </el-form-item>
                <el-button type="primary" @click="goToAddNewArticle">发布</el-button>
            </el-form>
        </el-card>
    </div>
</template>

<script setup lang="ts">
import { ref, reactive } from 'vue'
import { ElNotification } from 'element-plus'
import { addNewArticle } from '@/request/article'

const newArticle = reactive({
    title: '',
    images: [],
    content: '',
    isShow: "是",
})
const addArticle = reactive({
    title: '',
    images: '',
    content: '',
    isShow: 0,
})

const goToAddNewArticle = () => {
    if (newArticle.title === '' || newArticle.content === '') {
        ElNotification({
            title: '警告',
            message: '标题或内容不能为空',
            type: 'warning',
        })
        return
    } else if (newArticle.images.length === 0) {
        ElNotification({
            title: '警告',
            message: '至少上传一张图片',
            type: 'warning',
        })
        return
    }
    addArticle.title = newArticle.title;
    addArticle.content = newArticle.content;
    if (newArticle.isShow === '否') {
        addArticle.isShow = 1
    }
    const images = newArticle.images.join(',');
    addArticle.images = images;
    addNewArticle(addArticle)
}

const disabled = ref(false)

const handleRemove = (file: any) => {
    const data = newArticle.images.filter(item => item !== file.response.data);
    newArticle.images = data;
}
const uploadSuccess = (res: any) => {
    console.log(res.data);
    newArticle.images.push(res.data);
}
</script>

<style lang="less" scoped>
.ctn {
    .box-card {
        display: flex;
        flex-direction: column;
        text-align: start;
    }
}
</style>