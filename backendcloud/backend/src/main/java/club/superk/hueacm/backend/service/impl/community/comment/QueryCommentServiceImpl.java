package club.superk.hueacm.backend.service.impl.community.comment;

import club.superk.hueacm.backend.mapper.CommentMapper;
import club.superk.hueacm.backend.pojo.Comment;
import club.superk.hueacm.backend.result.Result;
import club.superk.hueacm.backend.service.community.comment.QueryCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QueryCommentServiceImpl implements QueryCommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public Result<List<Comment>> queryCommentByArticleId(Integer articleId) {
        // 查询所有评论和回复
        List<Comment> articleComments = commentMapper.queryCommentByArticleId(articleId);

        // 查询评论数据（不包括回复）
        List<Comment> originList = articleComments.stream().filter(comment -> comment.getOriginId() == 0).collect(Collectors.toList());

        // 设置评论数据的子节点，也就是回复内容
        for (Comment origin: originList) {
            // 表示回复对象集合
            List<Comment> commentList = articleComments.stream().filter(comment -> origin.getId().equals(comment.getOriginId())).collect(Collectors.toList());
            commentList.forEach(comment -> {
                // 找到父级评论的用户ID和昵称，并设置给当前的回复对象
                articleComments.stream().filter(c1 -> c1.getId().equals(comment.getPid())).findFirst().ifPresent((v -> {
                    comment.setPUserId(v.getUserId());
                    comment.setPName(v.getUsername());
                }));
            });
            origin.setChildren(commentList);
        }
        return Result.success(originList);
    }
}
