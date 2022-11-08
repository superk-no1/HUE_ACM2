package club.superk.hueacm.backend.controller.user.bot;

import club.superk.hueacm.backend.pojo.Bot;
import club.superk.hueacm.backend.result.Result;
import club.superk.hueacm.backend.service.user.bot.QueryBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user/bot")
public class QueryBotController {

    @Autowired
    private QueryBotService queryBotService;

    @GetMapping("/query")
    public Result<List<Bot>> queryBot() {
        return queryBotService.query();
    }

}
