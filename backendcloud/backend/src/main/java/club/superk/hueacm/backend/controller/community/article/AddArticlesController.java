package club.superk.hueacm.backend.controller.community.article;

import club.superk.hueacm.backend.result.Result;
import club.superk.hueacm.backend.service.impl.community.article.AddArticleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/community/article")
public class AddArticlesController {

    @Autowired
    private AddArticleServiceImpl addDiscussService;

    @PostMapping("/add")
    public Result<Void> addDiscuss(@RequestParam Map<String, String> data) {
        return addDiscussService.addDiscuss(data);
    }

}
