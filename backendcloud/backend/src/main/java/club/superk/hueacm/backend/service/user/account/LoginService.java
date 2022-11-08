package club.superk.hueacm.backend.service.user.account;

import club.superk.hueacm.backend.result.Result;

public interface LoginService {

    public Result<String> getToken(String username, String password);

}
