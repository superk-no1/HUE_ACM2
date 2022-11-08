<template>
  <div class="container">
    <div class="row">
      <div class="col-9">
        <div class="card" style="margin-top: 20px">
          <div class="card-header">
            <span style="color: #bd5555;font-size: 150%">我的Bot</span>
            <button type="button" class="btn btn-success float-end" data-bs-toggle="modal" data-bs-target="#createBot">
              创建Bot
            </button>

            <!-- Modal -->
            <div class="modal fade" id="createBot" tabindex="-1">
              <div class="modal-dialog modal-xl">
                <div class="modal-content">
                  <div class="modal-header">
                    <h5 class="modal-title">创建Bot</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                  </div>
                  <div class="modal-body">
                    <div class="mb-3">
                      <label for="createBot-title" class="form-label">名称</label>
                      <input v-model="botCreate.title" type="email" class="form-control" id="createBot-title" placeholder="请输入Bot名称">
                    </div>
                    <div class="mb-3">
                      <label for="createBot-description" class="form-label">简介</label>
                      <textarea v-model="botCreate.description" class="form-control" id="createBot-description" placeholder="请输入Bot简介" rows="3"></textarea>
                    </div>
                    <div class="mb-3">
                      <label for="createBot-content" class="form-label">简介</label>
                      <textarea v-model="botCreate.content" class="form-control" id="createBot-content" placeholder="请输入Bot代码" rows="7"></textarea>
                    </div>
                  </div>
                  <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" @click="create_bot">创建</button>
                  </div>
                </div>
              </div>
            </div>

          </div>
          <div class="card-body">
            <table class="table table-striped table-hover">
              <thead>
                <tr>
                  <th>Bot名称</th>
                  <th>上次修改时间</th>
                  <th>操作</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="bot in bots" :key="bot.id">
                  <td>{{ bot.title }}</td>
                  <td>{{ bot.modifytime }}</td>
                  <td>
                    <button type="button" class="btn btn-primary" data-bs-toggle="modal" :data-bs-target="'#update-bot-modal-' + bot.id">修改</button>
                    <button type="button" class="btn btn-danger" @click="remove_bot(bot)" style="margin-left: 15px">删除</button>

                    <div class="modal fade" :id="'update-bot-modal-' + bot.id" tabindex="-1">
                      <div class="modal-dialog modal-xl">
                        <div class="modal-content">
                          <div class="modal-header">
                            <h5 class="modal-title">修改Bot</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                          </div>
                          <div class="modal-body">
                            <div class="mb-3">
                              <label for="add-bot-title" class="form-label">名称</label>
                              <input v-model="bot.title" type="text" class="form-control" id="add-bot-title" placeholder="请输入Bot名称">
                            </div>
                            <div class="mb-3">
                              <label for="add-bot-description" class="form-label">简介</label>
                              <textarea v-model="bot.description" class="form-control" id="add-bot-description" rows="3" placeholder="请输入Bot简介"></textarea>
                            </div>
                            <div class="mb-3">
                              <label for="add-bot-code" class="form-label">代码</label>
                              <textarea v-model="bot.content" class="form-control" id="add-bot-code" rows="3" placeholder="请输入Bot代码"></textarea>
                            </div>
                          </div>
                          <div class="modal-footer">
                            <button type="button" class="btn btn-primary" @click="update_bot(bot)">保存</button>
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
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
      <div class="col-3">
        <div class="card" style="margin-top: 20px">
          <div class="card-body">
            <img :src="$store.state.user.avatar" alt="" style="width: 100%">
          </div>
          <div>
            <span style="font-size: 200%">{{ $store.state.user.username }}</span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { ref, reactive } from 'vue'
import $ from 'jquery'
import { useStore } from 'vuex'
import Swal from "sweetalert2";
import { Modal } from 'bootstrap/dist/js/bootstrap'

export default {
  name: "MyBotIndexView",
  components: {},
  setup() {
    const store = useStore();
    let bots = ref([]);

    const botCreate = reactive({
      title: "",
      description: "",
      content: "",
    })

    const refresh_bots = () => {
      $.ajax({
        url: "http://http://www.superk.club:8081/user/bot/query",
        type: "GET",
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          bots.value = resp.data;
        },
      })
    }
    refresh_bots();

    const create_bot = () => {
      $.ajax({
        url: "http://http://www.superk.club:8081/user/bot/add",
        type: "POST",
        data: {
          title: botCreate.title,
          description: botCreate.description,
          content: botCreate.content,
        },
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          if (resp.code === 200) {
            botCreate.title = "";
            botCreate.description = "";
            botCreate.content = "";
            Swal.fire({
              icon: 'success',
              title: '创建成功',
            });
            Modal.getInstance("#createBot").hide();
            refresh_bots();
          } else {
            Swal.fire({
              icon: 'error',
              title: '创建失败',
              text: resp.message
            });
          }
        },
      })
    }

    const update_bot = (bot) => {
      $.ajax({
        url: "http://http://www.superk.club:8081/user/bot/update",
        type: "POST",
        data: {
          bot_id: bot.id,
          title: bot.title,
          description: bot.description,
          content: bot.content,
        },
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          if (resp.code === 200) {
            Swal.fire({
              icon: 'success',
              title: '修改成功',
            });
            Modal.getInstance("#update-bot-modal-" + bot.id).hide();
            refresh_bots();
          } else {
            Swal.fire({
              icon: 'error',
              title: '修改失败',
              text: resp.message
            });
          }
        },
      })
    }

    const remove_bot = (bot) => {
      $.ajax({
        url: "http://http://www.superk.club:8081/user/bot/remove",
        type: "POST",
        data: {
          bot_id: bot.id,
        },
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          if (resp.code === 200) {
            Swal.fire({
              icon: 'success',
              title: '删除成功',
            });
            refresh_bots();
          } else {
            Swal.fire({
              icon: 'error',
              title: '删除失败',
              text: resp.message
            });
          }
        }
      })
    }

    return {
      bots,
      botCreate,
      create_bot,
      remove_bot,
      update_bot,
    }
  }
}
</script>

<style scoped>

</style>
