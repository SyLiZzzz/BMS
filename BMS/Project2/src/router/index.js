import Vue from 'vue'
import Router from 'vue-router'
import adminlogin from '@/view/adminlogin'
import adminindex from '@/view/manager/adminindex'
import userManager from '@/view/manager/userManager'
import orderManager from '@/view/manager/orderManager'
import shoesManager from '@/view/manager/shoesManager'
import addressManager from '@/view/manager/addressManager'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'index',
      component: adminlogin
    },
    {
      path: '/adminlogin',
      name: 'adminlogin',
      component: adminlogin
    },
    {
      path: '/adminindex',
      name: 'adminindex',
      component: adminindex,
      redirect: {name: 'userManager'},
      children: [
        {
          path: '/userManager',
          name: 'userManager',
          component: userManager
        },
        {
          path: '/orderManager',
          name: 'orderManager',
          component: orderManager
        },
        {
          path: '/shoesManager',
          name: 'shoesManager',
          component: shoesManager
        },
        {
          path: '/addressManager',
          name: 'addressManager',
          component: addressManager
        }
      ]
    }
  ]
})
