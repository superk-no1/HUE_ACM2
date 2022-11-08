package club.superk.hueacm.backend.service.impl.pk;

import club.superk.hueacm.backend.consumer.WebSocketServer;
import club.superk.hueacm.backend.consumer.utils.Game;
import club.superk.hueacm.backend.service.pk.ReceiveBotMoveService;
import org.springframework.stereotype.Service;

@Service
public class ReceiveBotMoveServiceImpl implements ReceiveBotMoveService {
    @Override
    public String receiveBotMove(Integer userId, Integer direction) {
        System.out.println("receive bot: " + userId + " move: " + direction + " ");
        if (WebSocketServer.users.get(userId) != null) {
            Game game = WebSocketServer.users.get(userId).game;
            if (game != null) {
                if (game.getPlayerA().getId().equals(userId)) {
                    game.setNextStepA(direction);
                    System.out.println(game.getPlayerA().getId() + " move: " + direction);
                } else if (game.getPlayerB().getId().equals(userId)) {
                    game.setNextStepB(direction);
                    System.out.println(game.getPlayerB().getId() + " move: " + direction);
                }
            }
        }
        return "receive bot move success";
    }
}
