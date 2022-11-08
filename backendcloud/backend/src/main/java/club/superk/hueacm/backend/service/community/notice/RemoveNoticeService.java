package club.superk.hueacm.backend.service.community.notice;

import club.superk.hueacm.backend.pojo.Notice;
import club.superk.hueacm.backend.result.Result;

import java.util.Map;

public interface RemoveNoticeService {
    public Result<Void> removeNotice(Map<String, String> data);
}
