import {createRouter, createWebHistory, RouteRecordRaw} from 'vue-router'
import Index from '@/views/index.vue'
const routes: Array<RouteRecordRaw> = [
    {
        path: '/',
        name: 'Home',
        component: Index,
    },
    {
        path: '/login',
        name: 'Login',
        component: () => import("@/views/login.vue"),
    },
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router