package club.superk.hueacm.backend.service.user.bot;

import club.superk.hueacm.backend.result.Result;

import java.util.Map;

public interface RemoveBotService {
    public Result<Void> remove(Map<String, String> data);
}
