package club.superk.hueacm.backend.service.user.bot;

import club.superk.hueacm.backend.pojo.Bot;
import club.superk.hueacm.backend.result.Result;

import java.util.List;

public interface QueryBotService {
    public Result<List<Bot>> query();
}
