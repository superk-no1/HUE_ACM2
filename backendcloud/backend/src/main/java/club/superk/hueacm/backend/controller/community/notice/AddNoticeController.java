package club.superk.hueacm.backend.controller.community.notice;

import club.superk.hueacm.backend.result.Result;
import club.superk.hueacm.backend.service.community.notice.AddNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/community/notice")
public class AddNoticeController {

    @Autowired
    private AddNoticeService addNoticeService;

    @PostMapping("/add")
    public Result<Void> addNotice(@RequestParam Map<String, String> data) {
        return addNoticeService.addNotice(data);
    }

}
