package club.superk.hueacm.coderunningsystem.service.impl;

import club.superk.hueacm.coderunningsystem.service.BotRunningService;
import club.superk.hueacm.coderunningsystem.service.impl.util.BotPool;
import org.springframework.stereotype.Service;

@Service
public class BotRunningServiceImpl implements BotRunningService {
    public final static BotPool botPool = new BotPool();

    @Override
    public String addBot(Integer userId, String botCode, String input) {
        System.out.println("add botCode: " + userId + "bot_code" + " " + input);
        botPool.addBot(userId, botCode, input);
        return "add bot success";
    }
}
