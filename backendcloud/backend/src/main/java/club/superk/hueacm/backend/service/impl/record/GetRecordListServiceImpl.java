package club.superk.hueacm.backend.service.impl.record;

import club.superk.hueacm.backend.mapper.RecordMapper;
import club.superk.hueacm.backend.mapper.UserMapper;
import club.superk.hueacm.backend.pojo.Record;
import club.superk.hueacm.backend.pojo.User;
import club.superk.hueacm.backend.service.record.GetRecordListService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class GetRecordListServiceImpl implements GetRecordListService {
    @Autowired
    private RecordMapper recodeMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public JSONObject getList(Integer page) {
        IPage<Record> recordIPage = new Page<>(page, 8);
        QueryWrapper<Record> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        List<Record> records = recodeMapper.selectPage(recordIPage, queryWrapper).getRecords();
        JSONObject resp = new JSONObject();
        List<JSONObject> items = new LinkedList<>();
        for (Record record : records) {
            User userA = userMapper.selectById(record.getAId());
            User userB = userMapper.selectById(record.getBId());
            JSONObject item = new JSONObject();
            item.put("a_photo", userA.getAvatar());
            item.put("a_username", userA.getUsername());
            item.put("b_photo", userB.getAvatar());
            item.put("b_username", userB.getUsername());
            item.put("record", record);
            String result = "平局";
            if ("A".equals(record.getLoser())) {
                result = "B玩家获胜";
            } else if ("B".equals(record.getLoser())) {
                result = "A玩家获胜";
            }
            item.put("result", result);
            items.add(item);
        }
        resp.put("records", items);
        resp.put("records_count", recodeMapper.selectCount(null));

        return resp;
    }
}
