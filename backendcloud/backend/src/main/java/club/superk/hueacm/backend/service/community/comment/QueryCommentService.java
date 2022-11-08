package club.superk.hueacm.backend.service.community.comment;

import club.superk.hueacm.backend.pojo.Comment;
import club.superk.hueacm.backend.result.Result;

import java.util.List;

public interface QueryCommentService {

    public Result<List<Comment>> queryCommentByArticleId(Integer articleId);

}
