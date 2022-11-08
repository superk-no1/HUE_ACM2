package club.superk.hueacm.backend.service.community.article;

import club.superk.hueacm.backend.pojo.Article;
import club.superk.hueacm.backend.result.Result;

import java.util.List;

public interface QueryArticleService {

    public Result<List<Article>> QueryAllArticle();

    public Result<Article> QueryArticleById(Integer id);

}
