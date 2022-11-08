package club.superk.hueacm.backend.service.community.comment;

import club.superk.hueacm.backend.result.Result;

import java.util.Map;

public interface AddCommentService {

    public Result<Void> addComment(Map<String, String> data);

}
