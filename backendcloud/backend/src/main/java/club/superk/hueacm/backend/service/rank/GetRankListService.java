package club.superk.hueacm.backend.service.rank;

import com.alibaba.fastjson.JSONObject;

public interface GetRankListService {
    JSONObject getRankList(Integer page);
}
