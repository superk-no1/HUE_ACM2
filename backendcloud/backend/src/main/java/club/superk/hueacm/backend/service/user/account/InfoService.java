package club.superk.hueacm.backend.service.user.account;

import club.superk.hueacm.backend.pojo.User;
import club.superk.hueacm.backend.result.Result;

public interface InfoService {

    public Result<User> getInfo();

    public Result<User> getUserById(Integer userId);

}
