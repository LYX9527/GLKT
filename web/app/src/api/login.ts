import request from '@/server/index';

/**
 * 登录
 */
export const login = () => {
    return request({
        url: 'admin/vod/user/login',
        method: 'post'
    })
}

export const getInfo = () => {
    return request({
        url: 'admin/vod/user/info',
        method: 'get'
    })
}