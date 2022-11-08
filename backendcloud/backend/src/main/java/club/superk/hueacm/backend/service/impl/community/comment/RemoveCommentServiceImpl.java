package club.superk.hueacm.backend.service.impl.community.comment;

import club.superk.hueacm.backend.mapper.CommentMapper;
import club.superk.hueacm.backend.pojo.Comment;
import club.superk.hueacm.backend.pojo.User;
import club.superk.hueacm.backend.result.Result;
import club.superk.hueacm.backend.service.community.comment.RemoveCommentService;
import club.superk.hueacm.backend.service.impl.util.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class RemoveCommentServiceImpl implements RemoveCommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public Result<Void> removeComment(Integer commentId) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        Comment comment = commentMapper.selectById(commentId);

        if (comment == null) {
            return Result.failed("该评论不存在或已被删除");
        }

        if (!comment.getUserId().equals(user.getId())) {
//            System.out.println(comment);
            return Result.failed("你没有权限删除该评论");
        }

        commentMapper.deleteById(commentId);
        return Result.success();
    }
}
