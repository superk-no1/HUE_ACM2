package club.superk.hueacm.backend.controller.user.account;

import club.superk.hueacm.backend.result.Result;
import club.superk.hueacm.backend.service.user.account.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/user/account")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/token")
    public Result<String> getToken(@RequestParam Map<String, String> map) {
        String username = map.get("username");
        String password = map.get("password");
        return loginService.getToken(username, password);
    }

}
