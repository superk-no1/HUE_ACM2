package club.superk.hueacm.backend.service.impl.user.bot;

import club.superk.hueacm.backend.mapper.BotMapper;
import club.superk.hueacm.backend.pojo.Bot;
import club.superk.hueacm.backend.pojo.User;
import club.superk.hueacm.backend.result.Result;
import club.superk.hueacm.backend.service.impl.util.UserDetailsImpl;
import club.superk.hueacm.backend.service.user.bot.QueryBotService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueryBotServiceImpl implements QueryBotService {

    @Autowired
    private BotMapper botMapper;

    @Override
    public Result<List<Bot>> query() {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        QueryWrapper<Bot> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", user.getId());

        List<Bot> botList = botMapper.selectList(queryWrapper);
        return Result.success(botList);
    }
}
