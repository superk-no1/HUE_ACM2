package club.superk.hueacm.backend.controller.user.account;

import club.superk.hueacm.backend.result.Result;
import club.superk.hueacm.backend.service.user.account.UpdateUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.util.Map;

@RestController
@RequestMapping("/user/account")
public class UpdateController {

    @Autowired
    private UpdateUserService updateUserService;

    @PostMapping("/update")
    public Result<Void> updateUser(@RequestParam Map<String, String> data) {
        return updateUserService.updateUserInfo(data);
    }

    @PostMapping("/update/avatar")
    public Result<String> updataUserAvatar(@RequestParam("file") MultipartFile file) throws FileNotFoundException {
        return updateUserService.updateUserAvatar(file);
    }

}
