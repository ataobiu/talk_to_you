<!--  -->
<template>
  <el-dialog style="--el-dialog-border-radius:2rem" v-model="loginDialogVisible" title="Talk To You" width="35rem"
    align-center destroy-on-close draggable @close="changeLoginDialogVisible" :show-close="false">
    <div class="ctn">
      <div class="left">
        <img :src="require('@/assets/communication.png')" alt="">
        <div>
          <p>{{ text }}</p>
          <p>{{ text2 }}</p>
        </div>
      </div>
      <div class="right">
        <el-form @keyup.enter="submitForm(ruleFormRef)" :rules="rules" v-if="loginOrRegister" ref="ruleFormRef"
          :model="ruleForm" status-icon>
          <el-form-item>
            <el-input autofocus v-model="ruleForm.account" autocomplete="off">
              <template #prefix>
                <img :src="require('@/assets/account.svg')" alt="">
                <span>账号</span>
              </template>
            </el-input>
          </el-form-item>
          <el-form-item>
            <el-input v-model="ruleForm.password" type="password" autocomplete="off">
              <template #prefix>
                <img :src="require('@/assets/passwd.svg')" alt="">
                <span>密码</span>
              </template>
            </el-input>
          </el-form-item>
          <el-form-item prop="validCode">
            <div class="validCode">
              <el-tooltip effect="dark" content="点击刷新" placement="left-start">
                <ValidCode class="code" @update:value="getCode" />
              </el-tooltip>
              <el-input placeholder="验证码" class="input" v-model="ruleForm.validCode">
                <template #prefix>
                  <img :src="require('@/assets/VerificationCode.svg')" alt="">
                </template>
              </el-input>
            </div>
          </el-form-item>
          <el-button type="primary" @click="submitForm(ruleFormRef)">登录</el-button>
        </el-form>
        <el-form @keyup.enter="submitForm(ruleFormRef)" :rules="rules" v-else ref="ruleFormRef" :model="ruleForm"
          status-icon>
          <el-form-item prop="account">
            <el-input autofocus v-model="ruleForm.account" autocomplete="off">
              <template #prefix>
                <img :src="require('@/assets/account.svg')" alt="">
                <span>账号</span>
              </template>
            </el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input v-model="ruleForm.password" type="password" autocomplete="off">
              <template #prefix>
                <img :src="require('@/assets/passwd.svg')" alt="">
                <span>密码</span>
              </template>
            </el-input>
          </el-form-item>
          <el-form-item prop="repassword">
            <el-input placeholder="再次确认密码" v-model="ruleForm.repassword" type="password" autocomplete="off">
              <template #prefix>
                <img :src="require('@/assets/passwd.svg')" alt="">
                <span>密码</span>
              </template>
            </el-input>
          </el-form-item>
          <el-form-item prop="email">
            <el-input v-model="ruleForm.email" autocomplete="off">
              <template #prefix>
                <img :src="require('@/assets/email.svg')" alt="">
                <span>邮箱</span>
              </template>
            </el-input>
          </el-form-item>
          <el-form-item prop="validCode">
            <div class="validCode">
              <el-tooltip effect="dark" content="点击刷新" placement="left-start">
                <ValidCode class="code" @update:value="getCode" />
              </el-tooltip>
              <el-input placeholder="验证码" class="input" v-model="ruleForm.validCode">
                <template #prefix>
                  <img :src="require('@/assets/VerificationCode.svg')" alt="">
                </template>
              </el-input>
            </div>
          </el-form-item>
          <el-button type="primary" @click="submitForm(ruleFormRef)">登录</el-button>
        </el-form>
        <div class="changeBtn">
          <a v-if="loginOrRegister" @click="loginOrRegister = !loginOrRegister" round>注册新账号</a>
          <a v-else @click="loginOrRegister = !loginOrRegister" round>返回登录</a>
        </div>
      </div>
    </div>
  </el-dialog>
</template>

<script setup lang="ts">
import { ref, watch, reactive } from 'vue';
// 获取登录注册框状态
import store from '@/store';
const loginDialogVisible = ref(store.state.loginDialogVisible);

// 改变登录注册框状态
const changeLoginDialogVisible = () => {
  store.commit('changeLoginDialogVisible');
};
const text = '我想大声告诉你'
const text2 = '你一直在我的世界里'

// 监听登录注册框状态改变
watch(
  () => store.state.loginDialogVisible,
  (newValue) => {
    loginDialogVisible.value = newValue;
  }
);

// 登录注册切换
const loginOrRegister = ref(true)

const getCode = (data: string) => {
  // ruleForm.validCode = data.toLowerCase();
}

// 表单效验
import type { FormInstance, FormRules } from 'element-plus'

const ruleFormRef = ref<FormInstance>()

const validatePass = (rule: any, value: any, callback: any) => {
  if (value === '') {
    callback(new Error('请再次输入密码'))
  } else if (value !== ruleForm.password) {
    callback(new Error("两次密码输入不一致！"))
  } else {
    callback()
  }
}
const ruleForm = reactive({
  account: '',
  password: '',
  repassword: '',
  validCode: '',
  email: ''
})
const rules = reactive<FormRules<typeof ruleForm>>({
  repassword: [{ validator: validatePass, trigger: 'blur' }],
})

import { goToLogin, goToRegister } from '@/request/login';
const submitForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return
  formEl.validate((valid) => {
    if (valid) {
      if (!ruleForm.email) {
        goToLogin({
          account: ruleForm.account,
          password: ruleForm.password,
        })
      } else {
        goToRegister({
          account: ruleForm.account,
          password: ruleForm.password,
          email: ruleForm.email,
        })
      }
    } else {
      return false
    }
  })
}

</script>

<style lang="less" scoped>
.ctn {
  display: flex;
  align-items: center;
  justify-content: space-around;

  .left {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    transition: all 0.5s;

    img:hover {
      transform: scale(1.2);
      cursor: pointer;
    }

    p {
      font-size: 20px;
      font-family: 'Courier New', Courier, monospace;
      font-weight: bold;
      text-shadow: 1px -1px 2px rgb(2, 104, 238);
      cursor: pointer;
    }

    p:hover {
      transform: scale(1.2);
    }
  }

  .right {
    width: 15rem;

    img {
      height: 1rem;
    }

    span {
      margin-left: 0.5rem;
    }

    .validCode {
      display: flex;
      align-items: center;
      height: 2rem;

      .code {
        background-color: rgb(253, 253, 253);
        border-radius: 1rem;
        height: 2rem;
        max-width: 5rem;

      }

      .input {
        margin-left: 2rem;
        width: 100%;
      }
    }

    .el-button {
      width: 100%;
      height: 2rem;
      border-radius: 1rem;
    }

    .changeBtn {
      display: flex;
      justify-content: end;
      margin-top: 1rem;
      font-size: 0.8rem;

      a:hover {
        color: #0091eb;
        cursor: pointer;
      }
    }
  }
}
</style>