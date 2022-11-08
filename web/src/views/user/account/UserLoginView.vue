<template>
  <content-field>
    <div class="wrapper">
      <div style="margin: 150px auto; opacity: 0.9; background-color: #ffffff; width: 350px; height: 320px; padding: 20px; border-radius: 10px">
          <div style="margin: 20px 0; text-align: center; font-size: 24px"><b>登录账号</b></div>
          <form @submit.prevent="login">
            <div class="mb-3">
              <label for="username" class="form-label">用户名</label>
              <input v-model="username" type="text" class="form-control" id="username" placeholder="请输入用户名">
            </div>
            <div class="mb-3">
              <label for="password" class="form-label">密码</label>
              <input v-model="password" type="password" class="form-control" id="password" placeholder="请输入密码">
            </div>
            <button type="submit" class="btn btn-primary">登录</button>
          </form>
      </div>
    </div>
  </content-field>
</template>

<script>

import ContentField from "@/components/ContentField";
import { useStore } from 'vuex'
import { ref } from 'vue'
import router from "@/router";
import Swal from "sweetalert2";

export default {
  name: "UserLoginView",
  components: {ContentField},
  setup() {
    const store = useStore();
    let username = ref('');
    let password = ref('');
    let message = ref('');

    const login = () => {
      message.value = "";
      store.dispatch("login", {
        username: username.value,
        password: password.value,
        success() {
          store.dispatch("getInfo", {
            success() {
              router.push({ name: 'home' });
              console.log(store.state.user);
            }
          })
        },
        error() {
          message.value = "用户名或密码错误";
          Swal.fire({
            icon: 'error',
            title: '登录失败',
            text: '用户名或密码错误！'
          });
        },
      })
    }

    return {
      username,
      password,
      message,
      login,
    }
  }
}
</script>

<style scoped>
button {
  width: 100%;
}
.wrapper {
  height: 612px;
  background-image: url("../../../assets/image/loginbg.jpg");
  overflow: hidden;
}
</style>
