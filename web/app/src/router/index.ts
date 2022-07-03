import {createRouter, createWebHistory, RouteRecordRaw} from 'vue-router'
const routes: Array<RouteRecordRaw> = [
    {
        path: '/',
        name: 'Home',
        component: () => import("@/views/login.vue"),
    },
    {
        path: '/login',
        name: 'Login',
        component: () => import("@/views/login.vue"),
    },
    {
        path: '/register',
        name: 'Register',
        component: () => import("@/views/register.vue"),
    },
    {
        path: '/index',
        name: 'Index',
        component: () => import("@/views/index.vue"),
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router