package club.superk.hueacm.backend.service.community.notice;

import club.superk.hueacm.backend.pojo.Notice;
import club.superk.hueacm.backend.result.Result;

import java.util.List;
import java.util.Map;

public interface QueryNoticeService {
    public Result<List<Notice>> queryNotice();
}
