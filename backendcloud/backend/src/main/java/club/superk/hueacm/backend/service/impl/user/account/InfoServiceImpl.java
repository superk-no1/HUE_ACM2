package club.superk.hueacm.backend.service.impl.user.account;

import club.superk.hueacm.backend.mapper.UserMapper;
import club.superk.hueacm.backend.pojo.User;
import club.superk.hueacm.backend.result.Result;
import club.superk.hueacm.backend.service.impl.util.UserDetailsImpl;
import club.superk.hueacm.backend.service.user.account.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class InfoServiceImpl implements InfoService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Result<User> getInfo() {
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        UserDetailsImpl loginUser = (UserDetailsImpl) authentication.getPrincipal();
        User user = loginUser.getUser();

        return Result.success(user);
    }

    @Override
    public Result<User> getUserById(Integer userId) {
        User user = userMapper.selectById(userId);
        return Result.success(user);
    }
}
