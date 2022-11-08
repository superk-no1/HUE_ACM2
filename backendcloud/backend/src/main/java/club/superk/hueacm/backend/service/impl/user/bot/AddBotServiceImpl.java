package club.superk.hueacm.backend.service.impl.user.bot;

import club.superk.hueacm.backend.mapper.BotMapper;
import club.superk.hueacm.backend.pojo.Bot;
import club.superk.hueacm.backend.pojo.User;
import club.superk.hueacm.backend.result.Result;
import club.superk.hueacm.backend.service.impl.util.UserDetailsImpl;
import club.superk.hueacm.backend.service.user.bot.AddBotService;
import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class AddBotServiceImpl implements AddBotService {

    @Autowired
    private BotMapper botMapper;

    @Override
    public Result<Void> add(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

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

        Date now = new Date();
        Bot bot = new Bot(null, user.getId(), title, description, content, now, now);
        botMapper.insert(bot);

        return Result.success();
    }
}
