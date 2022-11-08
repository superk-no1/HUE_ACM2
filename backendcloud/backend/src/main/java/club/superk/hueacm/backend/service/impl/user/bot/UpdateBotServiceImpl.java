package club.superk.hueacm.backend.service.impl.user.bot;

import club.superk.hueacm.backend.mapper.BotMapper;
import club.superk.hueacm.backend.pojo.Bot;
import club.superk.hueacm.backend.pojo.User;
import club.superk.hueacm.backend.result.Result;
import club.superk.hueacm.backend.service.impl.util.UserDetailsImpl;
import club.superk.hueacm.backend.service.user.bot.UpdateBotService;
import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class UpdateBotServiceImpl implements UpdateBotService {

    @Autowired
    private BotMapper botMapper;

    @Override
    public Result<Void> update(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        int bot_id = Integer.parseInt(data.get("bot_id"));

        String title = data.get("title");
        String description = data.get("description");
        String content = data.get("content");

        if (StrUtil.isBlank(title)) {
            return Result.failed("标题不能为空");
        }
        if (title.length() > 50) {
            return Result.failed("标题长度不能超过50");
        }

        if (StrUtil.isBlank(description)) {
            description = "这个用户很懒，什么也没有留下~";
        }
        if (description.length() > 200) {
            return Result.failed("标题长度不能超过200");
        }

        if (StrUtil.isBlank(content)) {
            return Result.failed("代码不能为空");
        }
        if (content.length() > 8000) {
            return Result.failed("代码长度不能超过8000");
        }

        Bot bot = botMapper.selectById(bot_id);
        if (bot == null) {
            return Result.failed("该Bot不存在或已被删除");
        }
        if (!bot.getUserId().equals(user.getId())) {
            return Result.failed("你没有权限删除该Bot");
        }

        Bot new_bot = new Bot(bot_id, user.getId(), title, description, content, bot.getCreatetime(), new Date());

        botMapper.updateById(new_bot);

        return Result.success();
    }
}
