import request from '@/server/index';

/**
 * 登录
 */
export const login = (data:ILoginFrom) => {
    return request({
        url: 'admin/vod/user/login',
        method: 'post',
        data
    })
}

export const getInfo = (params:{id:number}) => {
    return request({
        url: 'admin/vod/user/info',
        method: 'get',
        params
    })
}

export const verificationCode = () => {
    return request({
        url: 'verificationCode',
        method: 'get'
    })
}

export const register = (data:IRegisterFrom) => {
    return request({
        url: 'admin/vod/user/register',
        method: 'post',
        data
    })
}