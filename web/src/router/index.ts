import { createRouter, createWebHistory } from 'vue-router'
import store from '@/store'
const routes = [
  {
    path: "/",
    component: () => import('@/Layout/LayoutComponent.vue'),
    redirect: "/home",
    children: [
      {
        // 首页
        path: "/home",
        component: () => import('@/views/HomeView.vue')
      },
      {
        // 消息
        path: "/message",
        component: () => import('@/views/MessageView.vue')
      },
      {
        // 发布
        path: "/publish",
        component: () => import('@/views/PublishView.vue')
      },
      {
        // 个人主页
        path: "/private",
        component: () => import('@/views/PrivateView.vue')
      },
      {
        // 修改个人信息页
        path: "/update",
        component: () => import('@/views/UpdateUserView.vue')
      },
      {
        // 搜索页
        path: "/search",
        component: () => import('@/views/SearchView.vue')
      }
    ]
  },

  {
    // 404页面
    path: "/:pathMatch(.*)",
    component: () => import('@/views/404View.vue')
  }

]

const router = createRouter({
  history: createWebHistory(),
  routes
})

router.beforeEach((to, from, next) => {
  const token = localStorage.getItem('token')
  if (to.path == '/home') {
    next()
  } else if (token) {
    next()
  } else if (!token && to.path != '/home') {
    store.commit('changeLoginDialogVisible')
    next('/home')
  }
})

export default router