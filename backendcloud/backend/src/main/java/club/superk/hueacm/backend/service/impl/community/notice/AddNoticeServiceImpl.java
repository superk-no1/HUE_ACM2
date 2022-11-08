package club.superk.hueacm.backend.service.impl.community.notice;

import club.superk.hueacm.backend.mapper.NoticeMapper;
import club.superk.hueacm.backend.pojo.Notice;
import club.superk.hueacm.backend.pojo.User;
import club.superk.hueacm.backend.result.Result;
import club.superk.hueacm.backend.service.community.notice.AddNoticeService;
import club.superk.hueacm.backend.service.impl.util.UserDetailsImpl;
import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class AddNoticeServiceImpl implements AddNoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public Result<Void> addNotice(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        String title = data.get("title");
        String content = data.get("content");

        if (StrUtil.isBlank(title)) {
            return Result.failed("标题不能为空");
        }
        if (title.length() > 50) {
            return Result.failed("标题长度不能超过50");
        }

        if (StrUtil.isBlank(content)) {
            return Result.failed("内容不能为空");
        }
        if (content.length() > 800) {
            return Result.failed("公告长度不能超过800");
        }

        Date now = new Date();
        Notice notice = null;
        notice.setUserId(user.getId());
        notice.setTitle(title);
        notice.setContent(content);
        notice.setCreatetime(now);
        noticeMapper.insert(notice);

        return Result.success();
    }
}

