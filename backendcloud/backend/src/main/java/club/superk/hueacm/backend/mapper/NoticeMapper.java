package club.superk.hueacm.backend.mapper;

import club.superk.hueacm.backend.pojo.Notice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface NoticeMapper extends BaseMapper<Notice> {

    @Select("select n.*, u.username from notice n left join user u on n.user_id = u.id")
    List<Notice> queryAllNotice();

}
