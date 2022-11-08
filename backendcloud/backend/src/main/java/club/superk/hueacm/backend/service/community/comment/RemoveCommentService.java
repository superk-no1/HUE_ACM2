package club.superk.hueacm.backend.service.community.comment;

import club.superk.hueacm.backend.result.Result;

public interface RemoveCommentService {

    public Result<Void> removeComment(Integer commentId);

}
