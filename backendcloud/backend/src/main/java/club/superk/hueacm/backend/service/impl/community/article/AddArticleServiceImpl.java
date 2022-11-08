package club.superk.hueacm.backend.service.impl.community.article;

import club.superk.hueacm.backend.mapper.ArticleMapper;
import club.superk.hueacm.backend.pojo.Article;
import club.superk.hueacm.backend.pojo.User;
import club.superk.hueacm.backend.result.Result;
import club.superk.hueacm.backend.service.community.article.AddArticleService;
import club.superk.hueacm.backend.service.impl.util.UserDetailsImpl;
import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class AddArticleServiceImpl implements AddArticleService {

    @Autowired
    private ArticleMapper articleMapper;


    @Override
    public Result<Void> addDiscuss(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        String title = data.get("title");
        String content = data.get("content");
//        Integer type = Integer.parseInt(data.get("type"));

        if (StrUtil.isBlank(title)) {
            return Result.failed("标题不能为空");
        }
        if (title.length() > 50) {
            return Result.failed("标题长度不能超过50");
        }

        if (StrUtil.isBlank(content)) {
            return Result.failed("内容不能为空");
        }
        if (content.length() > 10000) {
            return Result.failed("文章长度不能超过10000");
        }

        Date now = new Date();
        Article article = new Article(null, user.getId(), user.getUsername(), user.getAvatar(), title, content, 1, now, 0);
        articleMapper.insert(article);

        return Result.success();
    }
}
