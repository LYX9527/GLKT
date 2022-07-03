import axios from 'axios'
import {ElNotification, ElMessage} from 'element-plus'
import errorCode, {ErrorCode, NormalCode} from './errorCode'
import {tansParams} from './param'
import cache from './cache'

// axios.defaults.withCredentials = true;
const service = axios.create({
    baseURL: "http://localhost:11001",
    timeout: 10000
})
// request拦截器
service.interceptors.request.use(config => {
    const isRepeatSubmit = (config.headers || {}).repeatSubmit === false
    // get请求映射params参数
    if (config.method === 'get' && config.params) {
        let url = config.url + '?' + tansParams(config.params);
        url = url.slice(0, -1);
        config.params = {};
        config.url = url;
    }
    if (!isRepeatSubmit && (config.method === 'post' || config.method === 'put')) {
        const requestObj = {
            url: config.url,
            data: typeof config.data === 'object' ? JSON.stringify(config.data) : config.data,
            time: new Date().getTime()
        }
        const sessionObj = cache.session.getJSON('sessionObj')
        if (sessionObj === undefined || sessionObj === null || sessionObj === '') {
            cache.session.setJSON('sessionObj', requestObj)
        } else {
            const s_url = sessionObj.url;                // 请求地址
            const s_data = sessionObj.data;              // 请求数据
            const s_time = sessionObj.time;              // 请求时间
            const interval = 1000;                       // 间隔时间(ms)，小于此时间视为重复提交
            if (s_data === requestObj.data && requestObj.time - s_time < interval && s_url === requestObj.url) {
                const message = '数据正在处理，请勿重复提交';
                console.warn(`[${s_url}]: ` + message)
                return Promise.reject(new Error(message))
            } else {
                cache.session.setJSON('sessionObj', requestObj)
            }
        }
    }
    return config
}, error => {
    console.log(error)
    Promise.reject(error)
})
// 响应拦截器
service.interceptors.response.use(res => {
        // 未设置状态码则默认成功状态
        const code: ErrorCode | NormalCode = res.data.code || 200;
        // 获取错误信息
        const msg: string = errorCode[code] || res.data.msg  || errorCode['default']
        // 二进制数据则直接返回
        if (res.request.responseType === 'blob' || res.request.responseType === 'arraybuffer') {
            return res.data
        }
        if (code === 401) {
            return Promise.reject('无效的会话，或者会话已过期，请重新登录。')
        } else if (code === 500) {
            ElMessage({
                message: msg,
                type: 'error'
            })
            return Promise.reject(new Error(msg))
        } else if (code !== 200 && code !== 800 && code !== 801 && code !== 802 && code !== 803) {
            ElNotification.error({
                title: msg
            })
            return Promise.reject('error')
        } else if (code === 800 || code === 801 || code === 802 || code === 803) {
            return Promise.resolve(res.data)
        } else {
            return Promise.resolve(res.data)
        }
    },
    error => {
        console.log('err' + error)
        let {message} = error;
        if (message == "Network Error") {
            message = "后端接口连接异常";
        } else if (message.includes("timeout")) {
            message = "系统接口请求超时";
        } else if (message.includes("Request failed with status code")) {
            message = "系统接口" + message.substr(message.length - 3) + "异常";
        }
        ElMessage({
            message: message,
            type: 'error',
            duration: 5 * 1000
        })
        return Promise.reject(error)
    }
)

export default service