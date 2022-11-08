<template>
  <div class="container">
    <div style="color: #666">
      <div style="margin: 10px 0">
        <input style="width: 300px" placeholder="请输入关键字">
        <button style="margin-left: 5px">搜索</button>
        <button @click="$router.push('/community/articleEditor')" style="margin-left: 150px" type="button" class="btn btn-primary float-end">分享文章</button>
      </div>
    </div>

    <div class="content">
      <div style="margin: 10px 0">
        <div style="padding: 10px 0; border-bottom: 2px dashed #ccc" v-for="item in tableData" :key="item.id">
          <div @click="$router.push('/community/article?id=' + item.id)" style="padding: 10px; font-size: 20px; color: #3f5FEB; cursor: pointer">
            {{ item.title }}
          </div>
          <div style="font-size: 14px; margin-top: 10px">
            <span style="margin-right: 5px" class="badge rounded-pill bg-warning text-dark">作者: {{ item.author }}</span>
            <span class="badge rounded-pill bg-warning text-dark">创建时间: {{ item.createTime }}</span>
          </div>
        </div>
      </div>
    </div>
  </div>

</template>

<script>
import {ref} from "vue";
import $ from 'jquery'
import {useStore} from "vuex";

export default {
  name: "CommunityIndexView",
  components: {},
  setup() {
    const store = useStore();
    let tableData = ref([]);

    const refresh_tableData = () => {
      $.ajax({
        url: "http://localhost:8081/community/article/all",
        type: "GET",
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          tableData.value = resp.data
        }
      })
    }
    refresh_tableData();

    return {
      tableData,
    }
  }
}
</script>

<style scoped>
.content {
  background-color: rgba(218, 247, 166, 0.5);
}
</style>
