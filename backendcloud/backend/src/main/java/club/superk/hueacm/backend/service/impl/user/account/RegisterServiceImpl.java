package club.superk.hueacm.backend.service.impl.user.account;

import club.superk.hueacm.backend.mapper.UserMapper;
import club.superk.hueacm.backend.pojo.User;
import club.superk.hueacm.backend.result.Result;
import club.superk.hueacm.backend.service.user.account.RegisterService;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Result<Void> register(String username, String password, String confirmedPassword) {
        if (StrUtil.isBlank(username)) {
            return Result.failed("用户名不允许为空");
        }
        if (StrUtil.isBlank(password) || StrUtil.isBlank(confirmedPassword)) {
            return Result.failed("密码不允许为空");
        }
        if (!password.equals(confirmedPassword)) {
            return Result.failed("两次输入密码不一致");
        }

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        List<User> userList = userMapper.selectList(queryWrapper);
        if (!userList.isEmpty()) {
            return Result.failed("同户名已存在");
        }

        String encodedPassword = passwordEncoder.encode(password);
        String avatar = "https://img-qn.51miz.com/Element/00/88/57/68/41049d87_E885768_96b5cecc.png";
        User user = new User(null, username, encodedPassword, avatar, 1500, null);
        userMapper.insert(user);

        return Result.success();
    }
}
