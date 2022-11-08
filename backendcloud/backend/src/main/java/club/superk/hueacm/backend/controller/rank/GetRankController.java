package club.superk.hueacm.backend.controller.rank;

import club.superk.hueacm.backend.result.Result;
import club.superk.hueacm.backend.service.rank.GetRankListService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/rank")
public class GetRankController {
    @Autowired
    private GetRankListService getRankListService;

    @GetMapping("/getlist")
    public Result<JSONObject> getRankList(@RequestParam Map<String, String> data) {
        Integer page = Integer.parseInt(data.get("page"));
        return Result.success(getRankListService.getRankList(page));
    }
}
