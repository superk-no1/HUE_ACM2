<template>
  <div class="container" style="margin-top: 18px; border: 1px solid #ccc">
    <div style="height: 70px; background-color: #dedbdb" class="row">
      <div class="col" style="margin-top: 10px">
        <div class="input-group input-group-lg">
          <span class="input-group-text" id="inputGroup-sizing-lg">标题</span>
          <input v-model="articleAdd.title" type="text" class="form-control" placeholder="请输入文章标题..." aria-label="Sizing example input" aria-describedby="inputGroup-sizing-lg">
        </div>
      </div>
      <div class="col" style="margin-top: 10px;">
        <button type="button" class="btn btn-primary btn-lg" style="margin-left: 420px" @click="add_article" >发布文章</button>
      </div>
    </div>
    <div style="margin-top: 5px">
      <div style="border: 1px solid #ccc">
        <Toolbar
            style="border-bottom: 1px solid #ccc"
            :editor="editorRef"
            :defaultConfig="toolbarConfig"
            :mode="mode"
        />
        <Editor
            style="height: 500px; overflow-y: hidden;"
            v-model="articleAdd.content"
            :defaultConfig="editorConfig"
            :mode="mode"
            @onCreated="handleCreated"
        />
      </div>
    </div>
  </div>
</template>

<script>
import '@wangeditor/editor/dist/css/style.css' // 引入 css

import {onBeforeUnmount, reactive, shallowRef,} from 'vue'
import { Editor, Toolbar } from '@wangeditor/editor-for-vue'
import $ from "jquery";
import Swal from "sweetalert2";
import {useStore} from "vuex";
import router from "@/router";

export default {
  components: { Editor, Toolbar },
  setup() {
    // 编辑器实例，必须用 shallowRef
    const editorRef = shallowRef()
    const store = useStore()

    const toolbarConfig = {}
    const editorConfig = { placeholder: '请输入内容...' }

    const articleAdd = reactive({
      title: "",
      content: ""
    })

    // 组件销毁时，也及时销毁编辑器
    onBeforeUnmount(() => {
      const editor = editorRef.value
      if (editor == null) return
      editor.destroy()
    })

    const handleCreated = (editor) => {
      editorRef.value = editor // 记录 editor 实例，重要！
    }

    const add_article = () => {
      $.ajax({
        url: "http://http://www.superk.club:8081/community/article/add",
        type: "POST",
        data: {
          title: articleAdd.title,
          content: articleAdd.content
        },
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          if (resp.code === 200) {
            articleAdd.title = "";
            articleAdd.content = "";
            Swal.fire({
              icon: 'success',
              title: '发布成功',
            });
            router.push({ name: 'communityIndex' });
          } else {
            Swal.fire({
              icon: 'error',
              title: '创建失败',
              text: resp.message
            });
          }
        }
      })
    }

    return {
      editorRef,
      mode: 'default', // 或 'simple'
      toolbarConfig,
      editorConfig,
      articleAdd,
      handleCreated,
      add_article
    };
  }
}
</script>

<style scoped>

</style>
