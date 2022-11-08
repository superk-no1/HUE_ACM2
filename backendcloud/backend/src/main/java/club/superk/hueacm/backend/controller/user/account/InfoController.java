package club.superk.hueacm.backend.controller.user.account;

import club.superk.hueacm.backend.mapper.UserMapper;
import club.superk.hueacm.backend.pojo.User;
import club.superk.hueacm.backend.result.Result;
import club.superk.hueacm.backend.service.user.account.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/user/account")
public class InfoController {

    @Autowired
    private InfoService infoService;

    @GetMapping("/info")
    public Result<User> getInfo() {
        return infoService.getInfo();
    }

    @GetMapping("/getUser")
    public Result<User> getUserById(@RequestParam("user_id") Integer userId) {
        return infoService.getUserById(userId);
    }

}
