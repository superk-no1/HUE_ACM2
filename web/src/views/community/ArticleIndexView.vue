<template>
  <div class="container">
    <div class="card" style="margin-top: 35px">
      <div class="card-header">
        <span style="font-size: 36px; font-weight:bold; ">
          {{ articleData.title }}
        </span>
        <div style="display: flex">
          <div style="width: 100px; text-align: center">
            <img :src="articleData.avatar" style="width: 50px; height: 50px; border-radius: 50%">
          </div>
          <div style="flex: 1; font-size: 14px; padding: 5px 0; line-height: 25px; text-align: left">
            <span style="font-size: 18px; font-weight:bold">{{ articleData.author }} </span>
            <div style="flex: 1">
              <span style="font-size: 14px">{{ articleData.createTime }} </span>
            </div>
          </div>

        </div>
      </div>
      <div class="card-body" style="text-align: left">
        <div v-html="articleData.content"></div>

        <!--        {{ articleData.content }}-->
      </div>
    </div>

    <div style="margin: 30px 0; text-align: left">
      <div style="margin: 10px 0">
        <div style="border-bottom: 2px solid orangered; padding: 10px 0; font-size: 25px">评论</div>
        <div style="padding: 10px 0">
          <textarea v-model="commentAdd.content" class="form-control" rows="3" placeholder="新的风暴已经出现，你的秒评何时再现..."></textarea>
        </div>
        <div style="padding: 10px; text-align: right">
          <button type="button" class="btn btn-primary" @click="add_comment">发布</button>
        </div>
      </div>

      <!-- 评论列表 -->
      <div style="background-color: white">
        <div v-for="item in commentData" :key="item.id" style="border-bottom: 1px solid #ccc; padding: 10px 0;">
          <div style="display: flex">
            <div style="width: 100px; text-align: center">
              <img :src="item.avatar" style="width: 50px; height: 50px; border-radius: 50%">
            </div>
            <div style="flex: 1; font-size: 16px; padding: 5px 0; line-height: 25px">
              <span style="font-size: 18px; font-weight:bold">{{ item.username }}: </span>
              <span style="font-size: 16px">{{ item.content }}</span>

              <div style="display: flex">
                <div style="width: 200px">
                  <span style="margin-top: 8px; font-size: 8px">{{ item.createTime }}</span>
                </div>
                <div style="text-align: right; flex: 1">
                  <span type="button" style="margin-right: 12px; color: #3f5FEB" data-bs-toggle="modal" :data-bs-target="'#reply-' + item.id">回复</span>

                  <!-- 回复Model -->
                  <div class="modal fade" :id="'reply-' + item.id" tabindex="-1">
                    <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
                      <div class="modal-content">
                        <div class="modal-header">
                          <h5 class="modal-title">回复 {{ item.username }}:</h5>
                          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                          <div class="form-floating">
                            <textarea v-model="commentAdd.content" class="form-control" placeholder="Leave a comment here" id="floatingTextarea"></textarea>
                            <label for="floatingTextarea">在此输入回复...</label>
                          </div>
                        </div>
                        <div class="modal-footer">
                          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
                          <button type="button" class="btn btn-primary" data-bs-dismiss="modal" @click="add_reply(item)">确认</button>
                        </div>
                      </div>
                    </div>
                  </div>

                  <span type="button" v-if="item.username == $store.state.user.username" @click="remove_comment(item)" style="margin-right: 10px; color: red">删除</span>
                </div>
              </div>
            </div>
          </div>

          <div v-if="item.children.length" style="padding-left: 100px; padding-right: 30px">
            <div v-for="subItem in item.children" :key="subItem.id" style="background-color: rgb(235,240,241); padding: 5px 20px">
              <!-- 回复列表 -->
              <div style="display: flex">
                <div style="width: 70px; text-align: center; margin-top: 5px">
                  <img :src="subItem.avatar" style="width: 35px; height: 35px; border-radius: 50%">
                </div>
                <div style="flex: 1; font-size: 14px; padding: 5px 0; line-height: 25px">
                  <b>{{ subItem.username }}:</b>
                  <span style="font-style: italic; color: #46bad2">@{{ subItem.pname }}: </span>
                  <span>{{ subItem.content }}</span>

                  <div style="display: flex; line-height: 20px; margin-top: 5px">
                    <div style="width: 200px">
                      <span style="margin-top: 8px; font-size: 8px">{{ subItem.createTime }}</span>
                    </div>
                    <div style="text-align: right; flex: 1">
                      <span type="button" style="margin-right: 12px; color: #3f5FEB" data-bs-toggle="modal" :data-bs-target="'#reply-' + subItem.id">回复</span>
                      <!-- 回复Model -->
                      <div class="modal fade" :id="'reply-' + subItem.id" tabindex="-1">
                        <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
                          <div class="modal-content">
                            <div class="modal-header">
                              <h5 class="modal-title">回复 {{ subItem.username }}:</h5>
                              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                              <div class="form-floating">
                                <textarea v-model="commentAdd.content" class="form-control" placeholder="Leave a comment here" id="floatingTextarea2"></textarea>
                                <label for="floatingTextarea2">在此输入回复...</label>
                              </div>
                            </div>
                            <div class="modal-footer">
                              <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
                              <button type="button" class="btn btn-primary" data-bs-dismiss="modal" @click="add_reply(subItem)">确认</button>
                            </div>
                          </div>
                        </div>
                      </div>
                      <span type="button" v-if="subItem.username == $store.state.user.username" @click="remove_comment(subItem)" style="margin-right: 10px; color: red">删除</span>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

</template>

<script>
import {reactive, ref} from "vue";
import $ from "jquery";
import {useStore} from "vuex";
import {useRoute} from "vue-router";
import Swal from "sweetalert2";
import { Modal } from 'bootstrap/dist/js/bootstrap'

export default {
  name: "ArticleIndexView",
  setup() {
    const store = useStore();
    const router = useRoute();
    const commentAdd = reactive({
      content: "",
    })
    const id = router.query.id;

    let articleData = ref();
    let commentData = ref();


    // const richText = ;

    const refresh_articleData = () => {
      $.ajax({
        url: "http://http://www.superk.club:8081/community/article/" + id,
        type: "GET",
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          articleData.value = resp.data
        }
      })
    }
    refresh_articleData();

    const refresh_commentData = () => {
      $.ajax({
        url: "http://http://www.superk.club:8081/comment/tree/" + id,
        type: "GET",
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          commentData.value = resp.data
        }
      })
    }
    refresh_commentData();

    const add_comment = () => {
      $.ajax({
        url: "http://http://www.superk.club:8081/comment/add",
        type: "POST",
        data: {
          content: commentAdd.content,
          article_id: id,
          pid: 0
        },
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          if (resp.code === 200) {
            commentAdd.content = "";
            Swal.fire({
              icon: 'success',
              title: '评论成功',
            });
            refresh_commentData();
          } else {
            Swal.fire({
              icon: 'error',
              title: '评论失败',
              text: resp.message
            });
          }
        }
      })
    }

    const add_reply = (comment) => {
      $.ajax({
        url: "http://http://www.superk.club:8081/comment/add",
        type: "POST",
        data: {
          content: commentAdd.content,
          article_id: id,
          pid: comment.id
        },
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          if (resp.code === 200) {
            commentAdd.content = "";
            Swal.fire({
              icon: 'success',
              title: '回复成功',
            });
            Modal.getInstance("#reply-" + comment.id).hide();
            refresh_commentData();
          } else {
            Swal.fire({
              icon: 'error',
              title: '回复失败',
              text: resp.message
            });
          }
        }
      })
    }

    const remove_comment = (comment) => {
      $.ajax({
        url: "http://http://www.superk.club:8081/comment/remove",
        type: "POST",
        data: {
          commentId: comment.id
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
          } else {
            Swal.fire({
              icon: 'error',
              title: '删除失败',
              text: resp.message
            });
          }
          // Modal.getInstance("#removeComment").hide();
          refresh_commentData();
        }
      })
    }

    return {
      articleData,
      commentData,
      commentAdd,
      refresh_commentData,
      remove_comment,
      add_comment,
      add_reply,
      refresh_articleData
    }
  }
}
</script>

<style scoped>
.card-header {
  background-color: white;
}
</style>
