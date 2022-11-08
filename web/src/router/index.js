import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import PkIndexView from "@/views/pk/PkIndexView";
import RecordIndexView from "@/views/record/RecordIndexView";
import NotFound from "@/views/error/NotFound";
import RankIndexView from "@/views/ranklist/RankIndexView";
import UserLoginView from "@/views/user/account/UserLoginView";
import UserRegisterView from "@/views/user/account/UserRegisterView";
import store from '@/store/index'
import Swal from 'sweetalert2'
import MyBotIndexView from "@/views/user/bot/MyBotIndexView";
import HonorIndexView from "@/views/honor/HonorIndexView";
import CommunityIndexView from "@/views/community/CommunityIndexView";
import ArticleIndexView from "@/views/community/ArticleIndexView";
import ArticleEditorView from "@/views/community/ArticleEditorView";
import UserInfoView from "@/views/user/account/UserInfoView";
import RecordContentView from "@/views/record/RecordContentView";

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView,
    meta: {
      requestAuth: false,
      isOpen: true
    }
  },
  {
    path: '/pk',
    name: 'pkIndex',
    component: PkIndexView,
    meta: {
      requestAuth: true,
      isOpen: false
    }
  },
  {
    path: '/record',
    name: 'recordIndex',
    component: RecordIndexView,
    meta: {
      requestAuth: true,
      isOpen: true
    }
  },
  {
    path: '/record/:recordId',
    name: 'recordContent',
    component: RecordContentView,
    meta: {
      requestAuth: true,
      isOpen: true
    }
  },
  {
    path: '/ranklist',
    name: 'rankListIndex',
    component: RankIndexView,
    meta: {
      requestAuth: true,
      isOpen: true
    }
  },
  {
    path: '/userInfo',
    name: 'userInfoIndex',
    component: UserInfoView,
    meta: {
      requestAuth: true,
      isOpen: true
    }
  },
  {
    path: '/user/bot',
    name: 'MyBotIndex',
    component: MyBotIndexView,
    meta: {
      requestAuth: true,
      isOpen: true
    }
  },
  {
    path: '/user/login',
    name: 'userLogin',
    component: UserLoginView,
    meta: {
      requestAuth: false,
      isOpen: true
    }
  },
  {
    path: '/user/register',
    name: 'userRegister',
    component: UserRegisterView,
    meta: {
      requestAuth: false,
      isOpen: true
    }
  },
  {
    path: '/honor',
    name: 'honorIndex',
    component: HonorIndexView,
    meta: {
      requestAuth: false,
      isOpen: true
    }
  },
  {
    path: '/community',
    name: 'communityIndex',
    component: CommunityIndexView,
    meta: {
      requestAuth: false,
      isOpen: true
    }
  },
  {
    path: '/community/article',
    name: 'articleIndex',
    component: ArticleIndexView,
    meta: {
      requestAuth: false,
      isOpen: true
    }
  },
  {
    path: '/community/articleEditor',
    name: 'articleEditorIndex',
    component: ArticleEditorView,
    meta: {
      requestAuth: false,
      isOpen: true
    }
  },
  {
    path: '/404',
    name: 'notFound',
    component: NotFound,
    meta: {
      requestAuth: false,
      isOpen: true
    }
  },
  {
    path: "/:catchAll(.*)",
    redirect: "/404",
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

router.beforeEach((to, from, next) => {

  let flag = 1;
  const jwt_token = localStorage.getItem("jwt_token");

  if (jwt_token) {
    store.commit("updateToken", jwt_token);
    store.dispatch("getInfo", {
      success() {
      },
      error() {
        Swal.fire({
          icon: 'error',
          title: '禁止访问',
          text: 'token无效，请重新登录！'
        });
        router.push({ name: 'userLogin' });
      }
    })
  } else {
    flag = 2;
  }

  if (!to.meta.isOpen) {
    Swal.fire({
      icon: 'error',
      title: '禁止访问',
      text: '因服务器性能限制，请前往友链体验'
    });
    next({name: "userLogin"});
  }

  if (to.meta.requestAuth && !store.state.user.is_login) {
    if (flag === 1) {
      next();
    } else {
      Swal.fire({
        icon: 'error',
        title: '禁止访问',
        text: '前先进行登录'
      });
      next({name: "userLogin"});
    }
  } else {
    next();
  }
})

export default router
