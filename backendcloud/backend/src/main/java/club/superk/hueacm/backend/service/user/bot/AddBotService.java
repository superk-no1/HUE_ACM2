package club.superk.hueacm.backend.service.user.bot;

import club.superk.hueacm.backend.result.Result;

import java.util.Map;

public interface AddBotService {
    public Result<Void> add(Map<String, String> data);

}
