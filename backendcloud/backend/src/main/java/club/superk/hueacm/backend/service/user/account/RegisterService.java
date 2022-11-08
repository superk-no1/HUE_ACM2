package club.superk.hueacm.backend.service.user.account;

import club.superk.hueacm.backend.result.Result;

public interface RegisterService {

    public Result<Void> register(String username, String password, String confirmedPassword);

}
