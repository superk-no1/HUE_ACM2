package club.superk.hueacm.backend.service.impl.community.article;

import club.superk.hueacm.backend.mapper.ArticleMapper;
import club.superk.hueacm.backend.pojo.Article;
import club.superk.hueacm.backend.result.Result;
import club.superk.hueacm.backend.service.community.article.QueryArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueryArticleServiceImpl implements QueryArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public Result<List<Article>> QueryAllArticle() {
        List<Article> articleList = articleMapper.selectList(null);
        return Result.success(articleList);
    }

    @Override
    public Result<Article> QueryArticleById(Integer id) {
        Article article = articleMapper.selectById(id);
        return Result.success(article);
    }

}
