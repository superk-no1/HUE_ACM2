package club.superk.hueacm.backend.service.community.article;

import club.superk.hueacm.backend.result.Result;

import java.util.Map;

public interface AddArticleService {

    public Result<Void> addDiscuss(Map<String, String> data);

}
