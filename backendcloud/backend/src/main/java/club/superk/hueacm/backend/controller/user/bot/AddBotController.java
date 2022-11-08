package club.superk.hueacm.backend.controller.user.bot;

import club.superk.hueacm.backend.result.Result;
import club.superk.hueacm.backend.service.user.bot.AddBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("user/bot")
public class AddBotController {

    @Autowired
    private AddBotService addBotService;

    @PostMapping("/add")
    public Result<Void> addBot(@RequestParam Map<String, String> data) {
        return addBotService.add(data);
    }
}
