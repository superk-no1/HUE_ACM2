package club.superk.hueacm.backend.controller.user.bot;

import club.superk.hueacm.backend.result.Result;
import club.superk.hueacm.backend.service.user.bot.UpdateBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/user/bot")
public class UpdateBotController {

    @Autowired
    private UpdateBotService updateBotService;

    @PostMapping("/update")
    public Result<Void> updateBot(@RequestParam Map<String, String> data) {
        return updateBotService.update(data);
    }

}
