package club.superk.hueacm.backend.controller.community.article;

import club.superk.hueacm.backend.pojo.Article;
import club.superk.hueacm.backend.result.Result;
import club.superk.hueacm.backend.service.community.article.QueryArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/community/article")
public class QueryArticleController {

    @Autowired
    private QueryArticleService queryArticleService;

    @GetMapping("/all")
    public Result<List<Article>> queryAll() {
        return queryArticleService.QueryAllArticle();
    }

    @GetMapping("/{id}")
    public Result<Article> queryById(@PathVariable Integer id) {
        return queryArticleService.QueryArticleById(id);
    }

}
