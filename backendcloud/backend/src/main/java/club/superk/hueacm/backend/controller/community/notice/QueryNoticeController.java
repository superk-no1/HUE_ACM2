package club.superk.hueacm.backend.controller.community.notice;

import club.superk.hueacm.backend.pojo.Notice;
import club.superk.hueacm.backend.result.Result;
import club.superk.hueacm.backend.service.community.notice.QueryNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/community/notice")
public class QueryNoticeController {

    @Autowired
    private QueryNoticeService queryNoticeService;

    @GetMapping("/query")
    public Result<List<Notice>> queryNotice() {
        return queryNoticeService.queryNotice();
    }

}
