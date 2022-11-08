<template>
  <div class="container">
    <div class="card" style="margin-top: 10px">
      <div class="row">
        <div class="col-9">
          <div class="card">
            <div class="card-header">
              <span style="font-size: 200%">公告栏</span>
            </div>
            <div class="card-body">
              <div style="height: 300px; overflow: auto">
                <table class="table ">
                  <thead>
                  <tr>
                    <th scope="col">标题</th>
                    <th scope="col">发布时间</th>
                    <th scope="col">发布人</th>
                    <th scope="col">操作</th>
                  </tr>
                  </thead>
                  <tbody>
                  <tr v-for="notice in notices" :key="notice.id">
                    <td>
                      <span style="color: red; font-size: 120%">
                        {{ notice.title }}
                      </span>
                    </td>
                    <td>{{ notice.createtime }}</td>
                    <td>
                      <span style="color: blue; font-size: 110%">
                        {{ notice.username }}
                      </span>
                    </td>
                    <td>
                      <button type="button" class="btn btn-outline-success" data-bs-toggle="modal" :data-bs-target="'#view-' + notice.id">查看</button>
                      <!-- 显示消息 -->
                      <div class="modal fade" :id="'view-' + notice.id" tabindex="-1">
                        <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
                          <div class="modal-content">
                            <div class="modal-header">
                              <h5 class="modal-title">{{ notice.title }}</h5>
                              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                              {{ notice.content }}
                            </div>
                            <div class="modal-footer">
                              <button type="button" class="btn btn-primary" data-bs-dismiss="modal">确认</button>
                            </div>
                          </div>
                        </div>
                      </div>
                    </td>

                  </tr>
                  </tbody>
                </table>
              </div>

            </div>
          </div>
        </div>
        <div class="col-3">
          <div class="card" style="margin-top: 50px; margin-right: 20px">
            <img src="https://tse2-mm.cn.bing.net/th/id/OIP-C.ATOycjacHoGRE8HUVfVFaAHaHa?pid=ImgDet&rs=1" style="margin: 8px">
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>

import {useStore} from "vuex";
import {ref} from "vue";
import $ from 'jquery'

export default {
  components: {
  },
  setup() {
    const store = useStore();
    let notices = ref([]);

    const refresh_notice = () => {
      $.ajax({
        url: "http://http://www.superk.club:8081/community/notice/query",
        type: "GET",
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          notices.value = resp.data;
        },
      })
    }
    refresh_notice();

    return {
      notices
    }
  }
}
</script>

<style scoped>
.card {

}
</style>
