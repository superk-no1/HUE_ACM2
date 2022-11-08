package club.superk.hueacm.backend.service.impl.community.notice;

import club.superk.hueacm.backend.mapper.NoticeMapper;
import club.superk.hueacm.backend.pojo.Notice;
import club.superk.hueacm.backend.result.Result;
import club.superk.hueacm.backend.service.community.notice.QueryNoticeService;
import club.superk.hueacm.backend.service.user.account.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueryNoticeServiceImpl implements QueryNoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    @Autowired
    private InfoService infoService;

    @Override
    public Result<List<Notice>> queryNotice() {
        List<Notice> noticeList = noticeMapper.queryAllNotice();
        return Result.success(noticeList);
    }
}
