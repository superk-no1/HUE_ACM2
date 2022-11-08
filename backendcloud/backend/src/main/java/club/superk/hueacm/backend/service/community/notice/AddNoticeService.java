package club.superk.hueacm.backend.service.community.notice;

import club.superk.hueacm.backend.result.Result;

import java.util.Map;

public interface AddNoticeService {
    public Result<Void> addNotice(Map<String, String> data);
}
