package club.superk.hueacm.backend.controller.community.comment;

import club.superk.hueacm.backend.pojo.Comment;
import club.superk.hueacm.backend.result.Result;
import club.superk.hueacm.backend.service.community.comment.AddCommentService;
import club.superk.hueacm.backend.service.community.comment.QueryCommentService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class QueryCommentController {

    @Autowired
    private QueryCommentService queryCommentService;

    @GetMapping("/tree/{articleId}")
    public Result<List<Comment>> queryCommentByArticleId(@PathVariable Integer articleId) {
        return queryCommentService.queryCommentByArticleId(articleId);
    }

}
