package club.superk.hueacm.backend.controller.community.comment;

import club.superk.hueacm.backend.result.Result;
import club.superk.hueacm.backend.service.community.comment.RemoveCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
public class RemoveCommentController {

    @Autowired
    private RemoveCommentService removeCommentService;

    @PostMapping("/remove")
    public Result<Void> removeComment(@RequestParam Integer commentId) {
        return removeCommentService.removeComment(commentId);
    }

}
