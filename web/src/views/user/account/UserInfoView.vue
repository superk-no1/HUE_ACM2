<template>
  <div class="container">
    <div class="row">
      <div class="col-9">
        <div class="card text-center" style="margin-top: 20px; text-align: left">
          <div class="card-header" style="text-align: left">
            修改信息
          </div>
          <div class="card-body" style="margin-left: 85px; margin-right: 70px">
            <form @submit.prevent="update_user">
              <div class="mb-3">
                <label for="InputUsername" class="form-label">用户名</label>
                <input v-model="userInfo.username" class="form-control" id="InputUsername" aria-describedby="emailHelp">
                <div id="emailHelp" class="form-text">用户名长度不能超过20个字符</div>
              </div>
              <div class="mb-3">
                <label for="InputPassword1" class="form-label">密码</label>
                <input v-model="userInfo.password" type="password" class="form-control" id="InputPassword1">
              </div>
              <div class="mb-3">
                <label for="InputPassword2" class="form-label">确认密码</label>
                <input v-model="userInfo.confirmed_password" type="password" class="form-control" id="InputPassword2">
              </div>
              <div class="mb-3">
                <label for="email" class="form-label">输入邮箱</label>
                <input v-model="userInfo.email" type="email" class="form-control" id="email" placeholder="请输入邮箱">
              </div>
              <div style="text-align: center; margin-top: 45px" class="d-grid gap-2 mx-auto">
                <button type="submit" class="btn btn-primary btn-lg" style="text-align: center">保存信息</button>
              </div>
            </form>
          </div>
        </div>
      </div>
      <div class="col-3">
        <div class="card" style="margin-top: 20px">
          <div class="card-body">
            <img :src="$store.state.user.avatar" alt="" style="width: 100%">
          </div>
          <div style="margin-bottom: 10px">
            <a-upload
                v-model:file-list="fileList"
                name="file"
                action="http://http://www.superk.club:8081/user/account/update/avatar"
                :headers="headers"
                @change="handleChange"
            >
              <a-button>
                <upload-outlined></upload-outlined>
                点击这里更换头像
              </a-button>
            </a-upload>
          </div>
        </div>
      </div>
    </div>

  </div>
</template>

<script>
import {useStore} from "vuex";
import {reactive, ref} from "vue";
import $ from "jquery";
import Swal from "sweetalert2";
import { message } from 'ant-design-vue';
import { UploadOutlined } from '@ant-design/icons-vue';
import router from "@/router";

export default {
  name: "UserInfoView",
  components: {
    UploadOutlined,
  },
  setup() {
    const store = useStore()
    const userInfo = reactive({
      username: "",
      password: "",
      confirmed_password: "",
      email: ""
    })

    const handleChange = info => {
      if (info.file.status !== 'uploading') {
        console.log(info.file, info.fileList);
      }

      if (info.file.status === 'done') {
        message.success(`${info.file.name} 更换成功`);
        router.push({name: 'home'});
      } else if (info.file.status === 'error') {
        message.error(`${info.file.name} 更换失败`);
      }
    };

    const fileList = ref([]);

    const update_user = () => {
      $.ajax({
        url: "http://http://www.superk.club:8081/user/account/update",
        type: "POST",
        data: {
          user_id: store.state.user.id,
          username: userInfo.username,
          password: userInfo.password,
          confirmed_password: userInfo.confirmed_password,
          email: userInfo.email
        },
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          if (resp.code === 200) {
            userInfo.username = "";
            userInfo.password = "";
            userInfo.confirmed_password = "";
            userInfo.email = ""
            Swal.fire({
              icon: 'success',
              title: '修改成功',
            });
          } else {
            Swal.fire({
              icon: 'error',
              title: '修改失败',
              text: resp.message
            });
          }
        }
      })
    }

    return {
      fileList,
      headers: {
        Authorization: "Bearer " + store.state.user.token,
      },
      handleChange,
      update_user,
      userInfo
    }
  }
}
</script>

<style scoped>

</style>
