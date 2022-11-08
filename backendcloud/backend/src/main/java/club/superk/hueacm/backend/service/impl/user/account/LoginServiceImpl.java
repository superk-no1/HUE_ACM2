package club.superk.hueacm.backend.service.impl.user.account;

import club.superk.hueacm.backend.pojo.User;
import club.superk.hueacm.backend.result.Result;
import club.superk.hueacm.backend.service.impl.util.UserDetailsImpl;
import club.superk.hueacm.backend.service.user.account.LoginService;
import club.superk.hueacm.backend.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public Result<String> getToken(String username, String password) {
        // 根据用户输入使用 authenticationToken 进行验证
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, password);

        // 若登录失败会自动抛出异常
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        // 获得登录的用户信息
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticate.getPrincipal();
        User user = loginUser.getUser();

        // 将用户ID封装成一个JWT token
        String jwt = JwtUtil.createJWT(user.getId().toString());

        return Result.success(jwt);
    }
}
