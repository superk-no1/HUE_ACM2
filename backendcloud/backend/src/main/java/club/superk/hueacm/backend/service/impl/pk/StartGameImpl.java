package club.superk.hueacm.backend.service.impl.pk;

import club.superk.hueacm.backend.consumer.WebSocketServer;
import club.superk.hueacm.backend.service.pk.StartGameService;
import org.springframework.stereotype.Service;

@Service
public class StartGameImpl implements StartGameService {
    @Override
    public String startGame(Integer aId, Integer aBotId, Integer bId, Integer bBotId) {
        System.out.println("start game: " + aId + " " + bId);
        WebSocketServer.startGame(aId, aBotId, bId, bBotId);
        return "start game success";
    }
}
