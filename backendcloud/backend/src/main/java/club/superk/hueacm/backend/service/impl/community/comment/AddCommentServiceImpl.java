package club.superk.hueacm.backend.service.impl.community.comment;

import club.superk.hueacm.backend.mapper.CommentMapper;
import club.superk.hueacm.backend.pojo.Article;
import club.superk.hueacm.backend.pojo.Comment;
import club.superk.hueacm.backend.pojo.User;
import club.superk.hueacm.backend.result.Result;
import club.superk.hueacm.backend.service.community.comment.AddCommentService;
import club.superk.hueacm.backend.service.impl.util.UserDetailsImpl;
import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class AddCommentServiceImpl implements AddCommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public Result<Void> addComment(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        String content = data.get("content");
        Integer articleId = Integer.parseInt(data.get("article_id"));
        Integer pid = Integer.parseInt(data.get("pid"));

        if (StrUtil.isBlank(content)) {
            return Result.failed("内容不能为空");
        }
        if (content.length() > 100) {
            return Result.failed("评论长度不能超过100");
        }

        Date now = new Date();
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setUserId(user.getId());
        comment.setCreateTime(now);
        comment.setArticleId(articleId);
        comment.setUsername(user.getUsername());
        comment.setAvatar(user.getAvatar());

        comment.setPid(pid);

        if (pid == 0) {
            comment.setOriginId(0);
        } else {
            Comment pcomment = commentMapper.selectById(pid);
            if (pcomment.getOriginId() != 0) {
                // 若当前回复的父级有祖宗，那么就设置相同的祖宗
                comment.setOriginId(pcomment.getOriginId());
            } else {
                // 否则就设置父级为当前回复的祖宗
                comment.setOriginId(pcomment.getId());
            }
        }

        commentMapper.insert(comment);
        return Result.success();
    }
}
