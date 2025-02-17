import {createRouter, createWebHistory} from 'vue-router';

//라우터는 클라이언트의 요청 경로에 따라 해당하는 컴포넌트를 불러와 페이지를 구성할 수 있다.
//URL 변경 시 DOM을 새로 갱신하는 것이 아니라 미리 컴포넌트를 가지고 있다가 변경된 요소영역만 갱신한다.(SPA 언어의 큰 특징)
//따라서 유연하게 페이지 전환이 가능하다.

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: () => import('../views/Home.vue')
    },
    {
      path: '/join',
      name: 'join',
      component: () => import('../views/Join.vue')
    },
    {
      path: '/login',
      name: 'login',
      component: () => import('../views/Login.vue')
    },
    {
      path: '/cart',
      name: 'cart',
      component: () => import('../views/Cart.vue')
    },
    {
      path: '/order',
      name: 'orderForm',
      component: () => import('../views/OrderForm.vue')
    },
    {
      path: '/orders',
      name: 'orders',
      component: () => import('../views/Orders.vue')
    },
    {
      path: '/orders/:id',
      name: 'orderDetail',
      component: () => import('../views/OrderDetail.vue')
    },
  ]
});

export default router;