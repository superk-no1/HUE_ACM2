package club.superk.hueacm.backend.service.user.bot;

import club.superk.hueacm.backend.result.Result;

import java.util.Map;

public interface UpdateBotService {
    public Result<Void> update(Map<String, String> data);
}
