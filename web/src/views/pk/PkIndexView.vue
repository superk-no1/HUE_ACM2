<template>
  <play-ground v-if="$store.state.pk.status === 'playing'">

  </play-ground>
  <match-ground v-if="$store.state.pk.status === 'matching'">

  </match-ground>
  <result-board v-if="$store.state.pk.loser != 'none'"></result-board>

  <div class="user-color" v-if="$store.state.pk.status === 'playing' && parseInt($store.state.user.id) === parseInt($store.state.pk.a_id)">你控制蓝蛇！</div>
  <div class="user-color" v-if="$store.state.pk.status === 'playing' && parseInt($store.state.user.id) === parseInt($store.state.pk.b_id)">你控制红蛇！</div>

</template>

<script>
import PlayGround from "@/components/PlayGround";
import {useStore} from "vuex";
import {onMounted, onUnmounted} from "vue";
import MatchGround from "@/components/MatchGround";
import ResultBoard from "@/components/ResultBoard";
export default {
  name: "PkIndexView",
  components: {ResultBoard, MatchGround, PlayGround},
  setup() {
    const store = useStore();
    const socketUrl = `ws://http://www.superk.club:8081/websocket/${store.state.user.token}/`;

    store.commit("updateLoser", "none");
    store.commit("updateIsRecord", false);

    let socket = null;

   // 当前组件被挂载时触发
    onMounted(() => {
      store.commit("updateOpponent", {
        username: "旗鼓相当的对手",
        photo: "https://cdn.acwing.com/media/article/image/2022/08/09/1_1db2488f17-anonymous.png"
      })
      socket = new WebSocket(socketUrl);

      socket.onopen = () => {
        console.log("vue connected!");
        store.commit("updateSocket", socket);
      }

      socket.onmessage = msg => {
        const data = JSON.parse(msg.data);
        if (data.event === "start-matching") {
          store.commit("updateOpponent", {
            username: data.opponent_username,
            photo: data.opponent_photo,
          });
          setTimeout(() => {
            store.commit("updateStatus", "playing");
          }, 200);
          store.commit("updateGame", data.game);
        } else if (data.event === "move") {
          console.log(data)
          const game = store.state.pk.gameObject;
          const [snake0, snake1] = game.snakes;
          snake0.set_direction(data.a_direction);
          snake1.set_direction(data.b_direction);
        } else if (data.event === "result") {
          console.log(data);
          const game = store.state.pk.gameObject;
          const [snake0, snake1] = game.snakes;

          if (data.loser === "all" || data.loser === "A") {
            snake0.status = "die";
          }
          if (data.loser === "all" || data.loser === "B") {
            snake1.status = "die";
          }
          store.commit("updateLoser", data.loser);
        }
      }

      socket.onclose = () => {
        console.log("vue disconnected!");
      }
    })

    // 当前组件被卸载时触发
    onUnmounted(() => {
      socket.close();
    })
  }
}
</script>

<style scoped>
div.user-color {
  text-align: center;
  color: darkslateblue;
  font-size: 30px;
  font-weight: 600;
}

</style>
