package club.superk.hueacm.backend.controller.community.comment;

import club.superk.hueacm.backend.result.Result;
import club.superk.hueacm.backend.service.community.comment.AddCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/comment")
public class AddCommentController {

    @Autowired
    private AddCommentService addCommentService;

    @PostMapping("/add")
    public Result<Void> addComment(@RequestParam Map<String, String> data) {
        return addCommentService.addComment(data);
    }

}
