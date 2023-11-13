<!--  -->
<template>
    <div class="ctn">
        <el-card class="box-card">
            <div style="display: flex;">
                <img style="height: 2rem;" :src="require('@/assets/发布.svg')" alt="">
                <h4>新发布：</h4>
            </div>
            <el-upload multiple v-model:file-list="fileList" :auto-upload="false" list-type="picture-card"
                :on-preview="handlePictureCardPreview" :on-remove="handleRemove">
                <img style="height: 2rem;" :src="require('@/assets/新增.svg')" alt="">
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
                        <el-radio label="0">是</el-radio>
                        <el-radio label="1">否</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-button type="primary" @click="goToAddNewArticle">发布</el-button>
            </el-form>
        </el-card>
        <el-dialog style="border-radius: 1rem;" v-model="dialogVisible">
            <img style="height: 50vh;" :src="dialogImageUrl" alt="Preview Image" />
        </el-dialog>
    </div>
</template>

<script setup lang="ts">
import { ref, reactive } from 'vue'
import { ElNotification } from 'element-plus'
import { addNewArticle } from '@/request/article'
import router from '@/router';
import type { UploadProps, UploadUserFile } from 'element-plus'

// 创建图片数组对象
const fileList = ref<UploadUserFile[]>([])
const newArticle = reactive({
    title: '',
    content: '',
    isShow: '',
})

const goToAddNewArticle = async () => {
    if (fileList.value.length === 0) {
        ElNotification({
            title: '警告',
            message: '至少上传一张图片',
            type: 'warning',
        })
        return
    } else if (newArticle.title === '' || newArticle.content === '') {
        ElNotification({
            title: '警告',
            message: '标题或内容不能为空',
            type: 'warning',
        })
        return
    }
    const formData = new FormData();
    fileList.value.forEach((item: any) => {
        formData.append('images', item.raw);
    });
    formData.append('title', newArticle.title);
    formData.append('content', newArticle.content);
    formData.append('isShow', newArticle.isShow);

    const code = await addNewArticle(formData)
    if (code === 200) {
        router.push("/private")
    }
}


// const handleRemove = (file: any) => {
//     const data = newArticle.images.filter(item => item !== file.response.data);
//     newArticle.images = data;
// }

const handleRemove: UploadProps['onRemove'] = (uploadFile, uploadFiles) => {
    console.log(uploadFile, uploadFiles)
    console.log(fileList.value.length);
}
// 展示图片
const dialogImageUrl = ref('')
const dialogVisible = ref(false)
const handlePictureCardPreview: UploadProps['onPreview'] = (uploadFile) => {
    dialogImageUrl.value = uploadFile.url!
    dialogVisible.value = true
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