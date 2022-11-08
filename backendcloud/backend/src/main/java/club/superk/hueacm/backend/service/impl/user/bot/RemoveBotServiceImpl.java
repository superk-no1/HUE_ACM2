package club.superk.hueacm.backend.service.impl.user.bot;

import club.superk.hueacm.backend.mapper.BotMapper;
import club.superk.hueacm.backend.pojo.Bot;
import club.superk.hueacm.backend.pojo.User;
import club.superk.hueacm.backend.result.Result;
import club.superk.hueacm.backend.service.impl.util.UserDetailsImpl;
import club.superk.hueacm.backend.service.user.bot.RemoveBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RemoveBotServiceImpl implements RemoveBotService {

    @Autowired
    private BotMapper botMapper;

    @Override
    public Result<Void> remove(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        int bot_id = Integer.parseInt(data.get("bot_id"));
        Bot bot = botMapper.selectById(bot_id);

        if (bot == null) {
            return Result.failed("该Bot不存在或已被删除");
        }

        if (!bot.getUserId().equals(user.getId())) {
            return Result.failed("你没有权限删除该Bot");
        }

        botMapper.deleteById(bot_id);
        return Result.success();
    }
}
