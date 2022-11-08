<template>
  <content-field>
    <div class="wrapper">
      <div style="margin: 150px auto; opacity: 0.9; background-color: #ffffff; width: 350px; height: 330px; padding: 20px; border-radius: 10px">
        <div style="margin: 20px 0; text-align: center; font-size: 24px"><b>注册账号</b></div>
        <form @submit.prevent="register">
          <div class="mb-3">
            <input v-model="username" type="text" class="form-control" id="username" placeholder="请输入用户名">
          </div>
          <div class="mb-3">
            <input v-model="password" type="password" class="form-control" id="password" placeholder="请输入密码">
          </div>
          <div class="mb-3">
            <input v-model="confirmedPassword" type="password" class="form-control" id="confirmedPassword" placeholder="请再次输入密码">
          </div>
          <button type="submit" class="btn btn-primary">注册</button>
        </form>
      </div>
    </div>
  </content-field>
</template>

<script>
import ContentField from "@/components/ContentField";
import {ref} from "vue";
import $ from 'jquery'
import router from "@/router";
import Swal from "sweetalert2";
export default {
  name: "UserRegisterView",
  components: {ContentField},
  setup() {
    let username = ref('');
    let password = ref('');
    let confirmedPassword = ref('');

    const register = () => {
      $.ajax({
        url: "http://http://www.superk.club:8081/user/account/register",
        type: "post",
        data: {
          username: username.value,
          password: password.value,
          confirmedPassword: confirmedPassword.value,
        },
        success(resp) {
          if (resp.code === 200) {
            Swal.fire({
              icon: 'success',
              title: '注册成功',
            });
            router.push({name: "userLogin"})
          } else {
            Swal.fire({
              icon: 'error',
              title: '注册失败',
              text: resp.message
            });
          }
        },
      });
    }

    return {
      username,
      password,
      confirmedPassword,
      register
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
