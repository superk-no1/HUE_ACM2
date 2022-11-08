package club.superk.hueacm.backend.service.impl.user.account;

import club.superk.hueacm.backend.mapper.UserMapper;
import club.superk.hueacm.backend.pojo.User;
import club.superk.hueacm.backend.result.Result;
import club.superk.hueacm.backend.service.impl.util.UserDetailsImpl;
import club.superk.hueacm.backend.service.user.account.UpdateUserService;
import cn.hutool.core.util.StrUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class UpdateUserServiceImpl implements UpdateUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Result<Void> updateUserInfo(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        int userId = Integer.parseInt(data.get("user_id"));
        String username = data.get("username");
        String password = data.get("password");
        String confirmedPassword = data.get("confirmed_password");
        String email = data.get("email");

        if (!user.getId().equals(userId)) {
            return Result.failed("你没有权限更新该用户的信息！");
        }
        if (StrUtil.isBlank(username)) {
            return Result.failed("用户名不允许为空");
        }
        if (StrUtil.isBlank(password) || StrUtil.isBlank(confirmedPassword)) {
            return Result.failed("密码不允许为空");
        }
        if (!password.equals(confirmedPassword)) {
            return Result.failed("两次输入密码不一致");
        }
        if (StrUtil.isBlank(email)) {
            return Result.failed("邮箱不允许为空");
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User user0 = userMapper.selectOne(queryWrapper);
        if (user0 != null && !user0.getId().equals(userId)) {
            return Result.failed("同户名已存在");
        }

        String encodedPassword = passwordEncoder.encode(password);
        user.setId(userId);
        user.setUsername(username);
        user.setPassword(encodedPassword);
        user.setEmail(email);

        userMapper.updateById(user);

        return Result.success();
    }

    @Override
    public Result<String> updateUserAvatar(MultipartFile file) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        String endpoint = "oss-cn-guangzhou.aliyuncs.com";
        String accessKeyId = "LTAI5tGZ5YghN2Su19km9Nyi";
        String accessKeySecret = "tYXhenZNLXYnf71zdmGwSPW1luGmwA";
        String bucketName = "superk-bucket";

        // 创建OSSClient实例。
        OSS ossClient = null;

        try {
            // 创建 OSS 实例
            ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            // 获取文件上传的流
            InputStream inputStream = file.getInputStream();
            // 构建目录
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String datePath = simpleDateFormat.format(new Date());
            // 获取文件名
            String originName = file.getOriginalFilename();
            String fileName = UUID.randomUUID().toString();
            String suffix = originName.substring(originName.lastIndexOf('.'));
            String newName = fileName + suffix;
            String fileUrl = "avatar/" + datePath + "/" + newName;
            // 文件上传到阿里云服务器
            ossClient.putObject(bucketName, fileUrl, inputStream);

            // 更新用户头像
            String avatarUrl = "https://" + bucketName + "." + endpoint + "/" + fileUrl;
            user.setAvatar(avatarUrl);
            userMapper.updateById(user);
            return Result.success(avatarUrl);
        } catch (Exception oe) {
            oe.printStackTrace();
            return Result.failed("上传失败");
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }

}
