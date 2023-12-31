import store from '@/store';
import axios from 'axios'

const request = axios.create({
    baseURL: '/api',
    timeout: 5000
})

// request 拦截器
// 可以自请求发送前对请求做一些处理
// 比如统一加token，对请求参数统一加密
request.interceptors.request.use((config) => {
    const token = localStorage.getItem('token') ? localStorage.getItem('token') : ''
    config.headers['Content-Type'] = 'application/json;charset=utf-8';
    if (token) {
        config.headers['token'] = token
    }
    // 如果请求数据中包含文件对象，使用FormData处理
    if (config.data instanceof FormData) {
        config.headers['Content-Type'] = 'multipart/form-data';
    }
    return config
},
    (error) => {
        return Promise.reject(error)
    }
);

// response 拦截器 
// 可以在接口响应后统一处理结果
request.interceptors.response.use(
    response => {
        let res = response.data;
        // 如果是返回的文件
        if (response.config.responseType === 'blob') {
            return res
        }
        // 兼容服务端返回的字符串数据
        if (typeof res === 'string') {
            res = res ? JSON.parse(res) : res
        }
        if (res.code === 401) {
            store.commit('changeLoginDialogVisible')
        }
        return res;
    },
    error => {
        console.error('err' + error) // for debug
        return Promise.reject(error)
    }
)

export default request